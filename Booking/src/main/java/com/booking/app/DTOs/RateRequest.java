package com.booking.app.DTOs;

public class RateRequest {
	
	private Long id;
	
	private String comment;
	
	private int rating;
	
	public RateRequest() {
		
	}

	public RateRequest(Long id, String comment, int rating) {
		super();
		this.id = id;
		this.comment = comment;
		this.rating = rating;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public int getRating() {
		return rating;
	}

	public void setRating(int rating) {
		this.rating = rating;
	}
	
	

}
