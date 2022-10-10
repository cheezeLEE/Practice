package com.practice.join.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.practice.join.model.UserModel;
import com.practice.join.service.UserService;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class UserController {
	
	@Autowired
	private UserService service;
	
    
    @GetMapping("/join")
    public String join() {
    	return "join/join";
    }
    
    @PostMapping("/join")
    public String joinPost(Model model, UserModel userModel) {
    	log.info("join controller");
    	
    	int join = service.join(userModel);
    	
    	log.info("Join Count : "+join);
    	
    	model.addAttribute("user", userModel);
    	return "join/joinResult";
    }
}
