package com.practice.join.model;

import lombok.Data;

@Data
public class UserModel {
	private String userId;
	private String userPw;
	private String userName;
	private String userEmail;
	private String validCode;
	private String auth;
}
