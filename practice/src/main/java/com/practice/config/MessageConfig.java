package com.practice.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.context.support.ResourceBundleMessageSource;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Configuration
public class MessageConfig {
	
	@Bean // 빈 이름은 무조건 messageSource 여야 함 !!
	public MessageSource messageSource() { 
	   
	    // 메시지 파일로 프로퍼티 형식 사용을 위한 MessageSource 구현체 클래스
	    ResourceBundleMessageSource ms = new ResourceBundleMessageSource();
	    ms.setBasenames("messages.messages"); // messages는 패키지명으로, 여기서 messages라는 폴더를 사용했다.
	    ms.setDefaultEncoding("UTF-8");
	    return ms;
	}
	
    @Bean
    public MessageSourceAccessor messageSourceAccessor (@Autowired MessageSource ms) {
        return new MessageSourceAccessor(ms);
    }

}
