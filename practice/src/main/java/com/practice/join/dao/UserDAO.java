package com.practice.join.dao;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.practice.join.model.UserModel;

import lombok.extern.slf4j.Slf4j;

@Repository
@Slf4j
public class UserDAO {

	@Autowired
	@Qualifier("mysqlSqlSession")
	private SqlSessionTemplate sqlSessionTemplate;
	
	public int join(UserModel userModel) {
		log.info("join DAO");
		return sqlSessionTemplate.insert("userMapper.join",userModel);
	}
	
//	public int login(UserModel userModel) {
//		log.info("login DAO");
//		return sqlSessionTemplate.selectOne("userMapper.login", userModel);
//	}
	
	// 로그인을 시도한 아이디에 대한 계정정보를 가져옴 -> 비밀번호 암호화를 수행하기 위해서
	public UserModel getAccount(String userId) {
		log.info("GetAccount : " + userId);
		return sqlSessionTemplate.selectOne("userMapper.getAccount", userId);
	}

}
