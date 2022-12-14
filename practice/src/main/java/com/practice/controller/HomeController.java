package com.practice.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/")
    public String index() {
        return "index";
    }
	
    @GetMapping("/test")
    public String test() {
        return "test/test";
    }
    
    @GetMapping("/timer")
    public String timer() {
    	return "test/timer";
    }
}
