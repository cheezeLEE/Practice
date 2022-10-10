package com.practice.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.practice.dao.mysql.MysqlDAO;
import com.practice.dao.oracle.OracleDAO;

@Service
public class TestServiceImpl implements TestService {
	
	@Autowired
	private OracleDAO oracleDAO;
	
	@Autowired
	private MysqlDAO mysqlDAO;

	public List<Map<String,Object>> getUserInfo(int userSeq) {
		return oracleDAO.getUserInfo(userSeq);
	}
	public List<Map<String,Object>> getUserInfo2(int userSeq) {
		return mysqlDAO.getUserInfo2(userSeq);
	}
}