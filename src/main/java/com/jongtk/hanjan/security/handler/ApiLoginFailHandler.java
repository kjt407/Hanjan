package com.jongtk.hanjan.security.handler;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

import lombok.extern.log4j.Log4j2;

//굳이 Bean으로 등록하지 않는다
@Log4j2
public class ApiLoginFailHandler implements AuthenticationFailureHandler{
	//스프링에서 기본적으로 제공하는 인증실패 핸들러
	//REST방식 로그인 실패시 json 타입으로 결과를 반환
	
	@Override
	public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException exception) throws IOException, ServletException {
		
		log.warn("loginFailer Handler ==========");
		log.warn("인증실패 "+exception.getMessage());
		
		response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);	//401
		response.setContentType("application/json; charset=utf-8");		
		JSONObject json = new JSONObject();
		json.put("code", 401);
		json.put("message", exception.getMessage());
		
		PrintWriter out = response.getWriter();
		out.print(json);
	}
	
	
}
