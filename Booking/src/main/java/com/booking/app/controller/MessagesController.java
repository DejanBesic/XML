package com.booking.app.controller;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.booking.app.DTOs.MessageRequest;
import com.booking.app.model.Message;
import com.booking.app.model.Reservation;
import com.booking.app.model.User;
import com.booking.app.service.impl.MessageServiceImpl;
import com.booking.app.service.impl.ReservationServiceImpl;
import com.booking.app.service.impl.UserServiceImpl;

@RestController
@RequestMapping("/api/messages")
public class MessagesController {
	
	@Autowired
	UserServiceImpl userService;
	
	@Autowired
	MessageServiceImpl messageService;
	
	@Autowired
	ReservationServiceImpl reservationService;
	
    @GetMapping("/getMessages")
	public ResponseEntity<?> getMessages() {
		//User user = userService.findByUsername(SecurityContextHolder.getContext().getAuthentication().getName());
    	User user = userService.findByUsername("twiste");
		return new ResponseEntity<>(messageService.findForUser(user), HttpStatus.OK);
	}
	
	@RequestMapping(value= "/sendMessage", method=RequestMethod.POST)
	public ResponseEntity<?> sendMessage(@RequestBody MessageRequest messageRequest) {
		Reservation reservation = reservationService.findOne(messageRequest.getReservationId());
		User sender = userService.findByUsername(SecurityContextHolder.getContext().getAuthentication().getName());
		
		if (reservation == null) {
			return new ResponseEntity<>("Wrong reservation id.", HttpStatus.BAD_REQUEST);
		}
		
		User reciver = reservation.getFacility().getOwner();
		
		//GULA OVDE STAVITI TRENUTNO VREME, NISAM SIGURAN KAKO SE RADI TO 
		Date date = new Date();
		
		Message message = new Message(reciver, sender, messageRequest.getMessage(), date);
		messageService.save(message);
		
		return new ResponseEntity<>(date, HttpStatus.OK);
	}
}
