package com.booking.app.DTOs;

public class MessageRequest {

	private String message;
	
	private Long reservationId;

	public MessageRequest() {
		
	}
	
	public MessageRequest(String message, Long reservationId) {
		this.message = message;
		this.reservationId = reservationId;
	}
	
	public Long getReservationId() {
		return reservationId;
	}

	public void setReservationId(Long reservationId) {
		this.reservationId = reservationId;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
	
	
}
