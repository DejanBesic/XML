package com.booking.app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.booking.app.model.Message;
import com.booking.app.model.User;

public interface MessageRepository extends JpaRepository<Message, Long> {

	List<Message> findByReciver(User reciver);
	
	List<Message> findBySender(User sender);
	
//	@Query("SELECT r FROM Message r where (r.SENDER_ID  = :userid or r.RECIVER_ID  = :userid) ORDER BY date DESC") 
//	List<Message> findForUser(Long userid);
	
	List<Message> findBySenderOrReciverOrderByDateDesc(User sender, User reciver);
}