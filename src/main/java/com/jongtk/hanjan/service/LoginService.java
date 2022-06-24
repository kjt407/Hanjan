package com.jongtk.hanjan.service;

import javax.transaction.Transactional;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.jongtk.hanjan.dto.MemberDTO;
import com.jongtk.hanjan.entity.Gender;
import com.jongtk.hanjan.entity.Member;
import com.jongtk.hanjan.entity.MemberRole;
import com.jongtk.hanjan.repository.MemberRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Service
@RequiredArgsConstructor
@Transactional
@Log4j2
public class LoginService {
	
	private final MemberRepository memberRepository;
	private final PasswordEncoder passwordEncoder;
	
	
	//회원가입
	public Long join(MemberDTO memberDTO) {
		
		log.info("request join Member ==== ");
		log.info(memberDTO);
		
		Member member = Member.builder()
				.username(memberDTO.getUsername())
				.password(passwordEncoder.encode(memberDTO.getPassword()))
				.email(memberDTO.getEmail())
				.name(memberDTO.getName())
				.gender(Gender.MALE)
				.build();
		
		member.addMemeberRole(MemberRole.USER);
		
		memberRepository.save(member);
		
		
		log.info("request join Encoded Member ==== ");
		log.info(member.getPassword());

		
		return member.getId();
	}
	//회원조회(ID)
	//회원조회(ALL)
	//회원탈퇴
	
}
