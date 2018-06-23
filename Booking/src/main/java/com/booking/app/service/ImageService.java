package com.booking.app.service;

import java.util.List;

import com.booking.app.model.Facility;
import com.booking.app.model.Image;

public interface ImageService {

	Image findById(Long id);
	
	Image save(Image image);
	
	void delete(Long id);
	
	List<Image> findByFacility(Facility facility);
}
