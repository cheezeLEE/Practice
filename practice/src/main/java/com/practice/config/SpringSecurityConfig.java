package com.practice.config;

import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.practice.join.service.UserService;

import lombok.RequiredArgsConstructor;

/*
    EnableWebSecurity : SpringSecurityFilterChain이 자동으로 포함됨 / @Configuration이 포함되어 있는 어노테이션
    	EnableWebSecurity, Configuration -> WebSecurityConfiguration -> securityFilterChains
    RequiredArgsConstructor : final이나 @NotNull이 붙은 필드의 생성자를 자동으로 생성해주는 롬복 어노테이션
*/
@EnableWebSecurity
@RequiredArgsConstructor
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter{
	private final UserService userService;
	
	@Bean //회원가입시 비번 암호화에 필요한 bean 등록
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean //실제 인증을 한 이후에 인증이 완료되면 Authentication객체를 반환을 위한 bean등록
	public DaoAuthenticationProvider authenticationProvider(UserService userService) {
		DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
		authenticationProvider.setUserDetailsService(userService);
		authenticationProvider.setPasswordEncoder(passwordEncoder());
		return authenticationProvider;
	}
	
    // 스프링시큐리티 앞단 설정
    @Override
    public void configure(WebSecurity web) throws Exception {
        // 이 메소드가 우선권이 제일 높음
        web.ignoring().antMatchers("/resources/**");
    }
	
	// Spring Security 규칙 설정
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		 http
		 	.authorizeRequests()	// 시큐리티 처리에 HttpServletRequest를 이용함
		 		.antMatchers("/login", "/join").permitAll()	// antMatchers : 특정한 경로를 지정, permitAll() : 모든 사용자가 접근할 수 있음
		 		.antMatchers("/main").hasAuthority("ROLE_USER")	// hasRole() : 시스템상에서 특정 권한을 가진 사람만이 접근할 수 있음 (hasRole은 검색시 자동으로 ROLE_을 붙임, hasAuthority는 ROLE_을 붙이지 않음
		 		.anyRequest().authenticated()	// 나머지 리소스들은 무조건 인증을 완료해야 접근이 가능
		 		.and()
		 	.formLogin()	// form 태그 기반의 로그인을 지원하겠다는 설정
		 		.loginPage("/login")	// login 페이지 지정, 없을 시 시큐리티의 기본 로그인 폼이 지정됨
		 		.loginProcessingUrl("/login")	// login이 수행되는 경로 (from태그의 action값)
		 		.defaultSuccessUrl("/main")		// login이 성공하면 이동하는 경로
		 		.failureUrl("/access_denied")	// login이 실패하면 이동하는 경로
		 		.usernameParameter("userId")
		 		.passwordParameter("userPw")
//		 		.successHandler(new MyLoginSuccessHandler)	// 로그인 성공후 항상 실행하기를 원하는 로직을 추가하기 위해 사용
//		 		.failurHandler(new MyLoginFailHandler)	// 로그인 실패후 항상 실행하기를 원하는 로직을 추가하기 위해 사용
		 		.and()
		 	.logout()	// Logout 관련 설정을 진행할 수 있도록 돕는 LogoutConfigurer<> 클래스를 반환함
		 		.logoutUrl("/logout")	// loginProcessingUrl()과 비슷한 기능으로, Client에서 SpringSecurity에게 logout을 요청하기 위한 url을 설정하는 메소드
		 		.logoutSuccessUrl("/login")	// 로그아웃 성공시 사용자가 redirect될 url을 지정하는 메소드
		 		.and()
		 	.csrf().disable();	// csrf 토큰을 사용하지 않음
	}
	
	// AuthenticationManagerBuilder : 스프링 시큐리티의 인증에 대한 지원을 설정하는 메소드를 가짐
	//								: 인증 설정을 만들기 위한 빌더 스타일 인터페이스를 사용하는 configure()의 인자로 사용됨
	@Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
		// Provider 설정 : 해당 메소드가 호출될 때까지 Bean 등록을 지연시킴 - 암호화 후 인증을 진행
		auth.authenticationProvider(authenticationProvider(userService));
    }
}
