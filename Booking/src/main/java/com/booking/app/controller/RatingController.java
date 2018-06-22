package com.booking.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.booking.app.DTOs.RateRequest;
import com.booking.app.model.Facility;
import com.booking.app.model.Rating;
import com.booking.app.model.User;
import com.booking.app.service.impl.FacilityServiceImpl;
import com.booking.app.service.impl.RatingServiceImpl;
import com.booking.app.service.impl.UserServiceImpl;

@RestController
@RequestMapping("/rating")
public class RatingController {
	
	@Autowired
	RatingServiceImpl ratingService;
	
	@Autowired
	FacilityServiceImpl facilityService;
	
	@Autowired
	UserServiceImpl userService;
	
	@RequestMapping(value= "/rate", method=RequestMethod.PUT)
	public ResponseEntity<?> rating(@RequestBody RateRequest rating) {
		
		if (rating == null) {
			return new ResponseEntity<>("Failed to rate.", HttpStatus.BAD_REQUEST);	
		}
		
		User user = userService.findByUsername(SecurityContextHolder.getContext().getAuthentication().getName());
		
		Facility facility = facilityService.findById(rating.getId());
		
		Rating rated = ratingService.findByFacilityAnduser(facility, user);
		if (rated == null) {
			return new ResponseEntity<>("You have already rated this facility.", HttpStatus.BAD_REQUEST);
		}
		
		Rating rate = ratingService.save(new Rating(rating.getRating(), rating.getComment(), user, facility, false, false));
		
		
		return new ResponseEntity<>(rate, HttpStatus.OK);
	}

	@GetMapping("/ratings")
	public ResponseEntity<?> getRatings(@RequestParam Long id) {
		
		Facility facility = facilityService.findById(id);
		if (facility != null) {
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}
		
		return new ResponseEntity<>(ratingService.findByFacility(facility), HttpStatus.OK);
	}
	
}
