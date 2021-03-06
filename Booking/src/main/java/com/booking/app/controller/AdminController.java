package com.booking.app.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.booking.app.DTOs.RegistrationResponse;
import com.booking.app.annotations.PermissionAnnotation;
import com.booking.app.logger.Logger;
import com.booking.app.model.FacilityType;
import com.booking.app.model.Rating;
import com.booking.app.model.Role;
import com.booking.app.model.User;
import com.booking.app.security.JwtTokenProvider;
import com.booking.app.service.impl.EmailServiceImpl;
import com.booking.app.service.impl.FacilityTypeServiceImpl;
import com.booking.app.service.impl.RandomPasswordGeneratorImpl;
import com.booking.app.service.impl.RatingServiceImpl;
import com.booking.app.service.impl.RoleServiceImpl;
import com.booking.app.service.impl.UserServiceImpl;

@RestController
@RequestMapping("/admin")
public class AdminController {
	
    @Autowired
    UserServiceImpl userService;
    
    @Autowired
    RatingServiceImpl ratingService;
    
    @Autowired
    RandomPasswordGeneratorImpl passwordService;
    
    @Autowired
    PasswordEncoder passwordEncoder;
    
    @Autowired
    EmailServiceImpl emailService;
    
    @Autowired
    FacilityTypeServiceImpl facService;
    
    @Autowired
    RoleServiceImpl roleService;
    
    @Autowired
    AuthenticationManager authenticationManager;
    
    @Autowired
    JwtTokenProvider tokenProvider;
    
    @GetMapping("/test")
    public ResponseEntity<?> test() {
    	return new ResponseEntity<>(true, HttpStatus.OK);
    }
    
    @PermissionAnnotation(name = "GET_REGULAR_USERS")
    @RequestMapping(
            value = "getUsers",
            method = RequestMethod.GET
    )
    public ResponseEntity<?> getUser() {
    	return new ResponseEntity<>(userService.findAllInactive(), HttpStatus.OK);
    }
	
    @PermissionAnnotation(name = "GET_TYPES")
	@GetMapping("/getTypes")
    public ResponseEntity<?> getTypes() {
    	return new ResponseEntity<>(facService.findAll(), HttpStatus.OK);
    }
	
    @PermissionAnnotation(name = "GET_RATINGS")
	@GetMapping("/getRatings")
	public ResponseEntity<?> getRating() {
		return new ResponseEntity<>(ratingService.findAllUnreviewed(), HttpStatus.OK);
    }
	
    @PermissionAnnotation(name = "APPROVE_COMMENT")
	@RequestMapping(value= "/approve", method=RequestMethod.POST)
	public ResponseEntity<?> approve(@RequestBody Rating rating) throws IOException {
		Rating rat = ratingService.findById(rating.getId());
		if (rat==null) {
			return new ResponseEntity<>("Failed to approve.", HttpStatus.BAD_REQUEST);
		}
		rat.setReviewed(true);
		rat.setApproved(true);
		ratingService.save(rat);
		Logger.getInstance().log("Approved comment id: "+rat.getId() +" , by admin: "+SecurityContextHolder.getContext().getAuthentication().getName());
		return new ResponseEntity<>(rat, HttpStatus.OK); 
	}
	
    @PermissionAnnotation(name = "APPROVE_USER")
	@RequestMapping(value= "/approveUser", method=RequestMethod.POST)
	public ResponseEntity<?> approveUser(@RequestBody User user) throws IOException {
		User us = userService.findById(user.getId());
		if (us==null) {
			return new ResponseEntity<>("Failed to activate user.", HttpStatus.BAD_REQUEST);
		}
		us.setActive(true);
		userService.save(us);
		Logger.getInstance().log("Approved user id: "+us.getId() +" , by admin: "+SecurityContextHolder.getContext().getAuthentication().getName());

		return new ResponseEntity<>(us, HttpStatus.OK); 
	}
	
    @PermissionAnnotation(name = "BLOCK_USER")
	@RequestMapping(value= "/blockUser", method=RequestMethod.POST)
	public ResponseEntity<?> blockUser(@RequestBody User user) throws IOException {
		User us = userService.findById(user.getId());
		if (us==null) {
			return new ResponseEntity<>("Failed to block user.", HttpStatus.BAD_REQUEST);
		}
		us.setActive(true);
		String subject = "You have been blocked";
		String messageText = "You have been reported by admin and you can no longer continue"
				+" to use our service.";
		if(!emailService.sendCustomEmail(us.getEmail(), subject, messageText)) {
			return new ResponseEntity<>("Failed to block user.", HttpStatus.BAD_REQUEST);
		}
		us.setPassword("a");
		userService.save(us);
		Logger.getInstance().log("Blocked user id: "+us.getId() +" , by admin: "+SecurityContextHolder.getContext().getAuthentication().getName());

		return new ResponseEntity<>(us, HttpStatus.OK); 
	}
	
    @PermissionAnnotation(name = "DELETE_COMMENT")
	@RequestMapping(value= "/block", method=RequestMethod.POST)
	public ResponseEntity<?> block(@RequestBody Rating rating) throws IOException {
		Rating rat = ratingService.findById(rating.getId());
		if (rat==null) {
			return new ResponseEntity<>("Failed to block.", HttpStatus.BAD_REQUEST);
		}
		rat.setReviewed(true);
		rat.setApproved(false);
		ratingService.save(rat);
		Logger.getInstance().log("Deleted comment id: "+rat.getId() +" , by admin: "+SecurityContextHolder.getContext().getAuthentication().getName());

		return new ResponseEntity<>(rat, HttpStatus.OK); 
	}
	
    @PermissionAnnotation(name = "ADD_AGENT")
	@RequestMapping(value= "/addAgent", method=RequestMethod.POST)
	public ResponseEntity<?> addAgent(@RequestBody User user) throws IOException {
		if(userService.findByEmail(user.getEmail())!=null) {
        	return new ResponseEntity<>(new RegistrationResponse(false, "Email address is already in use!"), HttpStatus.BAD_REQUEST);
        }
		Role role = roleService.findByName("AGENT");
		String pass = passwordService.generatePassword();

		User us = new User(user.getEmail(),user.getEmail(),pass,role);
		us.setPassword(passwordEncoder.encode(pass));
		us.setActive(true);
		us.setName(user.getName());
		us.setLastName(user.getLastName());
		us.setAddress(user.getAddress());
		us.setPmb(user.getPmb());
		
		
		User result = userService.save(us);
		if(result == null) {
        	return new ResponseEntity<>(new RegistrationResponse(false, "Server Error"), HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		if(!emailService.sendEmail(result.getEmail(), pass)) {
			userService.delete(result.getId());
			return new ResponseEntity<>(new RegistrationResponse(false, "Email Server Error"), HttpStatus.INTERNAL_SERVER_ERROR);
		}
		Logger.getInstance().log("Added agent id: "+result.getId() +" , by admin: "+SecurityContextHolder.getContext().getAuthentication().getName());

        return new ResponseEntity<>(result, HttpStatus.OK);
	}
	
    @PermissionAnnotation(name = "SAVE_TYPE")
	@RequestMapping(value= "/saveType", method=RequestMethod.POST)
	public ResponseEntity<?> saveType(@RequestBody FacilityType fact) {
		FacilityType ft = facService.findById(fact.getId());
		if(ft == null) {
			return new ResponseEntity<>("Failed to update.", HttpStatus.BAD_REQUEST);
		}
		ft.setName(fact.getName());
		fact = facService.save(ft);
		return new ResponseEntity<>(fact, HttpStatus.OK);
	}
	
    @PermissionAnnotation(name = "DELETE_TYPE")
	@RequestMapping(value= "/deleteType", method=RequestMethod.POST)
	public ResponseEntity<?> deleteType(@RequestBody FacilityType fact) throws IOException {
		FacilityType ft = facService.findById(fact.getId());
		if(ft == null) {
			return new ResponseEntity<>("Failed to delete.", HttpStatus.BAD_REQUEST);
		}
		facService.delete(ft.getId());
		Logger.getInstance().log("Deleted type of facility name: "+ft.getName() +" , by admin: "+SecurityContextHolder.getContext().getAuthentication().getName());

		return new ResponseEntity<>(ft, HttpStatus.OK);

	}
	
    @PermissionAnnotation(name = "ADD_TYPE")
	@RequestMapping(value= "/addNewType", method=RequestMethod.POST)
	public ResponseEntity<?> addNewType(@RequestBody FacilityType fact) {
		FacilityType ft = new FacilityType(fact.getName());
		fact = facService.save(ft);
		return new ResponseEntity<>(fact, HttpStatus.OK);
	}
}
