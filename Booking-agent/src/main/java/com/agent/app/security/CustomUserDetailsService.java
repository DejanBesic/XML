package com.agent.app.security;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.agent.app.model.User;
import com.agent.app.service.impl.LoginAttemptService;
import com.agent.app.ws.WSUserClient;
import com.agent.app.wsdl.UserFromIdRequest;
import com.agent.app.wsdl.UserRequest;
import com.agent.app.wsdl.UserWS;


@Service
public class CustomUserDetailsService implements UserDetailsService {

	@Autowired
	WSUserClient userClient;
	
	@Autowired
    private LoginAttemptService loginAttemptService;
	
	@Autowired
    private HttpServletRequest request;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String usernameOrEmail)
            throws UsernameNotFoundException {
        // Let people login with either username or email
        //User user = userRepository.findByUsernameOrEmail(usernameOrEmail, usernameOrEmail);

    	String ip = request.getRemoteAddr();
        if (loginAttemptService.isBlocked(ip)) {
            throw new RuntimeException("blocked");
        }
    	
    	UserRequest request = new UserRequest();
    	request.setUsername(usernameOrEmail);
    	request.setPassword("");
    	UserWS userWS = userClient.loginUser(request);
    	
    	if(userWS==null)
    		throw new UsernameNotFoundException("No user found with username " + usernameOrEmail);
    	
        return UserPrincipal.create(ws2User(userWS));
    }

    @Transactional
    public UserDetails loadUserById(Long id) {
    	
    	UserFromIdRequest request = new UserFromIdRequest();
    	request.setId(id);
    	
    	UserWS userWS = userClient.getUserById(request);
    	
    	User user = ws2User(userWS);
    	
        //User user = userRepository.findById(id);
        return UserPrincipal.create(user);
    }
    
    
    private User ws2User(UserWS ws){
    	User user = new User();
    	user.setActive(true);
    	user.setAddress(ws.getAddress());
    	user.setEmail(ws.getEmail());
    	user.setName(ws.getName());
    	user.setLastName(ws.getLastName());
    	user.setPmb(ws.getPmb());
    	user.setToken(ws.getToken());
    	user.setId(ws.getId());
    	user.setUsername(ws.getUsername());
    	user.setPassword(ws.getPassword());
    	
    	return user;
    }
    
    private String getClientIP() {
        String xfHeader = request.getHeader("X-Forwarded-For");
        if (xfHeader == null){
            return request.getRemoteAddr();
        }
        return xfHeader.split(",")[0];
    }
}