package com.booking.app.service;

import java.util.List;

import com.booking.app.DTOs.SearchDTO;
import com.booking.app.DTOs.SearchRequest;
import com.booking.app.model.Appointment;

public interface AppointmentService {

	Appointment findById(Long id);
	
	List<Appointment> findAll();
	
	Appointment save(Appointment appointment);
	
	void delete(Long id);

	List<SearchDTO> findBySearch(SearchRequest searchRequest);
	
}
