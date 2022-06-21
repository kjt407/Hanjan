package com.jongtk.hanjan.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

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
	@Column(unique = true, nullable = false)
	private String username;
	
	@Column(unique = true, nullable = false)
	private String email;

	@Column(nullable = false)
	private String name;
	
	private String message;
	
	private int drinkCapacity;
	
	private Gender gender;
	
	@Builder.Default
	@OneToMany(mappedBy = "host")
	List<DrinkGroup> hosted = new ArrayList<DrinkGroup>();

	@Builder.Default
	@ManyToMany(mappedBy = "members")
	List<DrinkGroup> joined = new ArrayList<DrinkGroup>();
	
	// mbti는 16개로 고정인것을 고려 Enum 타입으로 지정
	private Mbti mbti;
	
	// 값타입 Address 객체
	@Embedded
	private Address address;	
	
}
