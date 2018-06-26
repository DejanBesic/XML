package com.agent.app.ws;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.ws.client.core.support.WebServiceGatewaySupport;
import org.springframework.ws.soap.client.core.SoapActionCallback;

import com.agent.app.wsdl.ConfirmReservationRequest;
import com.agent.app.wsdl.ConfirmReservationResponse;
import com.agent.app.wsdl.ReservationRequest;
import com.agent.app.wsdl.ReservationsResponse;
import com.agent.app.wsdl.UnavailableReservationRequest;
import com.agent.app.wsdl.UnavailableResponse;

public class WSReservationClient extends WebServiceGatewaySupport{

	private static final String URI = "http://localhost:1312/ws";

	public boolean confirm(Long id) {
		// TODO Auto-generated method stub
		ConfirmReservationRequest request = new ConfirmReservationRequest();
		request.setId(id);
		
		ConfirmReservationResponse response = (ConfirmReservationResponse) getWebServiceTemplate().marshalSendAndReceive(URI , request, new SoapActionCallback(URI + "/confirmReservationRequest"));

		
		return response.isConfirmed();
	}
	
	public ReservationsResponse getReservations(){
		ReservationRequest request = new ReservationRequest();
		request.setUsername(SecurityContextHolder.getContext().getAuthentication().getName());
		
		ReservationsResponse response = (ReservationsResponse) getWebServiceTemplate().marshalSendAndReceive(URI , request, new SoapActionCallback(URI + "/reservationRequest"));
 
		return response;
	}

	public boolean setUnavailable(UnavailableReservationRequest request) {
		// TODO Auto-generated method stub
		UnavailableResponse response = (UnavailableResponse) getWebServiceTemplate().marshalSendAndReceive(URI , request, new SoapActionCallback(URI + "/unavailableReservationRequest"));
		 
		return response.isUnavailable();
	}
	
	
	
}
