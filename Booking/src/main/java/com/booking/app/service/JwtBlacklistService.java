package com.booking.app.service;

import com.booking.app.model.JwtBlocked;

public interface JwtBlacklistService {

	JwtBlocked findByJwt(String jwt);
	
	JwtBlocked save(JwtBlocked jwtBlocked);
	
}
