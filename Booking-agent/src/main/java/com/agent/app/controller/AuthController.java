package com.agent.app.controller;

import java.io.IOException;
import java.security.SecureRandom;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.agent.app.DTOs.JwtAuthenticationResponse;
import com.agent.app.DTOs.PasswordDTO;
import com.agent.app.model.User;
import com.agent.app.security.JwtTokenProvider;
import com.agent.app.service.EmailService;
import com.agent.app.service.UserService;
import com.agent.app.wsdl.UserRequest;
import com.booking.app.logger.Logger;



@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    JwtTokenProvider tokenProvider;
    
    @Autowired
    UserService userService;
    
    @Autowired
    EmailService emailService;

    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@RequestBody UserRequest loginRequest) throws IOException {
    	Authentication authentication = null;
    	
    	try {
    		authentication = autoLogin(loginRequest.getUsername(), loginRequest.getPassword());
    	}catch(Exception e){
    		e.printStackTrace();
    		Logger.getInstance().log("Unsuccessful login atempt with username: "+loginRequest.getUsername());
    		return new ResponseEntity<>("Wrong username or password", HttpStatus.BAD_REQUEST);
    	}
    	
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = tokenProvider.generateToken(authentication);
        
        Logger.getInstance().log("Successful login with username: "+loginRequest.getUsername());
        return new ResponseEntity<>(new JwtAuthenticationResponse(jwt), HttpStatus.OK);
    }
    
    @GetMapping("/GetUser")
    public ResponseEntity<?> getUser() {
    	return ResponseEntity.ok(SecurityContextHolder.getContext().getAuthentication());
    }
    
    @GetMapping("/signout")
    public boolean logout (HttpServletRequest request, HttpServletResponse response) throws IOException {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username= auth.getName();

        if (auth != null){    
            new SecurityContextLogoutHandler().logout(request, response, auth);
            Logger.getInstance().log("Successful logout from username: "+username);
            return true;
        }
        return false;
    }
    
    @PutMapping("/resetPassword")
    public ResponseEntity<?> resetPassword(@RequestBody User user) throws IOException{
    	
    	User u = userService.findByEmail(user.getEmail());
    	if(u==null || !u.getRole().getName().equals("AGENT")){
    		 Logger.getInstance().log("Unsuccessful password reset from username: "+SecurityContextHolder.getContext().getAuthentication().getName());
    		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    	}
    	
    	String newPassword = randomString(10);
    	emailService.sendCustomEmail(u.getEmail(), "Password Reset", "Username: " + u.getUsername() + "\nYour new password is: " + newPassword);
    	u.setPassword(passwordEncoder.encode(newPassword));
    	
    	userService.save(u);
		Logger.getInstance().log("Successful password reset from username: "+SecurityContextHolder.getContext().getAuthentication().getName());

    	return new ResponseEntity<>(HttpStatus.OK);
    }
    
    @PutMapping("/setNewPassword")
    public ResponseEntity<?> setNewPassword(@RequestBody PasswordDTO dto) throws IOException{
    	Authentication auth = SecurityContextHolder.getContext().getAuthentication();
    	if(auth==null)
    		return new ResponseEntity<>("You are not allowed to perform this action", HttpStatus.BAD_REQUEST);
    	
    	if(!dto.getConfirmPassword().equals(dto.getNewPassword()))
    		return new ResponseEntity<>("Please confirm your new password", HttpStatus.BAD_REQUEST);
    	
    	//^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\S+$).{8,}$
    	
    	if(!dto.getNewPassword().matches("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=\\S+$).{10,}$")){
    		return new ResponseEntity<>("Password must be at least 10 characters long and contain at least one upper case letter, one lower case letter and a number", HttpStatus.BAD_REQUEST);
    	}
    	
    	User user = userService.findByUsername(auth.getName());
    	if(user==null)
    		return new ResponseEntity<>("You are not allowed to perform this action", HttpStatus.BAD_REQUEST);
    	
    	if(!passwordEncoder.matches(dto.getOldPassword(), user.getPassword())){
    		return new ResponseEntity<>("Wrong current password", HttpStatus.BAD_REQUEST);
    	}
    	
    	user.setPassword(passwordEncoder.encode(dto.getNewPassword()));
    	user = userService.save(user);
    	if(user==null)
    		return new ResponseEntity<>("Something went wrong", HttpStatus.BAD_REQUEST);
    	
		Logger.getInstance().log("Successful password change from username: "+SecurityContextHolder.getContext().getAuthentication().getName());
    	return new ResponseEntity<>(HttpStatus.OK);
    	
    }
   
    
    private Authentication autoLogin(String username, String password) {
    	return authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        username,
                        password
                )
        );
    }
    
    private String randomString( int len ){
    	String AB = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
    	SecureRandom rnd = new SecureRandom();
	    StringBuilder sb = new StringBuilder( len );
	    for( int i = 0; i < len; i++ ) 
	      sb.append( AB.charAt( rnd.nextInt(AB.length()) ) );
	    return sb.toString();
    	}
}
