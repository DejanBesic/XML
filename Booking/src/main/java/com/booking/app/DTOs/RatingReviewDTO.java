package com.booking.app.DTOs;

public class RatingReviewDTO {

	private Long id;
	private String usersName;
	private String facilityName;
	private String comment;
	private Long userID;
	
	public RatingReviewDTO(Long id, String usersName, String facilityName, String comment, Long userID) {
		super();
		this.id = id;
		this.usersName = usersName;
		this.facilityName = facilityName;
		this.comment = comment;
		this.userID = userID;
	}
	public Long getUserID() {
		return userID;
	}
	public void setUserID(Long userID) {
		this.userID = userID;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getUsersName() {
		return usersName;
	}
	public void setUsersName(String usersName) {
		this.usersName = usersName;
	}
	public String getFacilityName() {
		return facilityName;
	}
	public void setFacilityName(String facilityName) {
		this.facilityName = facilityName;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
}
