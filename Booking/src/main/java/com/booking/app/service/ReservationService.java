package com.booking.app.service;

import java.util.List;

import com.booking.app.model.Facility;
import com.booking.app.model.Reservation;
import com.booking.app.model.User;

public interface ReservationService {

	Reservation findOne(Long id);
	
	List<Reservation> findAll();
	
	Reservation save(Reservation reservation);
	
	void delete(Long id);
	
	List<Reservation> findByFacility(Facility facility);
	
	List<Reservation> findByGuest(User guest);

	List<Reservation> findByGuestAndFacility(User guest, Facility facility);
}
