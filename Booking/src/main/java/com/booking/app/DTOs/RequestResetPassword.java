package com.booking.app.DTOs;

public class RequestResetPassword {

	String email;
	
	public RequestResetPassword() {
		
	}

	public RequestResetPassword(String email) {
		super();
		this.email = email;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
}
