package com.practice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.practice.dto.MailDto;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class MailService {
	
	@Autowired
    private JavaMailSender mailSender;
    
    @Value("${spring.mail.username}")
    private String FROM_ADDRESS;

    public void mailSend(MailDto mailDto) {
        SimpleMailMessage message = new SimpleMailMessage();
        // 받는사람 주소
        message.setTo(mailDto.getAddress());
        // 보내는사람 주소
        log.info("::::::::::::::::::::::::::::::::::"+FROM_ADDRESS);
        message.setFrom(FROM_ADDRESS);
        // 제목
        message.setSubject(mailDto.getTitle());
        // 내용
        message.setText(mailDto.getMessage());

        mailSender.send(message);    	
    }
}