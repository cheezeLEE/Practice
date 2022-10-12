package com.practice.controller;

import java.util.ArrayList;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.practice.service.TestService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Controller
public class TestController {

    private final MessageSourceAccessor messageSource;

	@Autowired
	private TestService service;


	@GetMapping("/testOracle")
	public String mypage() {

		System.out.println("1:::::::::::"+service.getUserInfo(1));
		return "test/test";
	}
	
	@GetMapping("/testMysql")
	public String mypage2() {
		System.out.println("2:::::::::::"+service.getUserInfo2(1));
		return "test/test";
	}

	// Messages.properties에 있는 값을 사용하여 Rest방식으로 데이터 전달
	// Spring Message 태그 사용을 위해 주석처리
//    @GetMapping("/testcall")
//    @ResponseBody
//    public ArrayList<String> test (Locale locale){
//
//    	// replace.test="ko 여기에 {0}값을 숫자 {1} 형태로 넣으면 치환변수로 쓸 수 있어요." 의 {0}, {1}에 할당될 값을 배열로 저장 
//        String[] replaceValues = new String[]{"바인딩변수1번값", "바인딩변수2번값"};
//
//        ArrayList<String> msgs = new ArrayList<>();
//        // messages.properties가 key=value 형식으로 작성되어 key를 호출하면 value가 ArrayList에 저장됨
//        // 기본값이 ko이므로(한국에서 접속하고 설정을 변경하지 않았기 때문) messages_ko.properties가 출력됨
//        msgs.add(messageSource.getMessage("test")); // ko test
//        msgs.add(messageSource.getMessage("like.this.comma.ok")); // ko 이렇게 콤마를 넣어서 구분값으로 써도 됩니다.
//        msgs.add(messageSource.getMessage("100")); // ko 숫자는 자동으로 String Key로 인식돼요
//        msgs.add(messageSource.getMessage("replace.test", replaceValues)); // ko 여기에 바인딩변수1번값값을 숫자 바인딩변수2번값 형태로 넣으면 치환변수로 쓸 수 있어요.
//
//        msgs.add(messageSource.getMessage("test", Locale.ENGLISH)); // Locale을 ENGLISH로 지정 -> messages_en.properties가 출력됨
//        msgs.add(messageSource.getMessage("like.this.comma.ok", Locale.ENGLISH));
//        msgs.add(messageSource.getMessage("100", Locale.ENGLISH));
//        msgs.add(messageSource.getMessage("replace.test", replaceValues,  Locale.ENGLISH));
//
//        msgs.add(messageSource.getMessage("test", locale)); // Locale의 기본값이 ko이므로 따로 설정하지 않으면 messages_ko.properties가 설정됨
//        msgs.add(messageSource.getMessage("like.this.comma.ok", locale));
//        msgs.add(messageSource.getMessage("100", locale));
//        msgs.add(messageSource.getMessage("replace.test", replaceValues, locale));
//
//        return msgs;
//        /* msgs 결과값
//         [
//		    "ko test",
//		    "ko 이렇게 콤마를 넣어서 구분값으로 써도 됩니다.",
//		    "ko 숫자는 자동으로 String Key로 인식되요",
//		    "ko 여기에 바인딩변수1번값값을 숫자 바인딩변수2번값 형태로 넣으면 치환변수로 쓸 수 있어요.",
//		    "en test",
//		    "en 이렇게 콤마를 넣어서 구분값으로 써도 됩니다.",
//		    "en 숫자는 자동으로 String Key로 인식되요",
//		    "en 여기에 바인딩변수1번값값을 숫자 바인딩변수2번값 형태로 넣으면 치환변수로 쓸 수 있어요.",
//		    "ko test",
//		    "ko 이렇게 콤마를 넣어서 구분값으로 써도 됩니다.",
//		    "ko 숫자는 자동으로 String Key로 인식되요",
//		    "ko 여기에 바인딩변수1번값값을 숫자 바인딩변수2번값 형태로 넣으면 치환변수로 쓸 수 있어요."
//		]
//         */
//    }
	
	@GetMapping("/messageTest")
	public String messageTest() {
		return "test/messageTest";
	}

}