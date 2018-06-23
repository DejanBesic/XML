package com.agent.app.controller;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.agent.app.DTOs.FacilityDTO;
import com.agent.app.ws.WSFacilityClient;
import com.agent.app.wsdl.AgentFacilitiesRequest;
import com.agent.app.wsdl.AgentFacilitiesResponse;
import com.agent.app.wsdl.AppointmentWS;
import com.agent.app.wsdl.ImagesWS;
import com.agent.app.wsdl.MessageResponse;
import com.agent.app.wsdl.NewFacilityRequest;
import com.booking.app.DTOs.MessageDTO;


@RestController
@RequestMapping("/api/facility")
public class FacilityController {

	@Autowired
	WSFacilityClient client;
	
	@GetMapping(value="/getFacilities")
    public ResponseEntity<?> getFacilities() {
    
		AgentFacilitiesRequest user = new AgentFacilitiesRequest();
		String username = SecurityContextHolder.getContext().getAuthentication().getName();
		user.setUsername(username);
		
		AgentFacilitiesResponse response = client.getAgentFacilities(user);
		if(response == null)
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		
    	return new ResponseEntity<>(response.getFacilityWS(), HttpStatus.OK);
    }
	
	@GetMapping(value="/getMessages")
    public ResponseEntity<?> getMessages() {
    
		MessageDTO msg = new MessageDTO(1L,1L,2L,"rec","sen","recn","senn","WAZZZA",new Date());
		MessageDTO msg1 = new MessageDTO(2L,1L,2L,"rec","sen","recn","senn","WAZZZA2",new Date());
		MessageDTO msg2 = new MessageDTO(3L,1L,2L,"rec","sen","recn","senn","WAZZZA3",new Date());
		
		List<MessageDTO> list = new ArrayList<MessageDTO>();
		list.add(msg);
		list.add(msg1);
		list.add(msg2);
    	return new ResponseEntity<>(list, HttpStatus.OK);
    }
	
//	@RequestMapping(method=RequestMethod.POST, consumes="application/json")
//	public ResponseEntity<?> addFacility(@RequestBody NewFacilityRequest facility){
//		MessageResponse response = client.addNewFacility(facility);
//		
//		return new ResponseEntity<>(response, HttpStatus.OK);
//	}
	
	@RequestMapping(value="/addNewFacility", method=RequestMethod.POST)
	public ResponseEntity<?> addFacility(@ModelAttribute FacilityDTO facility) throws DatatypeConfigurationException, ParseException, IOException{
		
		GregorianCalendar date1start = new GregorianCalendar();
		GregorianCalendar date1end = new GregorianCalendar();
		GregorianCalendar date2start = new GregorianCalendar();
		GregorianCalendar date2end = new GregorianCalendar();
		GregorianCalendar date3start = new GregorianCalendar();
		GregorianCalendar date3end = new GregorianCalendar();
		GregorianCalendar date4start = new GregorianCalendar();
		GregorianCalendar date4end = new GregorianCalendar();
		DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

		date1start.setTime(sdf.parse("2018-01-01"));
		date1end.setTime(sdf.parse("2018-03-31"));
		date2start.setTime(sdf.parse("2018-04-01"));
		date2end.setTime(sdf.parse("2018-06-30"));
		date3start.setTime(sdf.parse("2018-07-01"));
		date3end.setTime(sdf.parse("2018-09-30"));
		date4start.setTime(sdf.parse("2018-10-01"));
		date4end.setTime(sdf.parse("2018-12-31"));
		
		NewFacilityRequest req = facilityDTO2WS(facility);
		
		AppointmentWS app1 = req.getAppointmentWS().get(0);
		app1.setFromDate(DatatypeFactory.newInstance().newXMLGregorianCalendar(date1start));
		app1.setToDate(DatatypeFactory.newInstance().newXMLGregorianCalendar(date1end));
		app1.setFacility(facility.getName());
		
		AppointmentWS app2 = req.getAppointmentWS().get(1);
		app2.setFromDate(DatatypeFactory.newInstance().newXMLGregorianCalendar(date2start));
		app2.setToDate(DatatypeFactory.newInstance().newXMLGregorianCalendar(date2end));
		app2.setFacility(facility.getName());
		
		AppointmentWS app3 = req.getAppointmentWS().get(2);
		app3.setFromDate(DatatypeFactory.newInstance().newXMLGregorianCalendar(date3start));
		app3.setToDate(DatatypeFactory.newInstance().newXMLGregorianCalendar(date3end));
		app3.setFacility(facility.getName());

		AppointmentWS app4 = req.getAppointmentWS().get(3);
		app4.setFromDate(DatatypeFactory.newInstance().newXMLGregorianCalendar(date4start));
		app4.setToDate(DatatypeFactory.newInstance().newXMLGregorianCalendar(date4end));
		app4.setFacility(facility.getName());
		
		MessageResponse response = client.addNewFacility(req);
		
		if(response==null)
			return new ResponseEntity<>("Please fill every field", HttpStatus.BAD_REQUEST);
		
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
	
	
	private NewFacilityRequest facilityDTO2WS(FacilityDTO dto) throws IOException{
		NewFacilityRequest res = new NewFacilityRequest();
		
		res.setName(dto.getName());
		res.setAddress(dto.getAddress());
		res.setDescription(dto.getDescription());
		res.setLocation(dto.getLocation());
		res.setNumberOfPeople(dto.getNumberOfPeople());
		res.setCategory(dto.getCategory());
		res.setBathroom(dto.isBathroom());
		res.setBreakfast(dto.isBreakfast());
		res.setFullBoard(dto.isFullBoard());
		res.setHalfBoard(dto.isHalfBoard());
		res.setKitchen(dto.isKitchen());
		res.setOwner(SecurityContextHolder.getContext().getAuthentication().getName());
		res.setParkingLot(dto.isParkingLot());
		res.setTv(dto.isTv());
		res.setType(dto.getType());
		res.setWifi(dto.isWifi());
		res.setImagesWS(new ImagesWS());
		
		
		AppointmentWS app1 = new AppointmentWS();
		AppointmentWS app2 = new AppointmentWS();
		AppointmentWS app3 = new AppointmentWS();
		AppointmentWS app4 = new AppointmentWS();
		
		app1.setPrice(dto.getApp1());
		app2.setPrice(dto.getApp2());
		app3.setPrice(dto.getApp3());
		app4.setPrice(dto.getApp4());
		
		res.getAppointmentWS().add(app1);
		res.getAppointmentWS().add(app2);
		res.getAppointmentWS().add(app3);
		res.getAppointmentWS().add(app4);
		
		for(MultipartFile img : dto.getImages()){
			res.getImagesWS().getImages().add(img.getBytes());
		}
		
		
		return res;
	}
}
