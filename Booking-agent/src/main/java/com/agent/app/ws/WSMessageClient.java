package com.agent.app.ws;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.ws.client.core.support.WebServiceGatewaySupport;
import org.springframework.ws.soap.client.core.SoapActionCallback;

import com.agent.app.DTOs.MessageDTO;
import com.agent.app.wsdl.ConfirmReservationResponse;
import com.agent.app.wsdl.MessageRequest;
import com.agent.app.wsdl.MessagesResponse;
import com.agent.app.wsdl.SendMessageRequest;


public class WSMessageClient extends WebServiceGatewaySupport{

	private static final String URI = "http://localhost:1312/ws";
	
	public void send(MessageDTO dto) {
		// TODO Auto-generated method stub
		SendMessageRequest request = new SendMessageRequest();
		request.setMessage(dto.getMessage());
		request.setReciver(dto.getReciverUsername());
		request.setSender(SecurityContextHolder.getContext().getAuthentication().getName());
		getWebServiceTemplate().marshalSendAndReceive(URI , request, new SoapActionCallback(URI + "/sendMessageRequest"));

	}
	
	public MessagesResponse getMessages(){
		MessageRequest request = new MessageRequest();
		request.setName(SecurityContextHolder.getContext().getAuthentication().getName());
		
		MessagesResponse response = (MessagesResponse) getWebServiceTemplate().marshalSendAndReceive(URI , request, new SoapActionCallback(URI + "/messageRequest"));

		
		return response;
	}
	
	
	
	
}
