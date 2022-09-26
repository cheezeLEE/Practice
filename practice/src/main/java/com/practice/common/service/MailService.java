package com.practice.common.service;

import java.util.HashMap;

import javax.servlet.http.HttpSession;

import com.practice.common.model.MailDto;

public interface MailService {
	public void testMailSend(MailDto mailDto);
	public void joinMailSend(MailDto mailDto);
	public void validMailSend(MailDto mailDto);

	public void joinMailSend2(HttpSession session, HashMap<String, Object> param);
	public boolean emailCertification(HttpSession session, String address, String inputCode);
	
	
}