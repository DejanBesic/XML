package com.agent.app.ws;

import org.springframework.ws.client.core.support.WebServiceGatewaySupport;
import org.springframework.ws.soap.client.core.SoapActionCallback;

import com.agent.app.model.Facility;
import com.agent.app.wsdl.AgentFacilitiesWS;
import com.agent.app.wsdl.UserWS;


public class WSFacilityClient extends WebServiceGatewaySupport {
	private static final String URI = "http://localhost:1312/ws";
	
	public AgentFacilitiesWS facilityWS(UserWS user) {
		
		AgentFacilitiesWS response = (AgentFacilitiesWS) getWebServiceTemplate().marshalSendAndReceive(URI , user, new SoapActionCallback(URI + "/getFacilities"));
		
		
		return response;
//		FacilityRequest request = new FacilityRequest();
//		request.setName(facility.getName());
//		request.setAddress(facility.getAddress());
//		request.setDescription(facility.getDescription());
//		request.setCategory(facility.getCategory());
//		FacilityResponse response = (FacilityResponse) getWebServiceTemplate().marshalSendAndReceive(URI , request, new SoapActionCallback(URI + "/facilityRequest"));
//		return response;
	}
	
	
	
}
