package com.practice.join.model;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import edu.emory.mathcs.backport.java.util.Collections;
import lombok.Data;

// UserDetails : Spring Security에서 사용자의 정보를 담는 인터페이스
@Data
public class UserModel implements UserDetails{
	private String userId;
	private String userPw;
	private String userName;
	private String userEmail;
	private String validCode;
	private String auth;
	
	// 계정의 권한 목록 리턴
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return Collections.singletonList(new SimpleGrantedAuthority(this.auth));
	}
	
	// 계정의 비밀번호를 리턴
	@Override
	public String getPassword() {
		return this.userPw;
	}
	
	// 계정의 고유한 값을 리턴 (DB PK값, 중복이 없는 이메일 값 등) : 여기서는 사용자 ID
	@Override
	public String getUsername() {
		return this.userId;
	}
	
	// 계정의 만료 여부 리턴 : true - 만료 안됨
	@Override
	public boolean isAccountNonExpired() {
		return true;
	}
	
	// 계정의 잠김 여부 리턴 : true - 잠기지 않음
	@Override
	public boolean isAccountNonLocked() {
		return true;
	}
	
	// 비밀번호 만료 여부 리턴 : true - 만료 안됨
	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}
	
	// 계정의 활성화 여부 리턴 : true -  활성화 됨
	@Override
	public boolean isEnabled() {
		return true;
	}
	
	// userName 데이터가 @Data가 적용되지 않아 수동으로 작성함
	public String getUserName() {
		return this.userName;
	}
}
