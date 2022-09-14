package com.jongtk.hanjan.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.GenericGenerator;

import lombok.Getter;


@Entity
@Getter
public class EmailToken extends BaseEntity{

	// 생성전략을 UUID로 지정하여 id값을 토큰으로 활용함
	@Id @GeneratedValue(generator = "uuid2")
	@GenericGenerator(name = "uuid2", strategy = "uuid2")
	@Column(name = "EMAIL_TOKEN_ID")
	private String id;
	
	// 토큰에서 만료 여부를 확인할때 Member 엔티티는 탐색하지 않음 (지연 로딩 처리)
	@ManyToOne(fetch = FetchType.LAZY)
	private Member member;
	
	private boolean expired;
		
	private LocalDateTime expireDate;
	
	//토큰 생성 메소드(팩토리)
	public static EmailToken createToken(Member member) {
		
		EmailToken emailToken = new EmailToken();
		emailToken.member = member;
		emailToken.expired= false;
		emailToken.expireDate = LocalDateTime.now().plusMinutes(15);
		
		return emailToken;
	}

	//토큰 만료
	public void expireToken() {
		this.expired = true;
	}
	
	
	
	
}
