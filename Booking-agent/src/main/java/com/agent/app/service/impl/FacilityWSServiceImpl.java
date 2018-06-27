package com.agent.app.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.agent.app.service.FacilityWSService;
import com.agent.app.ws.WSFacilityClient;
import com.agent.app.wsdl.AgentFacilitiesRequest;
import com.agent.app.wsdl.AgentFacilitiesResponse;
import com.agent.app.wsdl.MessageResponse;
import com.agent.app.wsdl.NewFacilityRequest;

@Service
public class FacilityWSServiceImpl implements FacilityWSService {

	@Autowired
	WSFacilityClient client;
	
	@Override
	public AgentFacilitiesResponse getAgentFacilities(AgentFacilitiesRequest user) {
		// TODO Auto-generated method stub
		return client.getAgentFacilities(user);
	}

	@Override
	public MessageResponse addNewFacility(NewFacilityRequest facility) {
		// TODO Auto-generated method stub
		return client.addNewFacility(facility);
	}

	@Override
	public boolean deleteFacility(Long id) {
		// TODO Auto-generated method stub
		return client.deleteFacility(id);
	}

}
