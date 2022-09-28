package com.practice.common.service;

import java.util.HashMap;
import java.util.Random;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.practice.common.model.MailDto;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class MailServiceImpl implements MailService{
	@Autowired
    private JavaMailSender mailSender;
    
    @Value("${spring.mail.username}")
    private String FROM_ADDRESS;

    private static final String joinTitle = "회원가입 완료 이메일입니다.";
    private static final String joinMessage = "님, 가입을 환영합니다.";
    private static final String validTitle = "인증번호 이메일입니다.";
    private static final String validMessage = "인증번호 전송을 요청한 페이지에 아래 인증코드를 입력해주세요. \n\n";
    
    @Override
    public void testMailSend(MailDto mailDto) {
        SimpleMailMessage message = new SimpleMailMessage();
        // 받는사람 주소
        message.setTo(mailDto.getAddress());
        // 보내는사람 주소
        message.setFrom(FROM_ADDRESS);
        // 제목
        message.setSubject(mailDto.getTitle());
        // 내용
        message.setText(mailDto.getMessage());

        mailSender.send(message);    	
    }
    
    @Override
    public void joinMailSend(MailDto mailDto) {
        SimpleMailMessage message = new SimpleMailMessage();
        // 받는사람 주소
        message.setTo(mailDto.getAddress());
        // 보내는사람 주소
        message.setFrom(FROM_ADDRESS);
        // 제목
        message.setSubject(joinTitle);
        // 내용 : 회원가입 기능 구현후 홍길동 대신 id를 받아오기
        message.setText("홍길동" + joinMessage);

        mailSender.send(message);    	
    }

    @Override
    public void validMailSend(MailDto mailDto) {
        SimpleMailMessage message = new SimpleMailMessage();
        // 받는사람 주소
        message.setTo(mailDto.getAddress());
        // 보내는사람 주소
        message.setFrom(FROM_ADDRESS);
        // 제목
        message.setSubject(validTitle);
        // 내용
		// 임의의 authKey 생성
		Random random = new Random();
		String authKey = String.valueOf(random.nextInt(888888) + 111111);
        message.setText(validMessage + authKey);

        mailSender.send(message);    	
    }

	@Override
	public void joinMailSend2(HttpSession session, HashMap<String, Object> param) {
        SimpleMailMessage message = new SimpleMailMessage();
        // 받는사람 주소
        message.setTo(param.get("address").toString());
        // 보내는사람 주소
        message.setFrom(FROM_ADDRESS);
        // 제목
        message.setSubject(validTitle);
        // 내용
		// 임의의 authKey 생성
		Random random = new Random();
		String authKey = String.valueOf(random.nextInt(888888) + 111111);
        message.setText(validMessage + authKey);
        
        session.setAttribute(param.get("address").toString(), authKey);
        // 인증번호 세션 시간 설정
        session.setMaxInactiveInterval(300);
        
        mailSender.send(message);    			
	}

	/*
	 * 인증번호 체크 서비스로직
	 * 인증완료 : 0 / 인증번호 오류 : 1 / 세션만료 : 2 / 기타에러 : 99
	 */
	@Override
	public int emailCertification(HttpSession session, String address, String inputCode) {
		try {

			if(session.getAttribute(address) != null) {

				String authKey = session.getAttribute(address).toString();
				
				log.info(":::::::::::::::::"+authKey);
				log.info(":::::::::::::::::"+inputCode);
				
				if(authKey.equals(inputCode)) {
					return 0;
				} else {
					return 1;
				}				
			}else {
				log.info("세션이 만료되었습니다.");
				return 2;
			}
			
		}catch(Exception e) {
			log.info("실패...");
			e.printStackTrace();
			return 99;			
		}
	}
}
