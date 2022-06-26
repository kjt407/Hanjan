package com.jongtk.hanjan.security.filter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.FilterChain;
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

import com.jongtk.hanjan.security.dto.AuthMemberDTO;
import com.jongtk.hanjan.security.util.JWTUtil;

import lombok.extern.log4j.Log4j2;

@Log4j2
public class ApiLoginFilter extends AbstractAuthenticationProcessingFilter{//인증진행 추상 필터 클래스
	
	private JWTUtil jwtUtil;
	
	public ApiLoginFilter(String defaultFilterProcessesUrl, JWTUtil jwtUtil) {	// 생성자를 통해 Path를 지정
		super(defaultFilterProcessesUrl);
		this.jwtUtil = jwtUtil;
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

	@Override
	protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
			Authentication authResult) throws IOException, ServletException { //인증 성공시 토큰을 발급하여 클라이언트에게 응답

		String username = ((AuthMemberDTO)authResult.getPrincipal()).getUsername();
		
		String token = null;
		try {
			token = jwtUtil.generateToken(username);
			
			response.setContentType("text/plain");
			response.getOutputStream().write(token.getBytes());
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	} 
	
	
	
	

}
