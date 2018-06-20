package com.booking.app.ws;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import com.booking.app.model.User;
import com.booking.app.service.UserService;
import com.xml.booking.backendmain.ws_classes.TestRequest;
import com.xml.booking.backendmain.ws_classes.TestResponse;
import com.xml.booking.backendmain.ws_classes.UserFromIdRequest;
import com.xml.booking.backendmain.ws_classes.UserRequest;
import com.xml.booking.backendmain.ws_classes.UserWS;

@Endpoint
public class WSUserEndpoint {
	private static final String NAMESPACE_URI = "http://booking.xml.com/backendmain/ws-classes";

	@Autowired
	private UserService userService;
	
	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "userRequest")
	@ResponsePayload
	public UserWS getUserByUsername(@RequestPayload UserRequest userRequest) {
		
		User user = userService.findByUsername(userRequest.getUsername());
		if(user == null)
			return null;
		
		
		return user2ws(user);
	}
	
	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "userFromIdRequest")
	@ResponsePayload
	public UserWS getUserByUsername(@RequestPayload UserFromIdRequest userRequest) {
		
		User user = userService.findById(userRequest.getId());
		if(user == null)
			return null;
		
		
		return user2ws(user);
	}
	
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
}
