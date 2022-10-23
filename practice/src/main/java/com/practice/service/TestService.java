package com.practice.service;

import java.util.List;
import java.util.Map;

import net.nurigo.java_sdk.exceptions.CoolsmsException;

public interface TestService {
	public List<Map<String,Object>> getUserInfo(int userSeq);
	public List<Map<String,Object>> getUserInfo2(int userSeq);
	// SMS 테스트
	public String PhoneNumberCheck(String to) throws CoolsmsException;
}