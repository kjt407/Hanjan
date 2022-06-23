package com.jongtk.hanjan.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.filter.OncePerRequestFilter;

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
		
		String username = request.getParameter("username");
		String password = "1111";
		
		if(username == null) {
			//AuthenticationException를 구현한 Exception중 하나
			throw new BadCredentialsException("id cannot be null");	
		}
		
		return null;
	} 
	
	

}
