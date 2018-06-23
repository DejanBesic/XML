package com.booking.app.DTOs;

public class UserResponse {
	
	private String email;
	
	private String name;
	
	private String lastName;
	
	private String username;
	
	private String address;

	public UserResponse() {
		
	}
	
	public UserResponse(String email, String name, String lastName, String username, String address) {
		super();
		this.email = email;
		this.name = name;
		this.lastName = lastName;
		this.username = username;
		this.address = address;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}
	
	
}
