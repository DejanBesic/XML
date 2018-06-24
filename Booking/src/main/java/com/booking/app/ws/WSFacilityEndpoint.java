package com.booking.app.ws;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import com.booking.app.model.Appointment;
import com.booking.app.model.Facility;
import com.booking.app.model.Image;
import com.booking.app.model.User;
import com.booking.app.service.AppointmentService;
import com.booking.app.service.FacilityService;
import com.booking.app.service.FacilityTypeService;
import com.booking.app.service.ImageService;
import com.booking.app.service.LocationService;
import com.booking.app.service.UserService;
import com.xml.booking.backendmain.ws_classes.AgentFacilitiesRequest;
import com.xml.booking.backendmain.ws_classes.AgentFacilitiesResponse;
import com.xml.booking.backendmain.ws_classes.AgentFacilitiesWS;
import com.xml.booking.backendmain.ws_classes.AppointmentWS;
import com.xml.booking.backendmain.ws_classes.DeleteFacilityRequest;
import com.xml.booking.backendmain.ws_classes.DeleteFacilityResponse;
import com.xml.booking.backendmain.ws_classes.FacilityWS;
import com.xml.booking.backendmain.ws_classes.MessageResponse;
import com.xml.booking.backendmain.ws_classes.NewFacilityRequest;
import com.xml.booking.backendmain.ws_classes.TestRequest;
import com.xml.booking.backendmain.ws_classes.TestResponse;
import com.xml.booking.backendmain.ws_classes.UserFromIdRequest;
import com.xml.booking.backendmain.ws_classes.UserRequest;
import com.xml.booking.backendmain.ws_classes.UserWS;

@Endpoint
public class WSFacilityEndpoint {
	private static final String NAMESPACE_URI = "http://booking.xml.com/backendmain/ws-classes";
	
	@Autowired
	private FacilityService facilityService; 
	
	@Autowired
	private FacilityTypeService facilityTypeService; 
	
	@Autowired
	private AppointmentService appointmentService;
	
	@Autowired
	private LocationService locationService; 
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private ImageService imageService;
	
	
	
//	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "userRequest")
//	@ResponsePayload
//	public UserWS getUserByUsername(@RequestPayload UserRequest userRequest) {
//		
//		User user = userService.findByUsername(userRequest.getUsername());
//		if(user == null || !user.getRole().getName().equals("AGENT"))
//			return null;
//		
//		
//		return user2ws(user);
//	}
//	
//	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "userFromIdRequest")
//	@ResponsePayload
//	public UserWS getUserByUsername(@RequestPayload UserFromIdRequest userRequest) {
//		
//		User user = userService.findById(userRequest.getId());
//		if(user == null)
//			return null;
//		
//		
//		return user2ws(user);
//	}
	
	private UserWS user2ws(User user){
		UserWS res = new UserWS();
		res.setActive(user.isActive());
		res.setAddress(user.getAddress());
		res.setEmail(user.getEmail());
		res.setId(user.getId());
		res.setName(user.getName());
		res.setLastName(user.getLastName());
		res.setPmb(user.getPmb());
		res.setPassword(user.getPassword());
		res.setUsername(user.getUsername());
		
		return res;
	}
	
	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "testRequest")
	@ResponsePayload
	public TestResponse testRequest(@RequestPayload TestRequest request) {
		TestResponse response = new TestResponse();
		response.setName(request.getName().toUpperCase());
		return response;
	}
	
	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "deleteFacilityRequest")
	@ResponsePayload
	public DeleteFacilityResponse deleteFacility(@RequestPayload DeleteFacilityRequest request) {
		DeleteFacilityResponse response = new DeleteFacilityResponse();
		Facility fac = facilityService.findById(request.getId());
		if(fac==null){
			response.setDeleted(false);
			return response;
		}
		
		fac.setDeleted(true);
		fac = facilityService.save(fac);
		
		response.setDeleted(fac.isDeleted());
		
		return response;
	}
	
	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "agentFacilitiesRequest")
	@ResponsePayload
	public AgentFacilitiesResponse getAgentFacilities(@RequestPayload AgentFacilitiesRequest user) {
		AgentFacilitiesResponse response = new AgentFacilitiesResponse();
		
		List<Facility> facilities = facilityService.findByOwner(userService.findByUsername(user.getUsername()));
		for(Facility f : facilities){
			response.getFacilityWS().add(facility2WS(f));
			
		}
		
		return response;
	}

	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "newFacilityRequest")
	@ResponsePayload
	public MessageResponse addNewFacility(@RequestPayload NewFacilityRequest facilityRequest){
		
		Facility saved = facilityService.save(request2Facility(facilityRequest));
		
		if(facilityRequest.getImagesWS()!=null && facilityRequest.getImagesWS().getImages()!=null){
			List<byte[]> lista = facilityRequest.getImagesWS().getImages();
			for(int i=0; i<lista.size(); i++){
				Image image = new Image();
				image.setImagesDB(lista.get(i));
				image.setFacility(saved);
				imageService.save(image);
			}
		}
		
		MessageResponse response = new MessageResponse();
		
		if(saved==null)
			response.setMessage("Error creating facility");
		else{
			for(AppointmentWS ap : facilityRequest.getAppointmentWS()){
				Appointment appointment = ws2Appointment(ap);
				appointment.setFacility(saved);
				Appointment savedApp = appointmentService.save(appointment);
				if(savedApp==null){
					response.setMessage("Error creating facility");
					return response;
				}
			}
			response.setMessage("Successfuly created facility");
		}
		
		return response;
	}
	
	private FacilityWS facility2WS(Facility facility){
		FacilityWS res = new FacilityWS();
		res.setId(facility.getId());
		res.setName(facility.getName());
		res.setCategory(facility.getCategory());
		res.setOwner(facility.getOwner().getUsername());
		res.setType(facility.getType().getName());
		res.setDescription(facility.getDescription());
		res.setAddress(facility.getAddress());
		res.setLocation(facility.getLocation().getName());
		res.setParkingLot(facility.isParkingLot());
		res.setBathroom(facility.isBathroom());
		res.setWifi(facility.isWifi());
		res.setBreakfast(facility.isBreakfast());
		res.setHalfBoard(facility.isHalfBoard());
		res.setFullBoard(facility.isFullBoard());
		res.setKitchen(facility.isKitchen());
		res.setTv(facility.isTv());
		res.setNumberOfPeople(facility.getNumberOfPeople());
		
		List<Image> imgs = imageService.findByFacility(facility);
		for(Image img : imgs){
			res.getImages().add(img.getImagesDB());
		}
		
		return res;
	}
	
	private Facility ws2Facility(FacilityWS facility){
		Facility res = new Facility();
		res.setName(facility.getName());
		res.setCategory(facility.getCategory());
		res.setOwner(userService.findByUsername(facility.getOwner()));
		res.setType(facilityTypeService.findByName(facility.getType()));
		res.setDescription(facility.getDescription());
		res.setAddress(facility.getAddress());
		res.setLocation(locationService.findByName(facility.getLocation()));
		res.setParkingLot(facility.isParkingLot());
		res.setBathroom(facility.isBathroom());
		res.setWifi(facility.isWifi());
		res.setBreakfast(facility.isBreakfast());
		res.setHalfBoard(facility.isHalfBoard());
		res.setFullBoard(facility.isFullBoard());
		res.setKitchen(facility.isKitchen());
		res.setTv(facility.isTv());
		res.setNumberOfPeople(facility.getNumberOfPeople());
		
		return res;
	}
	
	private Facility request2Facility(NewFacilityRequest facility){
		Facility res = new Facility();
		res.setName(facility.getName());
		res.setCategory(facility.getCategory());
		res.setOwner(userService.findByUsername(facility.getOwner()));
		res.setType(facilityTypeService.findByName(facility.getType()));
		res.setDescription(facility.getDescription());
		res.setAddress(facility.getAddress());
		res.setLocation(locationService.findByName(facility.getLocation()));
		res.setParkingLot(facility.isParkingLot());
		res.setBathroom(facility.isBathroom());
		res.setWifi(facility.isWifi());
		res.setBreakfast(facility.isBreakfast());
		res.setHalfBoard(facility.isHalfBoard());
		res.setFullBoard(facility.isFullBoard());
		res.setKitchen(facility.isKitchen());
		res.setTv(facility.isTv());
		res.setNumberOfPeople(facility.getNumberOfPeople());
		
		return res;
	}
	
	private Appointment ws2Appointment(AppointmentWS appointment){
		Appointment res = new Appointment();
		res.setFromDate(appointment.getFromDate().toGregorianCalendar().getTime());
		res.setToDate(appointment.getToDate().toGregorianCalendar().getTime());
		res.setPrice(appointment.getPrice());
		
		return res;
	}
	
}
