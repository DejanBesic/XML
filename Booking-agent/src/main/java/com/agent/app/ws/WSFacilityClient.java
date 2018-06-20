package com.agent.app.ws;

import org.springframework.ws.client.core.support.WebServiceGatewaySupport;
import org.springframework.ws.soap.client.core.SoapActionCallback;

import com.agent.app.model.Facility;
import com.agent.app.wsdl.AgentFacilitiesRequest;
import com.agent.app.wsdl.AgentFacilitiesResponse;
import com.agent.app.wsdl.MessageResponse;
import com.agent.app.wsdl.NewFacilityRequest;


public class WSFacilityClient extends WebServiceGatewaySupport {
	private static final String URI = "http://localhost:1312/ws";
	
	public AgentFacilitiesResponse getAgentFacilities(AgentFacilitiesRequest user) {
		
		AgentFacilitiesResponse response = (AgentFacilitiesResponse) getWebServiceTemplate().marshalSendAndReceive(URI , user, new SoapActionCallback(URI + "/agentFacilitiesRequest"));
		
		return response;
	}
	
	
	public MessageResponse addNewFacility(NewFacilityRequest facility){
		
		return (MessageResponse) getWebServiceTemplate().marshalSendAndReceive(URI , facility, new SoapActionCallback(URI + "/newFacilityRequest"));
	}
	
	
}
