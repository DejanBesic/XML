package com.booking.app.ws;

import java.util.GregorianCalendar;
import java.util.List;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;


import com.booking.app.model.Reservation;
import com.booking.app.model.User;
import com.booking.app.service.ReservationService;
import com.booking.app.service.UserService;
import com.xml.booking.backendmain.ws_classes.ConfirmReservationRequest;
import com.xml.booking.backendmain.ws_classes.ConfirmReservationResponse;
import com.xml.booking.backendmain.ws_classes.ReservationRequest;
import com.xml.booking.backendmain.ws_classes.ReservationWS;
import com.xml.booking.backendmain.ws_classes.ReservationsResponse;;

@Endpoint
public class WSReservationEndpoint {
	
	private static final String NAMESPACE_URI = "http://booking.xml.com/backendmain/ws-classes";

	@Autowired
	private ReservationService reservationService;

	@Autowired
	private UserService userService;
	
	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "confirmReservationRequest")
	@ResponsePayload
	public ConfirmReservationResponse testRequest(@RequestPayload ConfirmReservationRequest request) {
		ConfirmReservationResponse response = new ConfirmReservationResponse();

		Reservation res = reservationService.findOne(request.getId());
		if(res==null){
			response.setConfirmed(false);
			return response;
		}
		res.setConfirmed(true);
		res = reservationService.save(res);
		response.setConfirmed(res.isConfirmed());
		
		return response;
	}
	
	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "reservationRequest")
	@ResponsePayload
	public ReservationsResponse getReservations(@RequestPayload ReservationRequest request) throws DatatypeConfigurationException{
		
		ReservationsResponse response = new ReservationsResponse();
		
		List<Reservation> sve = reservationService.findAll();
		
		for(Reservation r : sve){
			if(r.getFacility().getOwner().getUsername().equals(request.getUsername())){
				response.getReservationWS().add(reservation2WS(r));
			}
		}
		
		return response;
	}

	private ReservationWS reservation2WS(Reservation r) throws DatatypeConfigurationException {
		// TODO Auto-generated method stub
		ReservationWS ws = new ReservationWS();
		ws.setId(r.getId());
		ws.setFacility(r.getFacility().getName());
		ws.setGuest(r.getGuest().getUsername());
		ws.setPrice(r.getPrice());
		ws.setConfirmed(r.isConfirmed());
		
		GregorianCalendar from = new GregorianCalendar();
		from.setTime(r.getFromDate());
		GregorianCalendar to = new GregorianCalendar();
		to.setTime(r.getToDate());
		
		ws.setFromDate(DatatypeFactory.newInstance().newXMLGregorianCalendar(from));
		ws.setToDate(DatatypeFactory.newInstance().newXMLGregorianCalendar(to));
		
		return ws;
	}
}
