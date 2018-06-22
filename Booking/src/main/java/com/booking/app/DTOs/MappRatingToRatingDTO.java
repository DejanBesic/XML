package com.booking.app.DTOs;

import java.util.ArrayList;
import java.util.List;

import com.booking.app.model.Rating;

public final class MappRatingToRatingDTO {

	public static RatingReviewDTO mappRating(Rating rating) {
		return new RatingReviewDTO(rating.getId(),rating.getUser().getName()+" "+rating.getUser().getLastName(),rating.getFacility().getName(),rating.getComment());
	}
	
	public static List<RatingReviewDTO> mappRatings(List<Rating> ratings){
		List<RatingReviewDTO> list = new ArrayList<RatingReviewDTO>();
		for (Rating r : ratings) {
			list.add(mappRating(r));
		}
		return list; 
	}
}
