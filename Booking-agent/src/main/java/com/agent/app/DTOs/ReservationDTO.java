package com.agent.app.DTOs;

import java.util.Date;

public class ReservationDTO {

	private Long id;
	private String guestName;
	private String guestUsername;
	private String facility;
	private Date fromDate;
	private Date toDate;
	private boolean confirmed;
	
	public ReservationDTO(){
		
	}
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getGuestName() {
		return guestName;
	}
	public void setGuestName(String guestName) {
		this.guestName = guestName;
	}
	public String getGuestUsername() {
		return guestUsername;
	}
	public void setGuestUsername(String guestUsername) {
		this.guestUsername = guestUsername;
	}
	public String getFacility() {
		return facility;
	}
	public void setFacility(String facility) {
		this.facility = facility;
	}
	public Date getFromDate() {
		return fromDate;
	}
	public void setFromDate(Date fromDate) {
		this.fromDate = fromDate;
	}
	public Date getToDate() {
		return toDate;
	}
	public void setToDate(Date toDate) {
		this.toDate = toDate;
	}
	public ReservationDTO(Long id, String guestName, String guestUsername, String facility, Date fromDate,
			Date toDate) {
		super();
		this.id = id;
		this.guestName = guestName;
		this.guestUsername = guestUsername;
		this.facility = facility;
		this.fromDate = fromDate;
		this.toDate = toDate;
	}
	public boolean isConfirmed() {
		return confirmed;
	}
	public void setConfirmed(boolean confirmed) {
		this.confirmed = confirmed;
	}
	
}

