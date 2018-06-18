package com.agent.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.agent.app.model.Facility;
import com.agent.app.ws.WSFacilityClient;
import com.agent.app.wsdl.AgentFacilitiesWS;
import com.agent.app.wsdl.UserWS;


@RestController
@RequestMapping("/api/facility")
public class FacilityController {

	@Autowired
	WSFacilityClient client;
	
	@GetMapping(value="/getFacilities")
    public ResponseEntity<?> getFacilities() {
    
		UserWS user = new UserWS();
		user.setId(1);
		
		
		AgentFacilitiesWS response = client.facilityWS(user);
		if(response == null)
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		
		
    	return new ResponseEntity<>(response.getFacilityWS(), HttpStatus.OK);
    }
}
