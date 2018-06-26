	package com.agent.app.service;

import java.util.List;

import com.agent.app.model.Role;



public interface RoleService {

	Role findById(Long id);
	
	List<Role> findAll();
	
	Role findByName(String name);
	
	Role save(Role role);
	
	void delete(Long id);
	
}
