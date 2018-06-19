package com.booking.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.booking.app.DTOs.ReservationRequest;
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
	    public ResponseEntity<?> authenticateUser(@RequestBody ReservationRequest request) {
	        
	  		Facility facility = facilityService.findById(request.getFacilityId());
	  		
	  		User user = userService.findByUsername(SecurityContextHolder.getContext().getAuthentication().getName());
	  		
	  		if (user == null || facility == null) {
	  			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
	  		}
	  		
	  		Reservation reservation = reservationService.save(new Reservation(user, facility, request.getStartDate(), request.getEndDate(), request.getPrice()));
	  		
	  		return new ResponseEntity<>(reservation, HttpStatus.OK);
	    }

}
