package com.booking.app.service;

public interface EmailService {
	Boolean sendEmail(String to, String password);
	
	Boolean sendCustomEmail(String to, String subject, String messageText);
}
