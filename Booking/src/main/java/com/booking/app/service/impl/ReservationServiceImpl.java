package com.booking.app.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.booking.app.model.Facility;
import com.booking.app.model.Reservation;
import com.booking.app.model.User;
import com.booking.app.repository.ReservationRepository;
import com.booking.app.service.ReservationService;

@Service
public class ReservationServiceImpl implements ReservationService{

	@Autowired
	ReservationRepository reservationRepository;

	@Override
	public Reservation findOne(Long id) {
		return reservationRepository.findOne(id);
	}

	@Override
	public List<Reservation> findAll() {
		return reservationRepository.findAll();
	}

	@Override
	public Reservation save(Reservation reservation) {
		for (Reservation r : reservationRepository.findByFacility(reservation.getFacility())) {
  			int startDate1 = reservation.getFromDate().compareTo(r.getFromDate());
  			int startDate2 = reservation.getFromDate().compareTo(r.getToDate());
  			
  			int endDate1 = reservation.getToDate().compareTo(r.getFromDate());
  			int endDate2 = reservation.getToDate().compareTo(r.getToDate());
  			
  			if ((startDate1 >= 0 && startDate2 <=0 ) || (endDate1 >= 0 && endDate2 <= 0)) {
  				return null;
  			}
  		}
		
		return reservationRepository.save(reservation);
	}

	@Override
	public void delete(Long id) {
		reservationRepository.delete(id);
	}

	@Override
	public List<Reservation> findByFacility(Facility facility) {
		return reservationRepository.findByFacility(facility);
	}

	@Override
	public List<Reservation> findByGuest(User guest) {
		return reservationRepository.findByGuest(guest);
	}
	
	@Override
	public List<Reservation> findByGuestAndFacility(User guest, Facility facility) {
		return reservationRepository.findByGuestAndFacility(guest, facility);
	}

	@Override
	public Reservation update(Reservation reservation) {
		// TODO Auto-generated method stub
		return reservationRepository.save(reservation);
	}
	
}
