package com.jongtk.hanjan.controller;

import java.util.HashMap;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.jongtk.hanjan.dto.MemberDTO;
import com.jongtk.hanjan.service.LoginService;

import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/api/member")
@RequiredArgsConstructor
public class MemberController {
	               
	private final LoginService loginService;
	
	/**
	 * 회원가입: 1단계
	 * 이메일 중복 검사
	 */
	@GetMapping("/duplicate")
	public ResponseEntity<HashMap<String,Object>> checkDuplicationEmail(String email){
		
		HashMap<String,Object> result = new HashMap<>();
		
		boolean duplicate = loginService.checkDuplicationEmail(email);
		
		if(duplicate) {
			result.put("message", "Email '"+email+"' already exists");
			return ResponseEntity.status(HttpStatus.CONFLICT).body(result);
			}
		
		result.put("message", email+" is available email");
		return ResponseEntity.ok().body(result);
	}
	
	/**
	 * 회원가입: 2단계
	 * 이메일 인증 발송
	 */
	@PostMapping("/join")
	public ResponseEntity<HashMap<String,Object>> join(MemberDTO memberDTO){
		
		HashMap<String,Object> result = new HashMap<>();
		Long id;
		
		try {
			id = loginService.join(memberDTO);
		} catch (Exception e) {
			// 회원 가입 에러 발생 시
			result.put("message", "failed to register member");
			return ResponseEntity.internalServerError().body(result);
		}
		
		result.put("message", "success");
		result.put("id", id);
		return ResponseEntity.ok().body(result);
	}
	/**
	 * 회원가입: 3단계
	 * 이메일 토큰 인증
	 */
	@GetMapping("/emailauth/{uuid}")
	public String emailToken(@PathVariable("uuid") String uuid){
		
		boolean result = loginService.verifyEmail(uuid);
		
		return result ? "이메일 인증 성공" : "이메일 인증 실패";
	}

	
	
}
