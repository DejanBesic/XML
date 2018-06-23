package com.booking.app.service.impl;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.booking.app.model.Message;
import com.booking.app.model.User;
import com.booking.app.repository.MessageRepository;
import com.booking.app.service.MessageService;

@Service
public class MessageServiceImpl implements MessageService{

	@Autowired
	MessageRepository messageRepository;
	
	private static final DateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
	private static final DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
	
	@Override
	public Message findById(Long id) {
		return messageRepository.findOne(id);
	}

	@Override
	public Message save(Message message) {
		return messageRepository.save(message);
	}

	@Override
	public List<Message> findByReciver(User reciver) {
		
		
		
		return messageRepository.findByReciver(reciver);
	}

	@Override
	public List<Message> findBySender(User sender) {
		return messageRepository.findBySender(sender);
	}

}
