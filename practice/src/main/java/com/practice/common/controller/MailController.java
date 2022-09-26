package com.practice.common.controller;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.practice.common.model.MailDto;
import com.practice.common.service.MailService;

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

	@RequestMapping(value="/mailJoin2", method = RequestMethod.POST)
	@ResponseBody
    public void execMailJoin2(@RequestParam HashMap<String, Object> param, HttpServletRequest request) {		
		log.info("인증번호 이메일 전송");
		
		HttpSession session = request.getSession();
		
        mailService.joinMailSend2(session, param);
    }
	
	@RequestMapping(value="/certification", method = RequestMethod.POST)
	@ResponseBody
	private boolean emailCertification(HttpServletRequest request, String address, String inputCode) {
		HttpSession session = request.getSession();
		boolean result = mailService.emailCertification(session, address, inputCode);
		
		return result;
	}

}