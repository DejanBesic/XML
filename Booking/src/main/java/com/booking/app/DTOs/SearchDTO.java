package com.booking.app.DTOs;

import java.sql.Date;
import java.util.List;

import com.booking.app.model.Facility;

public class SearchDTO {

	Facility facility;
	
	int price;
	
	Date startDate;
	
	Date endDate;
	
	double rating;
	
	List<byte[]> images;

	public SearchDTO(Facility facility, int price, Date startDate, Date endDate, double rating, List<byte[]> images) {
		super();
		this.facility = facility;
		this.price = price;
		this.startDate = startDate;
		this.endDate = endDate;
		this.rating = rating;
		this.images = images;
	}
	
	
	public List<byte[]> getImages() {
		return images;
	}

	public void setImages(List<byte[]> images) {
		this.images = images;
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
