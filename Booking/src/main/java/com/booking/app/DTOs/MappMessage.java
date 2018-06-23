package com.booking.app.DTOs;

import java.util.ArrayList;
import java.util.List;

import com.booking.app.model.Message;

public final class MappMessage {
	public static MessageDTO mappMessage(Message msg) {
		return new MessageDTO(msg.getId(),msg.getReciver().getId(),msg.getSender().getId(),
				msg.getReciver().getUsername(),msg.getSender().getUsername(),msg.getReciver().getName()
				+" "+msg.getReciver().getLastName(),msg.getSender().getName()
				+" "+msg.getSender().getLastName(),msg.getMessage(),msg.getDate());
	}
	
	public static List<MessageDTO> mappMessages(List<Message> msgs){
		List<MessageDTO> list = new ArrayList<MessageDTO>();
		for(Message m : msgs) {
			list.add(mappMessage(m));
		}
		return list;
	}
}
