package com.booking.app.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.booking.app.DTOs.ReservationRequest;
import com.booking.app.logger.Logger;
import com.booking.app.model.Facility;
import com.booking.app.model.Reservation;
import com.booking.app.model.User;
import com.booking.app.service.impl.FacilityServiceImpl;
import com.booking.app.service.impl.ReservationServiceImpl;
import com.booking.app.service.impl.UserServiceImpl;

@RestController
@RequestMapping("/reservation")
public class ReservationController {
	
		@Autowired
		ReservationServiceImpl reservationService;
		
		@Autowired
		FacilityServiceImpl facilityService;
		
		@Autowired
		UserServiceImpl userService;
	
	  	@PostMapping()
	    public ResponseEntity<?> reserve(@RequestBody ReservationRequest request) {
	        
	  		Facility facility = facilityService.findById(request.getFacilityId());
	  		
	  		User user = userService.findByUsername(SecurityContextHolder.getContext().getAuthentication().getName());
	  		
	  		if (user == null || facility == null) {
	  			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
	  		}
	  			  		
	  		Reservation reservation = reservationService.save(new Reservation(user, facility, request.getStartDate(), request.getEndDate(), request.getPrice(), false));
	  		
	  		if (reservation == null) {
	  			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
	  		}
	  		
	  		return new ResponseEntity<>(reservation, HttpStatus.OK);
	    }
	  	
	  	@GetMapping()
	  	public ResponseEntity<?> getReservations() {
	  		User user = userService.findByUsername(SecurityContextHolder.getContext().getAuthentication().getName());
	  		
	  		if (user == null) {
	  			new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
	  		}
	  		
	  		return new ResponseEntity<>(reservationService.findByGuest(user), HttpStatus.OK);
	  	}
	  	
	  	@RequestMapping(value= "/delete", method=RequestMethod.DELETE)
	  	public ResponseEntity<?> delete(@RequestParam Long id) throws IOException{
	  		Reservation reservation = reservationService.findOne(id);
	  		
	  		if (reservation == null || !SecurityContextHolder.getContext().getAuthentication().getName().equals(reservation.getGuest().getUsername())) {
	  			
	  			return new ResponseEntity<>(reservation, HttpStatus.UNAUTHORIZED);
	  		}
	  		
	  		reservationService.delete(id);
            Logger.getInstance().log("Deleted reservation id: "+id+" from username: "+SecurityContextHolder.getContext().getAuthentication().getName());
	  		
	  		return new ResponseEntity<>(reservation, HttpStatus.OK);
	  	}

}
