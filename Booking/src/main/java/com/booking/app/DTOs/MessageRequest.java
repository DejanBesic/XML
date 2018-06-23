package com.booking.app.DTOs;

public class MessageRequest {

	private String message;
	
	private Long reciverId;

	public MessageRequest() {
		
	}
	
	public MessageRequest(String message, Long reservationId) {
		this.message = message;
		this.reciverId = reservationId;
	}
	
	public Long getReciverId() {
		return reciverId;
	}

	public void setReciverId(Long reciverId) {
		this.reciverId = reciverId;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
	
	
}
