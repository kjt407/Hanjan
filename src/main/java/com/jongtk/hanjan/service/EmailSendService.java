package com.jongtk.hanjan.service;


import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;


@Service
@RequiredArgsConstructor
@Log4j2
public class EmailSendService {	
	
	private final JavaMailSender mailSender;

	@Async
	public void sendEmail(String emailAddr, String tokenUid) {
		log.info("======= 이메일 발송: " + emailAddr);
		SimpleMailMessage message = new SimpleMailMessage();
		message.setFrom("devkjt40745@gmail.com");
		message.setTo(emailAddr);
		message.setSubject("[Hanjan] 이메일 인증을 완료해 주세요.");;
		message.setText("http://localhost:8080/api/member/emailauth/"+tokenUid);
		mailSender.send(message); 
	}
	
}
