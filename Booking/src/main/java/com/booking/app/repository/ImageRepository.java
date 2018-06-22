package com.booking.app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.booking.app.model.Facility;
import com.booking.app.model.Image;


@Repository
public interface ImageRepository extends JpaRepository<Image, Long>{
	
	List<Image> findByFacility(Facility facility);

}
