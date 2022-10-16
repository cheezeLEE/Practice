package com.practice.join.controller;

import java.util.Locale;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

import com.practice.join.model.UserModel;
import com.practice.join.service.UserService;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class UserController {
	
	@Autowired
	private UserService service;
	
	@GetMapping("/login")
	public String login() {
		return "login";
	}
	
	@PostMapping("/login")
	public String loginPost(Model model, UserModel userModel) {
		log.info("login controller");
		
		int login = service.login(userModel);
				
		if(login == 1) {
			log.info("login success");
			model.addAttribute("user", userModel);			
			return "main/main";
		}else {
			log.info("login fail");
			log.info("input password : " + userModel.getUserPw());			
			return "login";
		}		
	}
    
    @GetMapping("/join")
    public String join(@Nullable@RequestParam("language") String language, HttpSession session, Model model) {
    	
    	if(language == null) {
    		language = "ko";
    	}    		

    	session.setAttribute(SessionLocaleResolver.LOCALE_SESSION_ATTRIBUTE_NAME, new Locale(language));
    	model.addAttribute("language", language);
    	
    	return "join/join";
    }
    
    @PostMapping("/join")
    public String joinPost(Model model, UserModel userModel) {
    	log.info("join controller");
    	
    	int join = service.join(userModel);
    	
    	log.info("Join Count : "+join);
    	
    	model.addAttribute("user", userModel);
    	return "login";
    }        
}
