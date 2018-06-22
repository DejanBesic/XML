package com.booking.app.service;

import java.util.List;

import com.booking.app.DTOs.RatingReviewDTO;
import com.booking.app.model.Facility;
import com.booking.app.model.Rating;
import com.booking.app.model.User;

public interface RatingService {

	Rating findById(Long id);
	
	List<Rating> findAll();
	
	Rating save(Rating rating);
	
	void delete(Long id);
	
	List<Rating> findByFacility(Facility facility);
	
	Rating findByFacilityAnduser(Facility facility, User user);
	
	List<RatingReviewDTO> findAllUnreviewed(); 
}
