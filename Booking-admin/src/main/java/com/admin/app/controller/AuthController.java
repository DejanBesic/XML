package com.admin.app.controller;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.admin.app.model.User;
import com.admin.app.repository.UserRepository;
import com.admin.app.service.MailSender;
import com.admin.app.service.RecaptchaService;
import com.admin.app.service.SecurityService;
import com.admin.app.service.UserService;
import com.admin.app.validator.UserValidator;

@Controller
public class AuthController {
    @Autowired
    private UserService userService;

    @Autowired
    private SecurityService securityService;

    @Autowired
    private UserValidator userValidator;
    
    @Autowired 
    RecaptchaService captchaService;
    
    @Autowired
	private MailSender mailSender;
    
    @Autowired
    private UserRepository userRepository;

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login(Model model, String error, String logout) {
        if (error != null)
            model.addAttribute("error", "Your username and password is invalid.");

        if (logout != null)
            model.addAttribute("message", "You have been logged out successfully.");

        return "login";
    }

    @RequestMapping(value = {"/", "/welcome"}, method = RequestMethod.GET)
    public String welcome(Model model) {
        return "welcome";
    }
    
    @RequestMapping(value="/confirm", method = RequestMethod.GET)
	public String showConfirmationPage(Model model, @RequestParam("token") String token) {
			
		User user = userRepository.findByToken(token);
			
		if (user == null) { // No token found in DB
			return "login";
		} else { // Token found
			
			user.setActive(true);
			userRepository.save(user);
			
//			UserDetails userDetails = userService.loadUser(user.getEmail());
//			Authentication auth = 
//					  new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
//			SecurityContextHolder.getContext().setAuthentication(auth);
			model.addAttribute("message", "Account successfully verified.");
			return "login";
		}
		//return modelAndView;		
	}
}
