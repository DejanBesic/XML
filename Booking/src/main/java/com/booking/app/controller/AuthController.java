package com.booking.app.controller;

import java.io.IOException;
import java.util.Random;

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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.booking.app.DTOs.JwtAuthenticationResponse;
import com.booking.app.DTOs.LoginRequest;
import com.booking.app.DTOs.RegistrationResponse;
import com.booking.app.DTOs.RequestResetPassword;
import com.booking.app.DTOs.SignUpRequest;
import com.booking.app.logger.Logger;
import com.booking.app.model.Role;
import com.booking.app.model.User;
import com.booking.app.repository.UserRepository;
import com.booking.app.security.JwtTokenProvider;
import com.booking.app.service.impl.EmailServiceImpl;
import com.booking.app.service.impl.RoleServiceImpl;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleServiceImpl roleService;

    @Autowired
    EmailServiceImpl emailService;
    
    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    JwtTokenProvider tokenProvider;

    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@RequestBody LoginRequest loginRequest) throws IOException {
    	Authentication authentication = null;
    	
    	try {
    		authentication = autoLogin(loginRequest.getUsernameOrEmail(), loginRequest.getPassword());
    	}catch(Exception e){
    		e.printStackTrace();
    		Logger.getInstance().log("Unsuccessful login atempt with username: "+loginRequest.getUsernameOrEmail());
    		return new ResponseEntity<>("Wrong username or password", HttpStatus.BAD_REQUEST);
    	}
    	
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = tokenProvider.generateToken(authentication);
        
        User user = userRepository.findByUsernameOrEmail(loginRequest.getUsernameOrEmail(),loginRequest.getUsernameOrEmail());
        
        if (user == null || !user.isActive()) {
    		Logger.getInstance().log("Unsuccessful login atempt with inactive username: "+loginRequest.getUsernameOrEmail());
        	return new ResponseEntity<>("User is not activated yet", HttpStatus.BAD_REQUEST);
        }
		Logger.getInstance().log("Successful login with username: "+loginRequest.getUsernameOrEmail());
        return new ResponseEntity<>(new JwtAuthenticationResponse(jwt), HttpStatus.OK);
    }
    
    @PostMapping("/resetPassword")
    public ResponseEntity<?> resetPassword(@RequestBody RequestResetPassword email) throws IOException {
    	//ovo doradi da lepo upise
    	//Logger.getInstance().log("Password reset from username: "+email);
    	return null;
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
    
    @RequestMapping(value="/confirmRegistration", method=RequestMethod.PUT)
    public ResponseEntity<?> confirmRegistration(@RequestParam String token) {
    	User user = userRepository.findByToken(token);
    	if (user == null) {
    		return new ResponseEntity<>("Wrong token!", HttpStatus.BAD_REQUEST);
    	}
    	user.setActive(true);
    	userRepository.save(user);
    	return new ResponseEntity<>(null, HttpStatus.OK);
    }
    

    @PostMapping("/signup")
    public ResponseEntity<RegistrationResponse> registerUser(@RequestBody SignUpRequest signUpRequest) {
        if(userRepository.existsByUsername(signUpRequest.getUsername())) {
            return new ResponseEntity<>(new RegistrationResponse(false, "Username is already taken!"), HttpStatus.BAD_REQUEST);
        }

        if(userRepository.existsByEmail(signUpRequest.getEmail())) {
        	return new ResponseEntity<>(new RegistrationResponse(false, "Email address is already in use!"), HttpStatus.BAD_REQUEST);
        }
        
        if (!signUpRequest.getPassword().equals(signUpRequest.getConfirmPassword())) {
        	return new ResponseEntity<>(new RegistrationResponse(false, "Password doesn't match with confirm password"), HttpStatus.BAD_REQUEST);
        }
        
        Role role = roleService.findByName("REGULAR");
        
        // Creating user's account
        User user = new User(signUpRequest.getUsername(), signUpRequest.getEmail(), signUpRequest.getPassword(), role);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        
        String token = getToken(10);
        user.setActive(false);
        user.setToken(token);

        //Save in base
        User result = userRepository.save(user);
        
        if(result == null) {
        	return new ResponseEntity<>(new RegistrationResponse(false, "Server Error"), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        
        String subject = "Registration";
		String messageText = "Please click on the link to complete your registration. "
				+ "http://localhost:3000/confirm/" + token;
		if(!emailService.sendCustomEmail(user.getEmail(), subject, messageText)) {
			return new ResponseEntity<>(new RegistrationResponse(false, "Failed to send email."), HttpStatus.BAD_REQUEST);
		}
        

        return new ResponseEntity<>(new RegistrationResponse(true, "Successfuly registrated."), HttpStatus.OK);
    }
    
    
    private static final Random random = new Random();
    private static final String CHARS = "abcdefghijkmnopqrstuvwxyzABCDEFGHJKLMNOPQRSTUVWXYZ234567890";

    public static String getToken(int length) {
        StringBuilder token = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            token.append(CHARS.charAt(random.nextInt(CHARS.length())));
        }
        return token.toString();
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
