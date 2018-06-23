package com.booking.app.model;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Reservation implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne(optional = false)
	private User guest;
	
	@ManyToOne(optional = false)
	private Facility facility;
	
	@Column(nullable = false)
	private Date fromDate;
	
	@Column(nullable = false)
	private Date toDate;
	
	@Column(nullable = false)
	private int price;
	
	@Column(nullable = false)
	private boolean confirmed;

	public Reservation() {
		
	}
	
	public Reservation(User guest, Facility facility, Date fromDate, Date toDate, int price, boolean confirmed) {
		super();
		this.guest = guest;
		this.facility = facility;
		this.fromDate = fromDate;
		this.toDate = toDate;
		this.price = price;
		this.confirmed = confirmed;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public User getGuest() {
		return guest;
	}

	public void setGuest(User guest) {
		this.guest = guest;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public Facility getFacility() {
		return facility;
	}

	public void setFacility(Facility facility) {
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

	public boolean isConfirmed() {
		return confirmed;
	}

	public void setConfirmed(boolean confirmed) {
		this.confirmed = confirmed;
	}	
	
	

	
}
