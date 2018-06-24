package com.booking.app.ws;

import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import com.booking.app.model.Message;
import com.booking.app.model.User;
import com.booking.app.service.MessageService;
import com.booking.app.service.UserService;
import com.xml.booking.backendmain.ws_classes.ConfirmReservationRequest;
import com.xml.booking.backendmain.ws_classes.ConfirmReservationResponse;
import com.xml.booking.backendmain.ws_classes.MessageRequest;
import com.xml.booking.backendmain.ws_classes.MessageWS;
import com.xml.booking.backendmain.ws_classes.MessagesResponse;
import com.xml.booking.backendmain.ws_classes.SendMessageRequest;
import com.xml.booking.backendmain.ws_classes.SendMessageResponse;

@Endpoint
public class WSMessageEndpoint {
	private static final String NAMESPACE_URI = "http://booking.xml.com/backendmain/ws-classes";

	
	@Autowired
	private UserService userService;
	
	@Autowired
	private MessageService messageService;
	
	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "sendMessageRequest")
	public void sendRequest(@RequestPayload SendMessageRequest request) {
		Message message = new Message();
		message.setDate(new Date());
		message.setMessage(request.getMessage());
		message.setSender(userService.findByUsername(request.getSender()));
		message.setReciver(userService.findByUsername(request.getReciver()));
		
		message = messageService.save(message);
		
	}
	
	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "messageRequest")
	@ResponsePayload
	public MessagesResponse getMessages(@RequestPayload MessageRequest request) throws DatatypeConfigurationException{
		MessagesResponse response = new MessagesResponse();
		User reciver = userService.findByUsername(request.getName());
		List<Message> list = messageService.findByReciver(reciver);
		
		if(list.size()>0){
			for(Message m : list){
				response.getMessageWS().add(message2WS(m));
			}
		}
		
		return response;
	}

	private MessageWS message2WS(Message m) throws DatatypeConfigurationException {
		// TODO Auto-generated method stub
		MessageWS ws = new MessageWS();
		ws.setId(m.getId());
		ws.setMessage(m.getMessage());
		ws.setSender(m.getSender().getUsername());
		ws.setReciver(m.getReciver().getUsername());
		
		GregorianCalendar date = new GregorianCalendar();
		date.setTime(m.getDate());
		ws.setDate(DatatypeFactory.newInstance().newXMLGregorianCalendar(date));
		
		return ws;
	}
}
