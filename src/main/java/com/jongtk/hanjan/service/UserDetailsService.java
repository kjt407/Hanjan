package com.jongtk.hanjan.service;

import java.util.Optional;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.jongtk.hanjan.entity.Member;
import com.jongtk.hanjan.repository.MemberRepository;
import com.jongtk.hanjan.security.dto.AuthMemberDTO;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Service
@Log4j2
@RequiredArgsConstructor
public class UserDetailsService implements org.springframework.security.core.userdetails.UserDetailsService{
	 //UserDetailsService를 구현
	
	private final MemberRepository memberRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		//인증을 위한 로직을 직접 구현 가능
		
		log.info("loadUserByUsername 호출됨 ==== "+username);
		
		Optional<Member> memberOp = memberRepository.findByEmail(username);
		
		if(memberOp.isEmpty()) {
			// 입력된 아이디를 가진 회원이 있는지 검증
			throw new UsernameNotFoundException("Check your Account");
		}
		
		Member member = memberOp.get();
		
		log.info("Member = "+member.getName());
		
		// SpringSecurity User가 의도한 대로 GrantedAuthority를 생성해서 반환
		AuthMemberDTO authMemberDTO = new AuthMemberDTO(
				member.getEmail(), 
				member.getPassword(),
				member.getName(),
				member.getEmail(),
				member.getRoleSet().stream().map(
						role -> new SimpleGrantedAuthority("ROLE_"+role.name())
						).collect(Collectors.toSet())
				);
		authMemberDTO.setId(member.getId());
		
		return authMemberDTO;
	}
	
	
}
