package com.booking.app.service;

import java.util.List;

import com.booking.app.DTOs.MessageDTO;
import com.booking.app.model.Message;
import com.booking.app.model.User;

public interface MessageService {

	Message findById(Long id);
	
	Message save(Message message);
	
	List<Message> findByReciver(User reciver);
	
	List<Message> findBySender(User sender);
	
	List<MessageDTO> findForUser(User user);
}
