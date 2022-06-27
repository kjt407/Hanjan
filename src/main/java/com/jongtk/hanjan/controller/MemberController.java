package com.jongtk.hanjan.controller;

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
	public ResponseEntity<Long> join(MemberDTO memberDTO){
		
		Long id = memberService.join(memberDTO);
		
		return new ResponseEntity<Long>(id, HttpStatus.OK);
	}
	
	//Email 중복 검사
	@GetMapping("/duplication")
	public ResponseEntity<JSONObject> checkDuplicationEmail(String email){
		
		JSONObject json = new JSONObject();
		
		boolean duplication = memberService.checkDuplicationEmail(email);
		
		if(duplication) {
			json.put("message", "Email '"+email+"' already exists");
			return new ResponseEntity<JSONObject>(json, HttpStatus.CONFLICT) ;
		}
		
		json.put("message", email+" is available email");
		return new ResponseEntity<JSONObject>(json, HttpStatus.OK) ;
	}
	
	
}
