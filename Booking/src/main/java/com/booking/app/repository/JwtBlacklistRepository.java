package com.booking.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.booking.app.model.JwtBlocked;

@Repository
public interface JwtBlacklistRepository extends JpaRepository<JwtBlocked, Long> {

	JwtBlocked findByJwt(String jwt);
}
