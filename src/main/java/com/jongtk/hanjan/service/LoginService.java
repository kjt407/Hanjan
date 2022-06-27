package com.jongtk.hanjan.service;

import java.util.Optional;

import javax.persistence.EnumType;
import javax.transaction.Transactional;

import org.hibernate.boot.jaxb.hbm.spi.JaxbHbmTimestampSourceEnum;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.jongtk.hanjan.dto.MemberDTO;
import com.jongtk.hanjan.entity.Gender;
import com.jongtk.hanjan.entity.Member;
import com.jongtk.hanjan.entity.MemberRole;
import com.jongtk.hanjan.repository.MemberRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import net.bytebuddy.description.modifier.EnumerationState;
import net.bytebuddy.implementation.bytecode.Throw;

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
				.email(memberDTO.getEmail())
				.password(passwordEncoder.encode(memberDTO.getPassword()))
				.name(memberDTO.getName())
				.gender(memberDTO.getGender().toLowerCase().contains("f")? Gender.FEMALE : Gender.MALE)
				.build();
		
		member.addMemeberRole(MemberRole.USER);
		
		memberRepository.save(member);
		
		
		log.info("request join Encoded Member ==== ");
		log.info(member.getPassword());

		
		return member.getId();
	}
	
	//Email 중복 검사
	public boolean checkDuplicationEmail(String email){
		
		boolean duplication = false;
		
		Optional<Member> memberOp =  memberRepository.findByEmail(email);
		if(memberOp.isPresent()) duplication = true;
		
		return duplication;
	}
	
	
	//회원조회(ID)
	//회원조회(ALL)
	//회원탈퇴
	
}
