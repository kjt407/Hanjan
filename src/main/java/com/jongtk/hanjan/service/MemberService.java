package com.jongtk.hanjan.service;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.jongtk.hanjan.dto.MemberDTO;
import com.jongtk.hanjan.entity.Gender;
import com.jongtk.hanjan.entity.Member;
import com.jongtk.hanjan.repository.MemberRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class MemberService {
	
	private final MemberRepository memberRepository;
	
	//회원가입
	public Long join(MemberDTO memberDTO) {
		
		Member member = Member.builder()
				.username(memberDTO.getUsername())
				.password(memberDTO.getPassword())
				.email(memberDTO.getEmail())
				.name(memberDTO.getName())
				.gender(Gender.MALE)
				.build();
		
		memberRepository.save(member);
		
		return member.getId();
	}
	//회원조회(ID)
	//회원조회(ALL)
	//회원탈퇴
	
}
