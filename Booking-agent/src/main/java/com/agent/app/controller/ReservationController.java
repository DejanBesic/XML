package com.agent.app.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.agent.app.DTOs.FacilityDTO;
import com.agent.app.DTOs.ReservationDTO;
import com.agent.app.ws.WSReservationClient;
import com.agent.app.wsdl.UnavailableReservationRequest;

@RestController
@RequestMapping("/api/reservation")
public class ReservationController {

	@Autowired
	WSReservationClient reservationClient;
	
	@RequestMapping(value="/confirm/{id}", method=RequestMethod.PUT)
	public ResponseEntity<?> confirmReservation(@PathVariable Long id){
		boolean confirmed = reservationClient.confirm(id);
		
		if(!confirmed)
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@RequestMapping(value="/unavailable", method=RequestMethod.POST)
	public ResponseEntity<?> addUnavailable(@RequestBody ReservationDTO dto) throws DatatypeConfigurationException, ParseException{
		UnavailableReservationRequest request = new UnavailableReservationRequest();
		System.out.println(dto);
		request.setFacilityId(dto.getFacilityId());
		GregorianCalendar from = new GregorianCalendar();
		Date d = new SimpleDateFormat("yyyy-MM-dd").parse(dto.getFromDateString());
		from.setTime(d);
		request.setFrom(DatatypeFactory.newInstance().newXMLGregorianCalendar(from));
		

		GregorianCalendar to = new GregorianCalendar();
		Date d2 = new SimpleDateFormat("yyyy-MM-dd").parse(dto.getToDateString());
		to.setTime(d2);
		request.setTo(DatatypeFactory.newInstance().newXMLGregorianCalendar(to));
		
		boolean response = reservationClient.setUnavailable(request);
		
		if(response)
			return new ResponseEntity<>(response, HttpStatus.OK);
		
		return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
	}
}
