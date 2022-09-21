package com.jongtk.hanjan.advice;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionAdvice {
	
	@ExceptionHandler(RuntimeException.class)
	public ResponseEntity<ErrorResponse> re(RuntimeException e) {
		
		ErrorResponse response = ErrorResponse.builder()
			.code(500)
			.message(e.getMessage())
			.build();
		
		return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
	}

}
