package com.booking.app.DTOs;

import java.util.ArrayList;
import java.util.List;

import com.booking.app.model.Message;
import com.booking.app.model.Reservation;

public final class MappReservation {

	public static ReservationDTO mappReservation(Reservation res) {
		return new ReservationDTO(res.getId(),res.getGuest().getName()+" "+res.getGuest().getLastName(),res.getGuest().getUsername(),res.getFacility().getName(),res.getFromDate(),res.getToDate());
	}
	
	public static List<ReservationDTO> mappReservations(List<Reservation> ress){
		List<ReservationDTO> list = new ArrayList<ReservationDTO>();
		for(Reservation m : ress) {
			list.add(mappReservation(m));
		}
		return list;
	}
}
