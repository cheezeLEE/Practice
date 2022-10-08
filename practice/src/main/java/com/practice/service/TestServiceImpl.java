package com.practice.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.practice.dao.TestDAO;

@Service
public class TestServiceImpl implements TestService {
	@Autowired
	private TestDAO testDAO;

	public List<Map<String,Object>> getUserInfo(int userSeq) {
		return testDAO.getUserInfo(userSeq);
	}
}