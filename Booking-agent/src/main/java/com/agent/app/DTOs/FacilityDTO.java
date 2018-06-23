package com.agent.app.DTOs;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.agent.app.wsdl.AppointmentWS;

public class FacilityDTO {


    protected String name;
    protected int category;
    protected String owner;
    protected String type;
    protected String description;
    protected String address;
    protected String location;
    protected boolean parkingLot;
    protected boolean wifi;
    protected boolean breakfast;
    protected boolean halfBoard;
    protected boolean fullBoard;
    protected boolean tv;
    protected boolean kitchen;
    protected boolean bathroom;
    protected int numberOfPeople;
    protected double app1, app2, app3, app4;
    protected List<MultipartFile> images;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getCategory() {
		return category;
	}
	public void setCategory(int category) {
		this.category = category;
	}
	public String getOwner() {
		return owner;
	}
	public void setOwner(String owner) {
		this.owner = owner;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
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
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
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
	public int getNumberOfPeople() {
		return numberOfPeople;
	}
	public void setNumberOfPeople(int numberOfPeople) {
		this.numberOfPeople = numberOfPeople;
	}
	
	public List<MultipartFile> getImages() {
		return images;
	}
	public void setImages(List<MultipartFile> images) {
		this.images = images;
	}
	public double getApp1() {
		return app1;
	}
	public void setApp1(double app1) {
		this.app1 = app1;
	}
	public double getApp2() {
		return app2;
	}
	public void setApp2(double app2) {
		this.app2 = app2;
	}
	public double getApp3() {
		return app3;
	}
	public void setApp3(double app3) {
		this.app3 = app3;
	}
	public double getApp4() {
		return app4;
	}
	public void setApp4(double app4) {
		this.app4 = app4;
	}

}
