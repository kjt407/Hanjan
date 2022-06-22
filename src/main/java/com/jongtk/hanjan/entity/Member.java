package com.jongtk.hanjan.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.lang.Nullable;

import com.sun.istack.NotNull;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class Member {

	// Member 식별번호(일련번호)
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "MEMBER_ID")
	private long id;
	
	// 실제 Id로 사용함
	@Column(nullable = false)
	private String username;
	
	@Column(nullable = false)
	private String email;

	@Column(nullable = false)
	private String name;
	
	@Column(nullable = false) @Enumerated(EnumType.STRING) 
	private Gender gender;
	
	private String message;
	
	private int drinkCapacity;
	
	// mbti는 16개로 고정인것을 고려 Enum 타입으로 지정
	@Enumerated(EnumType.STRING)
	private Mbti mbti;
	
	// 값타입 Address 객체
	@Embedded
	private Address address;	
	
	//Entity 생성패턴: 팩토리메소드
	public static Member createMember(String username, String email, String name, Gender gender) {
		Member member = new Member();
		member.username = username;
		member.email = email;
		member.name = name;
		member.gender = gender;

		return member;
	}
	
}
