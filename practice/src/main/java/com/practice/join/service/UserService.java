package com.practice.join.service;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.practice.join.model.UserModel;

public interface UserService extends UserDetailsService{
	public int join(UserModel userModel);

	// 로그인은 시큐리티에서 자동으로 동작함
//	public int login(UserModel userModel);
	
}
