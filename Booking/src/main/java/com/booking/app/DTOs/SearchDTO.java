package com.booking.app.DTOs;

import java.sql.Date;

import com.booking.app.model.Facility;

public class SearchDTO {

	Facility facility;
	
	int price;
	
	Date startDate;
	
	Date endDate;
	
	double rating;

	public SearchDTO(Facility facility, int price, Date startDate, Date endDate, double rating) {
		super();
		this.facility = facility;
		this.price = price;
		this.startDate = startDate;
		this.endDate = endDate;
		this.rating = rating;
	}

	
	
	public double getRating() {
		return rating;
	}



	public void setRating(double rating) {
		this.rating = rating;
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
