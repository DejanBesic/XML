package com.booking.app.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.booking.app.model.JwtBlocked;
import com.booking.app.repository.JwtBlacklistRepository;
import com.booking.app.service.JwtBlacklistService;

@Service
public class JwtBlacklistServiceImpl implements JwtBlacklistService {

	@Autowired
	JwtBlacklistRepository jwtRepository;
	
	@Override
	public JwtBlocked findByJwt(String jwt) {
		return jwtRepository.findByJwt(jwt);
	}

	@Override
	public JwtBlocked save(JwtBlocked jwtBlocked) {
		return jwtRepository.save(jwtBlocked);
	}

}
