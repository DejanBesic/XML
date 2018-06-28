package com.booking.app.annotations;
import java.lang.reflect.Method;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.booking.app.model.Permission;
import com.booking.app.model.User;
import com.booking.app.service.UserService;
import com.booking.app.service.impl.PermissionServiceImpl;

public class PermissionInterceptor implements HandlerInterceptor {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private PermissionServiceImpl permissionService;

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		
		
		
		if (handler instanceof HandlerMethod) {
			HandlerMethod handlerMethod = (HandlerMethod) handler; 
			Method method = handlerMethod.getMethod();
			String permissionName = null;
			if(method.getDeclaringClass().isAnnotationPresent(RestController.class)) {
				if(method.isAnnotationPresent(PermissionAnnotation.class)) {
					PermissionAnnotation annotation = method.getAnnotation(PermissionAnnotation.class);
					permissionName = annotation.name();
				} else {
					return true;
				}
			} else {
				return true;
			}
			User ulogovani = userService.findByUsername(SecurityContextHolder.getContext().getAuthentication().getName());
			if(ulogovani == null) {
				return false;
			}
			ulogovani.setPermissions(permissionService.getPermissionsForUser(ulogovani.getId()));
			
			for(Permission permission : ulogovani.getPermissions()){
				
				if(permission.getName().equals(permissionName)){
					return true;
				}
			}
		
			return false;
		} else {
			return true;
		}
		
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		// TODO Auto-generated method stub
		
	}

}