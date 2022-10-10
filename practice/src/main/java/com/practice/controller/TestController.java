package com.practice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.practice.service.TestService;

@Controller
public class TestController {
	@Autowired
	private TestService service;


	@GetMapping("/testOracle")
	public String mypage() {

		System.out.println("1:::::::::::"+service.getUserInfo(1));
		return "test/test";
	}
	
	@GetMapping("/testMysql")
	public String mypage2() {
		System.out.println("2:::::::::::"+service.getUserInfo2(1));
		return "test/test";
	}
}