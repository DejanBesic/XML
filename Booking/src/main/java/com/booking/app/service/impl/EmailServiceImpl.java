package com.booking.app.service.impl;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.stereotype.Service;

import com.booking.app.service.EmailService;

@Service
public class EmailServiceImpl implements EmailService {

	@Override
	public Boolean sendEmail(String to, String password) {
	      String from = "asdsad603@gmail.com";
	      String host = "localhost";
	      String pass = "Asdasd12";

	      Properties props = new Properties();
	        props.put("mail.smtp.starttls.enable", "true");
	        props.put("mail.smtp.auth", "true");
	        props.put("mail.smtp.host", "smtp.gmail.com");
	        props.put("mail.smtp.port", "587");
	        props.put("mail.smtp.ssl.trust", "smtp.gmail.com");

	        Session session = Session.getInstance(props,
	                new javax.mail.Authenticator() {
	                  protected PasswordAuthentication getPasswordAuthentication() {
	                      return new PasswordAuthentication(from, pass);
	                  }
	                });
	        
	      try {
	         MimeMessage message = new MimeMessage(session);

	         message.setFrom(new InternetAddress(from));
	         message.setRecipients(Message.RecipientType.TO,
	                 InternetAddress.parse(to));
	         message.setSubject("Wellcome to BeiBooking");

	         message.setText("Your password is: "+password);

	         Transport.send(message);
	      } catch (MessagingException mex) {
	         return false;
	      }
	      return true;
	}

	@Override
	public Boolean sendCustomEmail(String to, String subject, String messageText) {
		 String from = "asdsad603@gmail.com";
	      String host = "localhost";
	      String pass = "Asdasd12";

	      Properties props = new Properties();
	        props.put("mail.smtp.starttls.enable", "true");
	        props.put("mail.smtp.auth", "true");
	        props.put("mail.smtp.host", "smtp.gmail.com");
	        props.put("mail.smtp.port", "587");
	        props.put("mail.smtp.ssl.trust", "smtp.gmail.com");

	        Session session = Session.getInstance(props,
	                new javax.mail.Authenticator() {
	                  protected PasswordAuthentication getPasswordAuthentication() {
	                      return new PasswordAuthentication(from, pass);
	                  }
	                });
	        
	      try {
	         MimeMessage message = new MimeMessage(session);

	         message.setFrom(new InternetAddress(from));
	         message.setRecipients(Message.RecipientType.TO,
	                 InternetAddress.parse(to));
	         message.setSubject(subject);

	         message.setText(messageText);

	         Transport.send(message);
	      } catch (MessagingException mex) {
	         return false;
	      }
	      return true;
	}

}
