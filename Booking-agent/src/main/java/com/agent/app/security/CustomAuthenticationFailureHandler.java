package com.agent.app.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.WebAttributes;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.stereotype.Component;

@Component
public class CustomAuthenticationFailureHandler extends SimpleUrlAuthenticationFailureHandler{

	@Override
	public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException exception) throws IOException, ServletException {
		// TODO Auto-generated method stub
		super.onAuthenticationFailure(request, response, exception);
		
		String errorMessage = "errorMessage";
		
		if (exception.getMessage().equalsIgnoreCase("User is disabled")) {
            errorMessage = "User is disabled";
        } else if (exception.getMessage().equalsIgnoreCase("User account has expired")) {
            errorMessage = "User account has expired";
        } else if (exception.getMessage().equalsIgnoreCase("blocked")) {
            errorMessage = "Blocked";
        }
		
		request.getSession().setAttribute(WebAttributes.AUTHENTICATION_EXCEPTION, errorMessage);
	}

}
