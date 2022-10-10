package com.practice.service;

import java.util.List;
import java.util.Map;

public interface TestService {
	public List<Map<String,Object>> getUserInfo(int userSeq);
	public List<Map<String,Object>> getUserInfo2(int userSeq);
}