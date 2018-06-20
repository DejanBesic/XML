package com.booking.app.DTOs;

import java.sql.Date;

import com.booking.app.model.Facility;

public class SearchDTO {

	Facility facility;
	
	int price;
	
	Date startDate;
	
	Date endDate;

	public SearchDTO(Facility facility, int price, Date startDate, Date endDate) {
		super();
		this.facility = facility;
		this.price = price;
		this.startDate = startDate;
		this.endDate = endDate;
	}

	public Facility getFacility() {
		return facility;
	}

	public void setFacility(Facility facility) {
		this.facility = facility;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
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
