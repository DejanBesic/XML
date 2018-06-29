package com.booking.app.controller;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.booking.app.DTOs.RegistrationResponse;
import com.booking.app.DTOs.UserRequest;
import com.booking.app.DTOs.UserResponse;
import com.booking.app.logger.Logger;
import com.booking.app.model.User;
import com.booking.app.service.impl.EmailServiceImpl;
import com.booking.app.service.impl.UserServiceImpl;


@RestController
@RequestMapping("/user")
public class UserController {

	@Autowired
	UserServiceImpl userService;
	
    @Autowired
    EmailServiceImpl emailService;
	
	public static final Pattern pattern = 
    	    Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);
	
	@GetMapping()
	public ResponseEntity<?> getUser() {
		User user = userService.findByUsername(SecurityContextHolder.getContext().getAuthentication().getName());
		
		UserResponse userDTO = new UserResponse(user.getEmail(), user.getName(), user.getLastName(), user.getUsername(), user.getAddress());
		return ResponseEntity.ok(userDTO);
	}
	
	@RequestMapping(value= "/changePassword", method=RequestMethod.POST)
	public ResponseEntity<?> changePassword(@RequestBody UserRequest userRequest) throws IOException {
		User user = userService.findByUsername(SecurityContextHolder.getContext().getAuthentication().getName());
		
		BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
		
		if (!bCryptPasswordEncoder.matches(userRequest.getOldPassword(), user.getPassword())) {
			return new ResponseEntity<>("Wrong password.", HttpStatus.BAD_REQUEST);
		}
		
	    if (!userRequest.getNewPassword().matches("((?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[.!?^&@#$%]).{6,20})")) {
    		return new ResponseEntity<>("Password must be at least 10 characters long and contain at least one upper case letter, one lower case letter, a number and one special character(.!?^&@#$%)", HttpStatus.BAD_REQUEST);
    	}
	   
		if (!userRequest.getNewPassword().equals(userRequest.getConfirmPassword())) {
			return new ResponseEntity<>("New password and confirm password are not the same.", HttpStatus.BAD_REQUEST);
		}
				
		user.setPassword(bCryptPasswordEncoder.encode(userRequest.getNewPassword()));
		
		userService.save(user);
        Logger.getInstance().log("Changed password by username: "+SecurityContextHolder.getContext().getAuthentication().getName());
		
		String subject = "Password change";
		String messageText = "You have successfully changed your password.";
		if(!emailService.sendCustomEmail(user.getEmail(), subject, messageText)) {
			return new ResponseEntity<>(new RegistrationResponse(false, "Failed to send email."), HttpStatus.BAD_REQUEST);
		}
		
		UserResponse userDTO = new UserResponse(user.getEmail(), user.getName(), user.getLastName(), user.getUsername(), user.getAddress());
		return ResponseEntity.ok(userDTO);
	}
	
	@PostMapping()
	public ResponseEntity<?> editUser(@RequestBody UserRequest userRequest) throws IOException {
		User user = userService.findByUsername(SecurityContextHolder.getContext().getAuthentication().getName());
		
		BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
		
		if (!bCryptPasswordEncoder.matches(userRequest.getOldPassword(), user.getPassword())) {
			return new ResponseEntity<>("Wrong password.", HttpStatus.BAD_REQUEST);
		}
		
	    if (!userRequest.getNewPassword().matches("((?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[.!?^&@#$%]).{6,20})")) {
    		return new ResponseEntity<>("Password must be at least 10 characters long and contain at least one upper case letter, one lower case letter, a number and one special character(.!?^&@#$%)", HttpStatus.BAD_REQUEST);
    	}
	    
	    Matcher matcher = pattern.matcher(userRequest.getEmail());
		
	    if (!matcher.matches()) {
	    	return new ResponseEntity<>("Email format is not valid.", HttpStatus.BAD_REQUEST);
        }
	   
		if (!userRequest.getNewPassword().equals(userRequest.getConfirmPassword())) {
			return new ResponseEntity<>("New password and confirm password are not the same.", HttpStatus.BAD_REQUEST);
		}
				
		user.setAddress(userRequest.getAddress());
		user.setEmail(userRequest.getEmail());
		user.setPassword(bCryptPasswordEncoder.encode(userRequest.getNewPassword()));
		user.setName(userRequest.getName());
		user.setLastName(user.getLastName());
		
		
		userService.save(user);
        Logger.getInstance().log("Changed password by username: "+SecurityContextHolder.getContext().getAuthentication().getName());
		
		String subject = "Password change";
		String messageText = "You have successfully changed your password.";
		if(!emailService.sendCustomEmail(user.getEmail(), subject, messageText)) {
			return new ResponseEntity<>(new RegistrationResponse(false, "Failed to send email."), HttpStatus.BAD_REQUEST);
		}
		
		UserResponse userDTO = new UserResponse(user.getEmail(), user.getName(), user.getLastName(), user.getUsername(), user.getAddress());
		return ResponseEntity.ok(userDTO);
	}
}
