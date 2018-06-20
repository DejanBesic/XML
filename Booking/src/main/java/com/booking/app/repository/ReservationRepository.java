package com.booking.app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.booking.app.model.Facility;
import com.booking.app.model.Reservation;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Long>{

	List<Reservation> findByFacility(Facility facility);

}
