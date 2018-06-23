package com.booking.app.controller;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.stream.Collectors;

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
import com.booking.app.DTOs.RatingResponse;
import com.booking.app.model.Facility;
import com.booking.app.model.Rating;
import com.booking.app.model.Reservation;
import com.booking.app.model.User;
import com.booking.app.service.impl.FacilityServiceImpl;
import com.booking.app.service.impl.RatingServiceImpl;
import com.booking.app.service.impl.ReservationServiceImpl;
import com.booking.app.service.impl.UserServiceImpl;

@RestController
public class RatingController {
	
	@Autowired
	RatingServiceImpl ratingService;
	
	@Autowired
	FacilityServiceImpl facilityService;
	
	@Autowired
	UserServiceImpl userService;
	
	@Autowired
	ReservationServiceImpl reservationService;
	
	@RequestMapping(value= "/rating/rate", method=RequestMethod.PUT)
	public ResponseEntity<?> rating(@RequestBody RateRequest rating) {
		
		if (rating == null) {
			return new ResponseEntity<>("Failed to rate.", HttpStatus.BAD_REQUEST);	
		}
		
		User user = userService.findByUsername(SecurityContextHolder.getContext().getAuthentication().getName());
		
		Facility facility = facilityService.findById(rating.getId());
		
		Rating rated = ratingService.findByFacilityAndUser(facility, user);
		
		Date todaysDate = new Date(Calendar.getInstance().getTime().getTime());
		List<Reservation> reservations = reservationService.findByGuestAndFacility(user, facility);
		for (Reservation r : reservations) {
			int endDate = todaysDate.compareTo(r.getToDate());
			if (endDate <= 0 || !r.isConfirmed()) {
				return new ResponseEntity<>("You can't rate until reservation date is over", HttpStatus.BAD_REQUEST);
			}
		
		}
		
		
		if (rated != null) {
			return new ResponseEntity<>("You have already rated this facility.", HttpStatus.BAD_REQUEST);
		}
		

		ratingService.save(new Rating(rating.getRating(), rating.getComment(), user, facility, false,false));

		
		
		return new ResponseEntity<>(null, HttpStatus.OK);
	}

	@GetMapping("/api/rating/ratings")
	public ResponseEntity<?> getRatings(@RequestParam Long id) {
		
		Facility facility = facilityService.findById(id);
		if (facility == null) {
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}
		List<Rating> ratingList = ratingService.findByFacility(facility).stream().filter(rating -> rating.isApproved()).collect(Collectors.toList());
		List<RatingResponse> ratings = new ArrayList<RatingResponse>();
		for (Rating r : ratingList) {
			ratings.add(new RatingResponse(r.getId(), r.getUser().getUsername(), r.getRating(), r.getComment(), r.getFacility().getId()));
		}
		
		return new ResponseEntity<>(ratings, HttpStatus.OK);
	}

}
