package com.agent.app.controller;

import java.security.Principal;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.agent.app.model.Facility;
import com.agent.app.ws.WSFacilityClient;
import com.agent.app.wsdl.AgentFacilitiesRequest;
import com.agent.app.wsdl.AgentFacilitiesResponse;
import com.agent.app.wsdl.AppointmentWS;
import com.agent.app.wsdl.FacilityWS;
import com.agent.app.wsdl.MessageResponse;
import com.agent.app.wsdl.NewFacilityRequest;
import com.agent.app.wsdl.UserWS;


@RestController
@RequestMapping("/api/facility")
public class FacilityController {

	@Autowired
	WSFacilityClient client;
	
	@GetMapping(value="/getFacilities")
    public ResponseEntity<?> getFacilities() {
    
		AgentFacilitiesRequest user = new AgentFacilitiesRequest();
		String username = SecurityContextHolder.getContext().getAuthentication().getName();
		
		
		AgentFacilitiesResponse response = client.getAgentFacilities(user);
		if(response == null)
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		
    	return new ResponseEntity<>(response.getFacilityWS(), HttpStatus.OK);
    }
	
//	@RequestMapping(method=RequestMethod.POST, consumes="application/json")
//	public ResponseEntity<?> addFacility(@RequestBody NewFacilityRequest facility){
//		MessageResponse response = client.addNewFacility(facility);
//		
//		return new ResponseEntity<>(response, HttpStatus.OK);
//	}
	
	@RequestMapping(method=RequestMethod.POST)
	public ResponseEntity<?> addFacility() throws DatatypeConfigurationException{
		
		NewFacilityRequest res = new NewFacilityRequest();
		res.setName("novii");
		res.setCategory(1);
		res.setOwner("twiste");
		res.setType("House");
		res.setDescription("opis novogg");
		res.setAddress("adresa novig");
		res.setLocation("Miami");
		res.setParkingLot(true);
		res.setBathroom(true);
		res.setWifi(false);
		res.setBreakfast(true);
		res.setHalfBoard(true);
		res.setFullBoard(false);
		res.setKitchen(false);
		res.setTv(true);
		res.setNumberOfPeople(4);
		
		GregorianCalendar c = new GregorianCalendar();
		c.setTime(new Date());
		
		AppointmentWS app = new AppointmentWS();
		app.setFromDate(DatatypeFactory.newInstance().newXMLGregorianCalendar(c));
		app.setToDate(DatatypeFactory.newInstance().newXMLGregorianCalendar(c));
		app.setPrice(100.0);
		
		AppointmentWS app2 = new AppointmentWS();
		app2.setFromDate(DatatypeFactory.newInstance().newXMLGregorianCalendar(c));
		app2.setToDate(DatatypeFactory.newInstance().newXMLGregorianCalendar(c));
		app2.setPrice(200.0);
		app2.setFacility("novii");
		
		
		res.getAppointmentWS().add(app);
		res.getAppointmentWS().add(app2);
		
		MessageResponse response = client.addNewFacility(res);
		
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
}
