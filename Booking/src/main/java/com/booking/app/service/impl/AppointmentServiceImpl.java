package com.booking.app.service.impl;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.booking.app.DTOs.SearchDTO;
import com.booking.app.DTOs.SearchRequest;
import com.booking.app.DTOs.SearchResponse;
import com.booking.app.model.Appointment;
import com.booking.app.model.Facility;
import com.booking.app.model.Image;
import com.booking.app.repository.AppointmentRepository;
import com.booking.app.service.AppointmentService;

@Service
public class AppointmentServiceImpl implements AppointmentService{

	@Autowired
	AppointmentRepository appointmentRepository;
	
	@Autowired
	FacilityServiceImpl facilityService;
	
	@Override
	public Appointment findById(Long id) {
		return appointmentRepository.findOne(id);
	}

	@Override
	public List<Appointment> findAll() {
		return appointmentRepository.findAll();
	}

	@Override
	public Appointment save(Appointment appointment) {
		return appointmentRepository.save(appointment);
	}

	@Override
	public void delete(Long id) {
		appointmentRepository.delete(id);
		
	}


	@Override
	public List<SearchDTO> findBySearch(SearchRequest searchRequest) {
		List<Facility> facilities = facilityService.filterFacility(searchRequest);
		List<Appointment> appointments =  appointmentRepository.findAll().stream()
				.filter(appointment -> {
					if (facilities.contains(appointment.getFacility())) {
						return true;
					}
					return false;
				})
				.sorted((a1, a2) -> a1.getFromDate().compareTo(a2.getFromDate()))
				.collect(Collectors.toList());
		
		int price = 0;
		long dayMili = 86400000;
		List<SearchDTO> searchList = new ArrayList<SearchDTO>(); 
		for (Appointment a : appointments) {
			int startDate =searchRequest.getStartDate().compareTo(a.getFromDate()); 
			int endDate = searchRequest.getEndDate().compareTo(a.getToDate());
			
			//ako se nalazi unutar jednog termina
			if (startDate >= 0 && endDate <= 0) {
				price = (int) (a.getPrice() * ((int)((searchRequest.getEndDate().getTime()/dayMili) - (searchRequest.getStartDate().getTime()/dayMili))));
				searchList.add(new SearchDTO(a.getFacility(), price, searchRequest.getStartDate(), searchRequest.getEndDate()));
			//ako se pocetak nalazi unutar jednog termina a kraj unutar drugog
			} else if (startDate >= 0 && endDate > 0){
				//oduzimam od tekuceg appointmenta - datum pocetka requesta
				price = (int) (a.getPrice() * ((int)((a.getToDate().getTime()/dayMili) - (searchRequest.getStartDate().getTime()/dayMili))));
				Date newStartDate = new Date(a.getToDate().getTime() + dayMili);
				for (Appointment app : appointments) {
					if (a.getFacility() == app.getFacility() && !searchList.stream().anyMatch(search -> search.getFacility() == app.getFacility())){
						
						endDate = searchRequest.getEndDate().compareTo(a.getToDate());
						startDate = newStartDate.compareTo(app.getFromDate());
			
						//kraj pretrage kada udje ovde, ako ne udje nece biti dodat u listu i nece biti prikazan
						if (startDate >=0 && endDate <= 0) {
							price = (int) (app.getPrice() * ((int)((searchRequest.getEndDate().getTime()/dayMili) - (newStartDate.getTime()/dayMili))));
							searchList.add(new SearchDTO(app.getFacility(), price, searchRequest.getStartDate(), searchRequest.getEndDate()));
						}
						
						//nastavlja pretragu
						if (startDate >= 0 && endDate > 0) {
							price = (int) (app.getPrice() * ((int)((app.getToDate().getTime()/dayMili) - (newStartDate.getTime()/dayMili))));
							newStartDate = new Date(app.getToDate().getTime() + dayMili);
						}
					}
				}
			}
		}
		
		return searchList;
	}
	
	private List<SearchResponse> appointmentsToSearchResponses(List<Appointment> appointments, Date startDate, Date endDate){
		List<SearchResponse> responses = new ArrayList<SearchResponse>();
		for (Appointment a : appointments) {
			responses.add(new SearchResponse(a, new ArrayList<Image>(), startDate, endDate));
		}
		return responses;
	}

}
