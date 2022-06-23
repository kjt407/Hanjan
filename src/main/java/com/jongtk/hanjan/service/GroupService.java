package com.jongtk.hanjan.service;

import org.springframework.stereotype.Service;

import com.jongtk.hanjan.entity.Group;
import com.jongtk.hanjan.entity.Member;
import com.jongtk.hanjan.entity.MemberGroup;
import com.jongtk.hanjan.repository.GroupRepository;
import com.jongtk.hanjan.repository.MemberRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class GroupService {
	
	private final GroupRepository groupRepository;
	private final MemberRepository memberRepository;
	
	//그룹생성
	public Long hostGroup(Long memberId, String title, String content) {
		
		Member member = memberRepository.findById(memberId).get();
		MemberGroup memberGroup = MemberGroup.createMemberGroup(member);
		Group group = Group.createGroup(member, title, content, memberGroup);
		
		groupRepository.save(group);
		
		return group.getId(); 
	}
	
	//그룹가입
	public void joinGroup(Long memberId, Long groupId) {
		
		Member member = memberRepository.findById(memberId).get();
		Group group = groupRepository.findById(groupId).get();
		
		MemberGroup memberGroup = MemberGroup.createMemberGroup(member);
		group.addMemberGroup(memberGroup);
		
		groupRepository.save(group);
	}
	
	//그룹탈퇴
	
	//그룹조회(ALL)
	//그룹조회(My)
	//그룹조회(findHost)
	//그룹조회(findMembers)
	
	//모임날짜생성
	
}
