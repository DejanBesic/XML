package com.booking.app.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Rating implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable = false)
	private int rating;
	
	@Column(nullable = true)
	private String comment;
	
	@ManyToOne(optional = false)
	private User user;
	
	@ManyToOne(optional = false)
	private Facility facility;
	
	@Column(nullable = false)
	private boolean reviewed;
	
	@Column(nullable = false)
	private boolean approved;
	
	public Rating() {
		
	}

	public Rating(int rating, String comment, User user, Facility facility, boolean reviewed, boolean approved) {
		super();
		this.rating = rating;
		this.comment = comment;
		this.user = user;
		this.facility = facility;
		this.reviewed = reviewed;
		this.approved = approved;
	}
	
	public boolean isApproved() {
		return approved;
	}

	public void setApproved(boolean approved) {
		this.approved = approved;
	}

	public boolean isReviewed() {
		return reviewed;
	}

	public void setReviewed(boolean reviewed) {
		this.reviewed = reviewed;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public int getRating() {
		return rating;
	}

	public void setRating(int rating) {
		this.rating = rating;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Facility getFacility() {
		return facility;
	}

	public void setFacility(Facility facility) {
		this.facility = facility;
	}
	
	

}
