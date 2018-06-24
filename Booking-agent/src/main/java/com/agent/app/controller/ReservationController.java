package com.agent.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.agent.app.ws.WSReservationClient;

@RestController
@RequestMapping("/api/reservation")
public class ReservationController {

	@Autowired
	WSReservationClient reservationClient;
	
	@RequestMapping(value="/confirm/{id}", method=RequestMethod.PUT)
	public ResponseEntity<?> confirmReservation(@PathVariable Long id){
		boolean confirmed = reservationClient.confirm(id);
		
		if(!confirmed)
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		
		return new ResponseEntity<>(HttpStatus.OK);
	}
}
