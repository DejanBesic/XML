package com.agent.app.service;

import com.agent.app.wsdl.AgentFacilitiesRequest;
import com.agent.app.wsdl.AgentFacilitiesResponse;
import com.agent.app.wsdl.MessageResponse;
import com.agent.app.wsdl.NewFacilityRequest;

public interface FacilityWSService {

	AgentFacilitiesResponse getAgentFacilities(AgentFacilitiesRequest user);
	
	MessageResponse addNewFacility(NewFacilityRequest facility);
	
	boolean deleteFacility(Long id);
}
