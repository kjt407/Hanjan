package com.jongtk.hanjan.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test/")
public class TestController {
	
	@GetMapping("get")
	public ResponseEntity<String> get(){
		return new ResponseEntity<>("정상적 반환",HttpStatus.OK);
	}
	
	@PostMapping("post")
	public ResponseEntity<String> post(){
		return new ResponseEntity<>("정상적 반환",HttpStatus.OK);
	}
	
	@PutMapping("put")
	public ResponseEntity<String> put(){
		return new ResponseEntity<>("정상적 반환",HttpStatus.OK);
	}
	@DeleteMapping("delete")
	public ResponseEntity<String> delete(){
		return new ResponseEntity<>("정상적 반환",HttpStatus.OK);
	}
}
