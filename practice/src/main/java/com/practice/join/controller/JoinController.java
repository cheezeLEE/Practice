package com.practice.join.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.practice.join.model.User;
import com.practice.join.service.JoinService;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class JoinController {
	
	@Autowired
	private JoinService service;
	
    
    @GetMapping("/join")
    public String join() {
    	return "join/join";
    }
    
    @PostMapping("/join")
    public String joinPost(Model model, User user) {
    	model.addAttribute("user", user);
    	return "join/joinResult";
    }
}
