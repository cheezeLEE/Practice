package com.practice.config;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.server.Encoding;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.i18n.AcceptHeaderLocaleResolver;

import lombok.extern.slf4j.Slf4j;

@Slf4j
//@Configuration // 설정파일을 의미하는 어노테이션 -> Spring Message 태그 사용을 위해 주석처리
public class MessageTestConfig {

    @Bean // LocaleResolver Bean 생성 : 다국어처리 방법 선택
    public LocaleResolver defaultLocaleResolver() {
        //AceeptHeaderLocaleResolver :  웹 브라우저가 전송한 Accept-Language 헤더로부터 Locale 선택, setLocale() 지원X
        AcceptHeaderLocaleResolver localeResolver = new AcceptHeaderLocaleResolver();
        localeResolver.setDefaultLocale(Locale.KOREAN); // locale의 기본값을 KOREA로 지정

        log.info("localeResolver Bean Created.");
        return localeResolver;
    }

    @Bean // ReloadableResourceBundleMessageSource Bean 생성 : 기본 MessageSource를 대체
    public ReloadableResourceBundleMessageSource messageSource() {
        Locale.setDefault(Locale.KOREAN); // 제공하지 않는 언어로 들어왔을 때 처리

        ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
        messageSource.setBasename("classpath:/messages/messages"); // 읽어올 messages.properties의 경로를 지정함
        messageSource.setDefaultEncoding(Encoding.DEFAULT_CHARSET.toString()); // 인코딩 설정 : DEFAULT_CHARSET -> UTF-8
        messageSource.setDefaultLocale(Locale.getDefault()); // Default Locale은 웹 브라우저가 전송한 값으로 설정
        messageSource.setCacheSeconds(600); // 캐시 주기 설정, 기본값 : forever

        log.info("messageSource Bean Created. Default Charset is {} and Default Locale is {}", Encoding.DEFAULT_CHARSET.toString(), Locale.getDefault());

        return messageSource;
    }

    @Bean // MessageSourceAccessor bean 등록으로 MessageSource를 직접 사용하는 것보다 편리하게 작성
    public MessageSourceAccessor messageSourceAccessor (@Autowired ReloadableResourceBundleMessageSource messageSource) {
    	// 위에서 설정한 messageSource를 autowired로 가져와서 MessageSourceAccessor에 지정
        return new MessageSourceAccessor(messageSource);
    }

}