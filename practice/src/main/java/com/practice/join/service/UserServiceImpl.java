package com.practice.join.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.practice.join.dao.UserDAO;
import com.practice.join.model.UserModel;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserDAO dao;
	
	@Override
	public int join(UserModel userModel) {
		log.info("join service");
		return dao.join(userModel);
	}

}
