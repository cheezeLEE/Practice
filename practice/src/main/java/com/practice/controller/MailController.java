package com.practice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.practice.dto.MailDto;
import com.practice.service.MailService;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class MailController {
	
	@Autowired
    private MailService mailService;

    @GetMapping("/mail")
    public String dispMail() {
        return "mail";
    }

    @PostMapping("/mail")
    public void execMail(MailDto mailDto) {
    	log.info("::::::::::::::::: " + mailDto.getTitle() + ", " + mailDto.getAddress() + ", " + mailDto.getMessage());
        mailService.mailSend(mailDto);
    }
}