package com.jongtk.hanjan.controller;

import java.util.HashMap;
import java.util.Map;

import org.json.simple.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jongtk.hanjan.dto.MemberDTO;
import com.jongtk.hanjan.service.LoginService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/member")
@RequiredArgsConstructor
public class MemberController {
	               
	private final LoginService memberService;
	
	//회원가입
	@PostMapping({"/join"})
	public ResponseEntity<HashMap<String,Object>> join(MemberDTO memberDTO){
		
		HashMap<String,Object> result = new HashMap<>();
		Long id;
		
		try {
			id = memberService.join(memberDTO);
		} catch (Exception e) {
			// 회원 가입 에러 발생 시
			result.put("message", "failed to register member");
			return ResponseEntity.internalServerError().body(result);
		}
		
		result.put("message", "success");
		result.put("id", id);
		return ResponseEntity.ok().body(result);
	}
	
	//Email 중복 검사
	@GetMapping("/duplicate")
	public ResponseEntity<HashMap<String,Object>> checkDuplicationEmail(String email){
		
		HashMap<String,Object> result = new HashMap<>();
		
		boolean duplicate = memberService.checkDuplicationEmail(email);
		
		if(duplicate) {
			result.put("message", "Email '"+email+"' already exists");
			return ResponseEntity.status(HttpStatus.CONFLICT).body(result);
			}
		
		result.put("message", email+" is available email");
		return ResponseEntity.ok().body(result);
	}
	
	
}
