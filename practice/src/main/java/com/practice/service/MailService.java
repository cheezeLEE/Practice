package com.practice.service;

import com.practice.dto.MailDto;

public interface MailService {
	public void testMailSend(MailDto mailDto);
	public void joinMailSend(MailDto mailDto);
	public void validMailSend(MailDto mailDto);
}