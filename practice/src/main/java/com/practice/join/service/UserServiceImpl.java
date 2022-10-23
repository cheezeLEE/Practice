package com.practice.join.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
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

	// 유저의 정보를 불러와서 UserDetails로 리턴
	@Override
	public UserDetails loadUserByUsername(String userId) throws UsernameNotFoundException {
		// 여기서 받은 유저 패스워드와 비교하여 로그인 인증
		// 로그인을 시도한 계정의 정보를 얻어옴
		UserModel userModel = dao.getAccount(userId);
		
		if(userModel == null) {
			throw new UsernameNotFoundException("User not authorized");
		}
				
		return userModel;
	}

//	@Override
//	public int login(UserModel userModel) {
//		log.info("login service");
//		return dao.login(userModel);
//	}

}
