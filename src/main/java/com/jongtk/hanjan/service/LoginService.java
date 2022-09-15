package com.jongtk.hanjan.service;

import java.time.LocalDateTime;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.jongtk.hanjan.dto.MemberDTO;
import com.jongtk.hanjan.entity.EmailToken;
import com.jongtk.hanjan.entity.Gender;
import com.jongtk.hanjan.entity.Member;
import com.jongtk.hanjan.entity.MemberRole;
import com.jongtk.hanjan.repository.EmailTokenRepository;
import com.jongtk.hanjan.repository.MemberRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Service
@RequiredArgsConstructor
@Transactional
@Log4j2
public class LoginService {
	
	private final MemberRepository memberRepository;
	private final EmailTokenRepository emailTokenRepository;
	private final PasswordEncoder passwordEncoder;
	private final EmailTokenService emailTokenService;
	private final EmailSendService emailSendService;
	
	
	/**
	 * 회원가입: 1단계(이메일 인증 전)
	 * @param memberDTO
	 * @throws Exception 
	 */
	public Long join(MemberDTO memberDTO) throws Exception {
		
		log.info("request join Member ==== ");
		log.info(memberDTO);
		
		Optional<Member> memberOp = memberRepository.findByEmail(memberDTO.getEmail());
		if(memberOp.isPresent()) {
			// 이미 존재하는 회원일 경우
			throw new Exception("already Exist");
		}
		
		Member member = Member.builder()
				.email(memberDTO.getEmail())
				.password(passwordEncoder.encode(memberDTO.getPassword()))
				.name(memberDTO.getName())
				.gender(memberDTO.getGender().toLowerCase().contains("f")? Gender.FEMALE : Gender.MALE)
				.emailVerified(false)
				.build();
		
		member.addMemeberRole(MemberRole.USER);
		
		memberRepository.save(member);
		
		// 이메일 인증용 토큰 발행
		String tokenUid = emailTokenService.createEmailToken(member);
		
		// 이메일 발송
		emailSendService.sendEmail(member.getEmail(), tokenUid);
		
		return member.getId();
	}
	
	/**
	 * Email 인증
	 * @param uuid
	 */
	public boolean verifyEmail(String uuid) {
		
		Optional<EmailToken> tokenOp = emailTokenRepository.findById(uuid);
		if(tokenOp.isPresent()) {
			EmailToken emailToken = tokenOp.get();
			LocalDateTime expireDate = emailToken.getExpireDate();
			LocalDateTime nowDate = LocalDateTime.now();
			if(expireDate.isAfter(nowDate)) {
				Member member = emailToken.getMember();
				member.verifiedEmail();
				memberRepository.save(member);
				
				return true;
			}
		}
		
		return false;
	}
	
	/**
	 * Email 중복 검사
	 * @param email
	 */
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
