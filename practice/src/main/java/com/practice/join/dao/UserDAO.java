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
	
	public int login(UserModel userModel) {
		log.info("login DAO");
		return sqlSessionTemplate.selectOne("userMapper.login", userModel);
	}
}
