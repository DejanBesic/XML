package com.booking.app.service.impl;

import java.security.SecureRandom;

import org.springframework.stereotype.Service;

import com.booking.app.service.RandomPasswordGenerator;

@Service
public class RandomPasswordGeneratorImpl implements RandomPasswordGenerator{

	private static int STRING_LEN = 10;
	private static final String AB = ".,/!@#$%^&*()_+|\0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
	private static SecureRandom rnd = new SecureRandom();
	
	@Override
	public String generatePassword() {
		StringBuilder sb = new StringBuilder( STRING_LEN );
		   for( int i = 0; i < STRING_LEN; i++ ) 
		      sb.append( AB.charAt( rnd.nextInt(AB.length()) ) );
		   return sb.toString();
	}

}
