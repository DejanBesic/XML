package com.admin.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.admin.app.model.Role;

public interface RoleRepository extends JpaRepository<Role, Long>{
	
	Role findById(Long id);
}
