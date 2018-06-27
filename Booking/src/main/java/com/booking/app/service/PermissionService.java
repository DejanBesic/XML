package com.booking.app.service;

import java.util.List;

import com.booking.app.model.Permission;

public interface PermissionService {

	List<Permission> getPermissionsForUser(Long userID);
}
