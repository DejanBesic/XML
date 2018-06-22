package com.booking.app.DTOs;

public class UserRequest {

	private String email;
	
	private String name;
	
	private String lastName;
	
	private String address;
	
	private String oldPassword;
	
	private String newPassword;
	
	private String confirmPassword;

	public UserRequest() {
		
	}
	
	public UserRequest(String email, String name, String lastName, String address, String oldPassword,
			String newPassword, String confirmPassword) {
		super();
		this.email = email;
		this.name = name;
		this.lastName = lastName;
		this.address = address;
		this.oldPassword = oldPassword;
		this.newPassword = newPassword;
		this.confirmPassword = confirmPassword;
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

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getOldPassword() {
		return oldPassword;
	}

	public void setOldPassword(String oldPassword) {
		this.oldPassword = oldPassword;
	}

	public String getNewPassword() {
		return newPassword;
	}

	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}

	public String getConfirmPassword() {
		return confirmPassword;
	}

	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}
	
	
}
