package com.booking.app.DTOs;

import java.sql.Date;

public class ReservationRequest {

	
	Long facilityId;
	
	Date startDate;
	
	Date endDate;
	
	int price;
	
	public ReservationRequest() {
		
	}
	
	

	public ReservationRequest(Long facilityId, Date startDate, Date endDate, int price) {
		super();
		this.facilityId = facilityId;
		this.startDate = startDate;
		this.endDate = endDate;
		this.price = price;
	}


	

	public int getPrice() {
		return price;
	}



	public void setPrice(int price) {
		this.price = price;
	}



	public Long getFacilityId() {
		return facilityId;
	}

	public void setFacilityId(Long facilityId) {
		this.facilityId = facilityId;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	
	
	
}
