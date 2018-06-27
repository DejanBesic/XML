package com.booking.app.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.booking.app.model.Permission;
import com.booking.app.repository.PermissionRepository;
import com.booking.app.service.PermissionService;

@Service
public class PermissionServiceImpl implements PermissionService{

	@Autowired
	PermissionRepository permissionRepository;
	
	@Override
	public List<Permission> getPermissionsForUser(Long userID) {
		return permissionRepository.findPermissionsForUser(userID);
	}

}
