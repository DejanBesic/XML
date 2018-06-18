package com.agent.app.model;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Facility {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable = false)
	private String name;
	
	@Column(nullable = false)
	private int Category;
	
	@Column(nullable = true)
	private String description;
	
	@Column(nullable = false)
	private String address;
	
	@Column(nullable = false)
	private boolean parkingLot;
	
	@Column(nullable = false)
	private boolean wifi;
	
	@Column(nullable = false)
	private boolean breakfast;
	
	@Column(nullable = false)
	private boolean halfBoard;
	
	public boolean isParkingLot() {
		return parkingLot;
	}

	public void setParkingLot(boolean parkingLot) {
		this.parkingLot = parkingLot;
	}

	public boolean isWifi() {
		return wifi;
	}

	public void setWifi(boolean wifi) {
		this.wifi = wifi;
	}

	public boolean isBreakfast() {
		return breakfast;
	}

	public void setBreakfast(boolean breakfast) {
		this.breakfast = breakfast;
	}

	public boolean isHalfBoard() {
		return halfBoard;
	}

	public void setHalfBoard(boolean halfBoard) {
		this.halfBoard = halfBoard;
	}

	public boolean isFullBoard() {
		return fullBoard;
	}

	public void setFullBoard(boolean fullBoard) {
		this.fullBoard = fullBoard;
	}

	public boolean isTv() {
		return tv;
	}

	public void setTv(boolean tv) {
		this.tv = tv;
	}

	public boolean isKitchen() {
		return kitchen;
	}

	public void setKitchen(boolean kitchen) {
		this.kitchen = kitchen;
	}

	public boolean isBathroom() {
		return bathroom;
	}

	public void setBathroom(boolean bathroom) {
		this.bathroom = bathroom;
	}


	@Column(nullable = false)
	private boolean fullBoard;
	
	@Column(nullable = false)
	private boolean tv;
	
	@Column(nullable = false)
	private boolean kitchen;
	
	@Column(nullable = false)
	private boolean bathroom;	
	
	@Column(nullable = false)
	private int numberOfPepople;
	
	public Facility() {
		
	}

	public Facility(String name, int category,
			String description, String address, int numberOfPepople) {
		super();
		this.name = name;
		Category = category;
		this.description = description;
		this.address = address;
		this.numberOfPepople = numberOfPepople;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public int getCategory() {
		return Category;
	}


	public void setCategory(int category) {
		Category = category;
	}


	public String getDescription() {
		return description;
	}


	public void setDescription(String description) {
		this.description = description;
	}


	public String getAddress() {
		return address;
	}


	public void setAddress(String address) {
		this.address = address;
	}


	public int getNumberOfPepople() {
		return numberOfPepople;
	}


	public void setNumberOfPepople(int numberOfPepople) {
		this.numberOfPepople = numberOfPepople;
	}


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}
	
}
	
