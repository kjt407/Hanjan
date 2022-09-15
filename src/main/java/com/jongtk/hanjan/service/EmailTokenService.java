package com.jongtk.hanjan.service;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.jongtk.hanjan.entity.EmailToken;
import com.jongtk.hanjan.entity.Member;
import com.jongtk.hanjan.repository.EmailTokenRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;


@Service
@RequiredArgsConstructor
@Transactional
@Log4j2
public class EmailTokenService {
	
	private final EmailTokenRepository emailTokenRepository;
	

	/**
	 * 이메일 인증을 위한 EmailToken 생성
	 * @param member
	 * @param email
	 * @return String '토큰ID'
	 */
	public String createEmailToken(Member member) {
		
		EmailToken token = EmailToken.createToken(member);
		emailTokenRepository.save(token);
		
		return token.getId();
	}
	
}
