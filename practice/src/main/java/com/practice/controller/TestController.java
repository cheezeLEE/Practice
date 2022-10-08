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

		System.out.println(service.getUserInfo(1));


		return "test/test";
	}
}