package com.jongtk.hanjan.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.util.AntPathMatcher;
import org.springframework.web.filter.OncePerRequestFilter;

import lombok.extern.log4j.Log4j2;

@Log4j2
public class ApiCheckFilter extends OncePerRequestFilter{ //매번 동작하는 기본적인 필터
	
	private AntPathMatcher antPathMatcher;
	private String pattern;
	
	public ApiCheckFilter(String pattern) {
		//기본 생성자를 통해 antPattern을 입력받아 초기화함
		this.antPathMatcher = new AntPathMatcher();
		this.pattern = pattern;
		
	}

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		
		log.info("API CHECK FILTER 동작함 =======================");
		log.info("API CHECK FILTER 동작함 =======================");
		log.info("API CHECK FILTER 동작함 =======================");
		
		//요청 URI가 사전 설정된 pattern과 일치하다면 다른 비즈니스 로직을 구현함
		if(antPathMatcher.match(pattern, request.getRequestURI())) {
			log.info("API CHECK FILTER 패턴일치 =======================");			
			log.info("API CHECK FILTER 패턴일치 =======================");			
			log.info("API CHECK FILTER 패턴일치  =======================");
			
			return;
		}
		
		filterChain.doFilter(request, response); 	//다음 필터의 단계로 넘겨주는 역할
	}
	
	

}
