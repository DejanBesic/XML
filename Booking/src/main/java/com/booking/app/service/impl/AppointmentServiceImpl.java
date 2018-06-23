package com.booking.app.service.impl;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.booking.app.DTOs.SearchDTO;
import com.booking.app.DTOs.SearchRequest;
import com.booking.app.model.Appointment;
import com.booking.app.model.Facility;
import com.booking.app.model.Rating;
import com.booking.app.model.Reservation;
import com.booking.app.repository.AppointmentRepository;
import com.booking.app.service.AppointmentService;

@Service
public class AppointmentServiceImpl implements AppointmentService{

	@Autowired
	AppointmentRepository appointmentRepository;
	
	@Autowired
	ReservationServiceImpl reservationService;
	
	@Autowired
	FacilityServiceImpl facilityService;
	
	@Autowired
	RatingServiceImpl ratingService;
	
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
		Date start = searchRequest.getStartDate();
		Date end = searchRequest.getEndDate();
		
		List<SearchDTO> searchList = new ArrayList<SearchDTO>(); 
		for (Appointment a : appointments) {
			
			int startDate = start.compareTo(a.getFromDate()); 
			int endDate = end.compareTo(a.getToDate());
			
			List<Reservation> reservations = reservationService.findByFacility(a.getFacility());
			boolean permission = true;
			for (Reservation r : reservations) {
				int fromDate1 = r.getFromDate().compareTo(start);
				int fromDate2 = r.getFromDate().compareTo(end);
				
				int toDate1 = r.getToDate().compareTo(start);
				int toDate2 = r.getToDate().compareTo(end);
				
				if ((fromDate1 >= 0 && fromDate2 <= 0 ) || (toDate1 >= 0 && toDate2 <= 0)) {
					permission = false;
				}
			}
			
			
//			int startEndDate = start.compareTo(a.getToDate());
			
			//ako se nalazi unutar jednog termina
			if (startDate >= 0 && endDate <= 0 && permission) {
				price = (int) (a.getPrice() * ((int)((end.getTime()/dayMili) - (start.getTime()/dayMili))));
				List<Rating> ratings = ratingService.findByFacility(a.getFacility());
				double averageRating = 0;
				for (Rating r : ratings) {
					averageRating += r.getRating();
				}
				if (averageRating != 0) {
					averageRating /= ratings.size();	
				}
				
				searchList.add(new SearchDTO(a.getFacility(), price, start, end, averageRating));				
			}
			//ako se pocetak nalazi unutar jednog termina a kraj unutar drugog
//			else if (startDate >= 0 && endDate > 0 && startEndDate <= 0) {
//				//oduzimam od tekuceg appointmenta - datum pocetka requesta
//				price += (int) (a.getPrice() * ((int)((a.getToDate().getTime()/dayMili) - (start.getTime()/dayMili))));
//				Date newStartDate = new Date(a.getToDate().getTime() + dayMili);
//				
//				for (Appointment app : appointments) {
//					if (a.getFacility() == app.getFacility() && !searchList.stream().anyMatch(search -> search.getFacility() == app.getFacility()) && a != app){
//						
//						endDate = end.compareTo(app.getToDate());
//						startDate = newStartDate.compareTo(app.getFromDate());
//						startEndDate = newStartDate.compareTo(app.getToDate());
//						
//						//kraj pretrage kada udje ovde, ako ne udje nece biti dodat u listu i nece biti prikazan
//						if (startDate >=0 && endDate <= 0) {
//							price += (int) (app.getPrice() * ((int)(((end.getTime()+dayMili)/dayMili) - (newStartDate.getTime()/dayMili))));
//							searchList.add(new SearchDTO(app.getFacility(), price, start, end));
//						}
//						
//						//nastavlja pretragu
//						if (startDate >= 0 && endDate > 0 && startEndDate <= 0) {
//							price += (int) (app.getPrice() * ((int)(((app.getToDate().getTime()+dayMili)/dayMili) - (newStartDate.getTime()/dayMili))));
//							newStartDate = new Date(app.getToDate().getTime() + dayMili);
//						}
//					}
//				}
//			}
		}
		
		return searchList;
	}
}
