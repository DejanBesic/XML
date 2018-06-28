package com.agent.app.security;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.security.authentication.event.AuthenticationFailureBadCredentialsEvent;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.stereotype.Component;

import com.agent.app.service.impl.LoginAttemptService;

@Component
public class AuthenticationFailureListener 
  implements ApplicationListener<AuthenticationFailureBadCredentialsEvent> {
 
    @Autowired
    private LoginAttemptService loginAttemptService;
    
    @Autowired
    private HttpServletRequest request;
 
    public void onApplicationEvent(AuthenticationFailureBadCredentialsEvent e) {
        WebAuthenticationDetails auth = (WebAuthenticationDetails) 
          e.getAuthentication().getDetails();
         
        loginAttemptService.loginFailed(request.getRemoteAddr());
    }
}

