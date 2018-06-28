package com.booking.app.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.booking.app.DTOs.JwtAuthenticationResponse;
import com.booking.app.DTOs.LoginRequest;
import com.booking.app.logger.Logger;
import com.booking.app.security.JwtTokenProvider;

@RestController
@RequestMapping("/api/admin")
public class AdminLoginController {
    @Autowired
    AuthenticationManager authenticationManager;
    
    @Autowired
    JwtTokenProvider tokenProvider;
    //lll
    @PostMapping("/login")
    public ResponseEntity<?> authenticateUser(@RequestBody LoginRequest loginRequest) throws IOException {
    	Authentication authentication = null;
    	
    	try {
    		authentication = autoLogin(loginRequest.getUsernameOrEmail(), loginRequest.getPassword());
    	}catch(Exception e){
    		e.printStackTrace();
    		Logger.getInstance().log("Unsuccessful login atempt with username: "+loginRequest.getUsernameOrEmail());
    		return new ResponseEntity<>("Wrong username or password", HttpStatus.BAD_REQUEST);
    	}
    	
    	Boolean ad= false;
    	for(GrantedAuthority s : authentication.getAuthorities()) {
    		if(s.getAuthority().toUpperCase().equals("ADMIN")) {
    			ad = true;
    			break;
    		}
    	} 
    	if(!ad) {
    		Logger.getInstance().log("Unsuccessful login atempt with username: "+loginRequest.getUsernameOrEmail());
    		return new ResponseEntity<>("Wrong username or password ", HttpStatus.BAD_REQUEST);
    	}
    	
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = tokenProvider.generateToken(authentication);
        
        Logger.getInstance().log("Successful login with username: "+loginRequest.getUsernameOrEmail());
        return new ResponseEntity<>(new JwtAuthenticationResponse(jwt), HttpStatus.OK);
    	
    }
    
    @GetMapping("/logout")
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
    
    private Authentication autoLogin(String username, String password) {
    	return authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        username,
                        password
                )
        );
    }
}
