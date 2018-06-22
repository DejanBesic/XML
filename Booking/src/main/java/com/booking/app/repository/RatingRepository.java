package com.booking.app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.booking.app.model.Facility;
import com.booking.app.model.Rating;
import com.booking.app.model.User;

@Repository
public interface RatingRepository extends JpaRepository<Rating, Long>{

	List<Rating> findByFacility(Facility facility);
	
	Rating findByFacilityAndUser(Facility facility, User user);

}
