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
import com.jongtk.hanjan.repository.EmailTokenRepository;
import com.jongtk.hanjan.repository.MemberRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import net.bytebuddy.description.modifier.EnumerationState;
import net.bytebuddy.implementation.bytecode.Throw;

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
	public String createEmailToken(Member member, String email) {
		return null;
	}
	
	
}
