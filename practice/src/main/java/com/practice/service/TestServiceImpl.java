package com.practice.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.practice.dao.mysql.MysqlDAO;
import com.practice.dao.oracle.OracleDAO;

import net.nurigo.java_sdk.api.Message;
import net.nurigo.java_sdk.exceptions.CoolsmsException;

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
	
	public String PhoneNumberCheck(String to) throws CoolsmsException {
			
		// CoolSMS 사이트에서 본인의 api_key를 발급
		String api_key = "NCSVDKPCE0XCKFJP";
		String api_secret = "SFOXW8LUK8E8H7SLC5M4PAMN5Q8JVQK0";
		Message coolsms = new Message(api_key, api_secret);
		
		// 6자리 난수 생성
		Random rand  = new Random();
	    String numStr = "";
	    for(int i=0; i<6; i++) {
	       String ran = Integer.toString(rand.nextInt(10));
	       numStr+=ran;
	    }          
	
	    HashMap<String, String> params = new HashMap<String, String>();
	    params.put("to", to);    // 수신전화번호 (ajax로 view 화면에서 받아온 값으로 넘김)
	    params.put("from", "01088949752");    // 발신전화번호. 테스트시에는 발신,수신 둘다 본인 번호로 하면 됨
	    params.put("type", "sms"); 
	    params.put("text", "인증번호는 [" + numStr + "] 입니다.");
	
	    coolsms.send(params); // 메시지 전송
	        
	    return numStr;
	
	}
}