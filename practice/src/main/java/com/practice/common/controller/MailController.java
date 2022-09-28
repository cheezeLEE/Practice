package com.practice.common.controller;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
	private ResponseEntity<String> emailCertification(HttpServletRequest request, String address, String inputCode) {
		HttpSession session = request.getSession();
		int result = mailService.emailCertification(session, address, inputCode);
		
		if(result == 0) {
			return new ResponseEntity<>("인증완료", HttpStatus.OK); // 200
		}else if(result == 1) {
			return new ResponseEntity<>("인증번호 오류", HttpStatus.BAD_REQUEST); // 400
		}else if(result == 2) {
			return new ResponseEntity<>("세션 만료", HttpStatus.UNAUTHORIZED);	 // 401
		}else if(result == 99) {
			return new ResponseEntity<>("문법 오류", HttpStatus.INTERNAL_SERVER_ERROR); // 500
		}else {
			return new ResponseEntity<>("정체불명의 오류", HttpStatus.INTERNAL_SERVER_ERROR); //500
		}
	}

}