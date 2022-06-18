package com.jongtk.hanjan.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test/")
public class TestController {
	
	@GetMapping("")
	public ResponseEntity<String> get(){
		return new ResponseEntity<>("정상적 반환",HttpStatus.OK);
	}
}
