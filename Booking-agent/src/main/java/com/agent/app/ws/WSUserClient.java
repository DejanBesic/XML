package com.agent.app.ws;

import org.springframework.ws.client.core.support.WebServiceGatewaySupport;
import org.springframework.ws.soap.client.core.SoapActionCallback;

import com.agent.app.wsdl.AgentFacilitiesRequest;
import com.agent.app.wsdl.AgentFacilitiesResponse;
import com.agent.app.wsdl.MessageResponse;
import com.agent.app.wsdl.NewFacilityRequest;
import com.agent.app.wsdl.UserFromIdRequest;
import com.agent.app.wsdl.UserRequest;
import com.agent.app.wsdl.UserWS;

public class WSUserClient extends WebServiceGatewaySupport{

private static final String URI = "http://localhost:1312/ws";
	
	public UserWS loginUser(UserRequest user) {
		
		UserWS response = (UserWS) getWebServiceTemplate().marshalSendAndReceive(URI , user, new SoapActionCallback(URI + "/userRequest"));
		
		return response;
	}
	
	public UserWS getUserById(UserFromIdRequest user) {
		
		UserWS response = (UserWS) getWebServiceTemplate().marshalSendAndReceive(URI , user, new SoapActionCallback(URI + "/userFromIdRequest"));
		
		return response;
	}
	

}
