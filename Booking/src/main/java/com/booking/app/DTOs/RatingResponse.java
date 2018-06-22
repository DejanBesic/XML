package com.booking.app.DTOs;

public class RatingResponse {
	
	private Long id;
	
	private String username;
	
	private int rating;
	
	private String comment;
	
	private Long facilityId;

	
	public RatingResponse() {
		
	}
	
	public RatingResponse(Long id, String username, int rating, String comment, Long facilityId) {
		super();
		this.id = id;
		this.username = username;
		this.rating = rating;
		this.comment = comment;
		this.facilityId = facilityId;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public int getRating() {
		return rating;
	}

	public void setRating(int rating) {
		this.rating = rating;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public Long getFacilityId() {
		return facilityId;
	}

	public void setFacilityId(Long facilityId) {
		this.facilityId = facilityId;
	}
	
	

}
