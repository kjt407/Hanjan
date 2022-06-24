package com.jongtk.hanjan.security.filter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.web.server.MethodNotAllowedException;

import lombok.extern.log4j.Log4j2;

@Log4j2
public class ApiLoginFilter extends AbstractAuthenticationProcessingFilter{//인증진행 추상 필터 클래스
	
	public ApiLoginFilter(String defaultFilterProcessesUrl) {	// 생성자를 통해 Path를 지정
		super(defaultFilterProcessesUrl);
	}

	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
			throws AuthenticationException, IOException, ServletException {
		// Authentication을 반환함
		log.info("ApiLoginFilter=============");
		log.info(request.getMethod());

		if(!	request.getMethod().equals("POST")) {
			List<HttpMethod> allowMethods = new ArrayList<>();
			allowMethods.add(HttpMethod.POST);
			throw new MethodNotAllowedException(request.getMethod(),allowMethods);				
		}
		
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		
		if(username == null) {
			log.info("아이디는 null일 수 없습니다.");
			//AuthenticationException를 구현한 Exception중 하나
			throw new BadCredentialsException("id cannot be null");	
		}
		
		UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(username, password);
		
		return getAuthenticationManager().authenticate(token);
	} 
	
	

}
