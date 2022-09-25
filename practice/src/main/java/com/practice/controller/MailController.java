package com.practice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.practice.dto.MailDto;
import com.practice.service.MailService;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class MailController {
	
	@Autowired
    private MailService mailService;

    @GetMapping("/mailTest")
    public String dispTestMail() {
        return "mailTest";
    }

	@RequestMapping(value="/mailTest", method = RequestMethod.POST)
	@ResponseBody
    public void execMailTest(MailDto mailDto) {
        mailService.testMailSend(mailDto);
    }

	@RequestMapping(value="/mailJoin", method = RequestMethod.POST)
	@ResponseBody
    public void execMailJoin(MailDto mailDto) {
        mailService.joinMailSend(mailDto);
    }

	@RequestMapping(value="/mailValid", method = RequestMethod.POST)
	@ResponseBody
    public void execMailValid(MailDto mailDto) {
        mailService.validMailSend(mailDto);
    }

}