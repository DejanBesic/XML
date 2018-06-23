package com.booking.app.DTOs;

import java.util.Date;

public class MessageDTO {

	private Long id;
	private Long reciverID;
	private Long senderID;
	private String reciverUsername;
	private String senderUsername;
	private String reciverName;
	private String senderName;
	private String message;
	private Date date;
	public MessageDTO(Long id, Long reciverID, Long senderID, String reciverUsername, String senderUsername,
			String reciverName, String senderName, String message, Date date) {
		super();
		this.id = id;
		this.reciverID = reciverID;
		this.senderID = senderID;
		this.reciverUsername = reciverUsername;
		this.senderUsername = senderUsername;
		this.reciverName = reciverName;
		this.senderName = senderName;
		this.message = message;
		this.date = date;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getReciverID() {
		return reciverID;
	}
	public void setReciverID(Long reciverID) {
		this.reciverID = reciverID;
	}
	public Long getSenderID() {
		return senderID;
	}
	public void setSenderID(Long senderID) {
		this.senderID = senderID;
	}
	public String getReciverUsername() {
		return reciverUsername;
	}
	public void setReciverUsername(String reciverUsername) {
		this.reciverUsername = reciverUsername;
	}
	public String getSenderUsername() {
		return senderUsername;
	}
	public void setSenderUsername(String senderUsername) {
		this.senderUsername = senderUsername;
	}
	public String getReciverName() {
		return reciverName;
	}
	public void setReciverName(String reciverName) {
		this.reciverName = reciverName;
	}
	public String getSenderName() {
		return senderName;
	}
	public void setSenderName(String senderName) {
		this.senderName = senderName;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
}
