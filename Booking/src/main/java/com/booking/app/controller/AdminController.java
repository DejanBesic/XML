package com.booking.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.booking.app.model.Rating;
import com.booking.app.security.JwtTokenProvider;
import com.booking.app.service.impl.RatingServiceImpl;
import com.booking.app.service.impl.UserServiceImpl;

@RestController
@RequestMapping("/admin")
public class AdminController {
	
    @Autowired
    UserServiceImpl userService;
    
    @Autowired
    RatingServiceImpl ratingService;
    
    @Autowired
    AuthenticationManager authenticationManager;
    
    @Autowired
    JwtTokenProvider tokenProvider;

	@GetMapping("/getUsers")
    public ResponseEntity<?> getUser() {
    	return new ResponseEntity<>(userService.findAll(), HttpStatus.OK);
    }
	
	@GetMapping("/getRatings")
	public ResponseEntity<?> getRating() {
		return new ResponseEntity<>(ratingService.findAllUnreviewed(), HttpStatus.OK);
    }
	
	@RequestMapping(value= "/approve", method=RequestMethod.POST)
	public ResponseEntity<?> approve(@RequestBody Rating rating) {
		Rating rat = ratingService.findById(rating.getId());
		if (rat==null) {
			return new ResponseEntity<>("Failed to approve.", HttpStatus.BAD_REQUEST);
		}
		rat.setReviewed(true);
		rat.setApproved(true);
		ratingService.save(rat);
		return new ResponseEntity<>(rat, HttpStatus.OK); 
	}
	
	@RequestMapping(value= "/block", method=RequestMethod.POST)
	public ResponseEntity<?> block(@RequestBody Rating rating) {
		Rating rat = ratingService.findById(rating.getId());
		if (rat==null) {
			return new ResponseEntity<>("Failed to block.", HttpStatus.BAD_REQUEST);
		}
		rat.setReviewed(true);
		rat.setApproved(false);
		ratingService.save(rat);
		return new ResponseEntity<>(rat, HttpStatus.OK); 
	}
}
