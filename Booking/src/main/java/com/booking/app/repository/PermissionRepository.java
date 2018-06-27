package com.booking.app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.booking.app.model.Permission;

@Repository
public interface PermissionRepository extends JpaRepository<Permission, Long> {

	@Query("SELECT p FROM Permission p WHERE p.id in (SELECT up.permission_id FROM UserPermission up where up.user_id = :userID)") 
	List<Permission> findPermissionsForUser(@Param("userID") Long userID);
}
