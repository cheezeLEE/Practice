package com.practice.common.service;

import com.practice.common.model.MailDto;

public interface MailService {
	public void testMailSend(MailDto mailDto);
	public void joinMailSend(MailDto mailDto);
	public void validMailSend(MailDto mailDto);
}