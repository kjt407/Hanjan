package com.jongtk.hanjan.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
	
	@PostMapping({"/join"})
	public ResponseEntity<Long> join(MemberDTO memberDTO){
		
		Long id = memberService.join(memberDTO);
		
		return new ResponseEntity<Long>(id, HttpStatus.OK);
	}
	
	
}
