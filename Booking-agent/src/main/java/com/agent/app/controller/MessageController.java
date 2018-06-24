package com.agent.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.agent.app.DTOs.MessageDTO;
import com.agent.app.ws.WSMessageClient;



@RestController
@RequestMapping("/api/message")
public class MessageController {

	@Autowired
	private WSMessageClient messageClient;
	
	@RequestMapping(value= "/sendMessage", method=RequestMethod.POST)
	public ResponseEntity<?> sendMessage(@RequestBody MessageDTO messageRequest) {
		messageClient.send(messageRequest);
		
		return new ResponseEntity<>(HttpStatus.OK);
	}
}
