package com.booking.app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.booking.app.model.Message;
import com.booking.app.model.User;

public interface MessageRepository extends JpaRepository<Message, Long> {

	List<Message> findByReciver(User reciver);
	
	List<Message> findBySender(User sender);
	
}
