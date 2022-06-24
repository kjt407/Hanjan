package com.jongtk.hanjan.filter;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;
import org.springframework.boot.jackson.JsonObjectDeserializer;
import org.springframework.http.HttpStatus;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.StringUtils;
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
		
		//요청 URI가 사전 설정된 pattern과 일치하다면 다른 비즈니스 로직을 구현함
		if(antPathMatcher.match(pattern, request.getRequestURI())) {
			log.info("API CHECK FILTER 패턴일치 =======================");			
			log.info("API CHECK FILTER 패턴일치 =======================");			
			log.info("API CHECK FILTER 패턴일치  =======================");
			
			if(checkAuthHeader(request)) {
				log.info("헤더 검증 완료 =======");
				filterChain.doFilter(request, response);
				return;
			} else {
				// 인증 실패시 response를 통해 HTTPstatus와 Message 직접 전송
				response.setStatus(HttpServletResponse.SC_FORBIDDEN);	//상태코드 403을 지정
				response.setContentType("application/json; charset=utf-8");
				JSONObject json = new JSONObject();
				json.put("code", 403);	//response body에도 코드값을 같이 전달
				json.put("message", "FAIL CHECK API TOKEN");
				
				PrintWriter out =  response.getWriter();
				out.print(json);	//Object를 매개변수로 받을 수 있음
				return;
			}
		}
		
		filterChain.doFilter(request, response); 	//다음 필터의 단계로 넘겨주는 역할
	}
	
	private boolean checkAuthHeader(HttpServletRequest request) {
		
		boolean result = false;
		
		String authHeader = request.getHeader("Authorization");
		if(StringUtils.hasText(authHeader)) {
			if(authHeader.equals("1234"))
			result = true;
		}
		
		return result;
	}
	
	

}
