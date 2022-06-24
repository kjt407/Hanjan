package com.jongtk.hanjan.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jongtk.hanjan.dto.GroupDTO;
import com.jongtk.hanjan.service.GroupService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/group")
@RequiredArgsConstructor
public class GroupController {
	
	private final GroupService groupService;
	
	@GetMapping({"","/get"})
	public ResponseEntity<List<GroupDTO>> get(){
		List<GroupDTO> result =  groupService.getAll();
		
		return new ResponseEntity<List<GroupDTO>>(result, HttpStatus.OK);
	}
	
	
}
