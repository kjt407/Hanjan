package com.jongtk.hanjan.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jongtk.hanjan.dto.GroupDTO;
import com.jongtk.hanjan.security.dto.AuthMemberDTO;
import com.jongtk.hanjan.service.GroupServiceImp;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@RestController
@RequestMapping("/api/group")
@RequiredArgsConstructor
@Log4j2
public class GroupController {
	
	private final GroupServiceImp groupService;
	
	/*
	 * 전체 그룹 목록
	 */
	@GetMapping("/list")
	public ResponseEntity<List<GroupDTO>> list(){
		List<GroupDTO> result =  groupService.getAll();
		
		return new ResponseEntity<List<GroupDTO>>(result, HttpStatus.OK);
	}
	
	/*
	 * 내 그룹 목록
	 */
	@GetMapping("/my")
	public ResponseEntity<List<GroupDTO>> myList(@AuthenticationPrincipal AuthMemberDTO authMember){
		List<GroupDTO> result =  groupService.getMyGroups(authMember.getId());
		
		return new ResponseEntity<List<GroupDTO>>(result, HttpStatus.OK);
	}
	
	/*
	 * 새 그룹 호스팅
	 */
	@PostMapping("/host")
	public ResponseEntity<Long> hostGroup(@AuthenticationPrincipal AuthMemberDTO authMember){
		
		log.info("그룹 호스팅: 로그인 유저 = " + authMember);
		
		
		return ResponseEntity.ok(10L);
	}
	
	/*
	 * 특정 그룹에 가입
	 */
	@PostMapping("/host")
	public ResponseEntity<String> joinGroup(@AuthenticationPrincipal AuthMemberDTO authMember, Long groupId){
		groupService.join(authMember.getId(), groupId);
		
		return ResponseEntity.ok("success");
	}
}
