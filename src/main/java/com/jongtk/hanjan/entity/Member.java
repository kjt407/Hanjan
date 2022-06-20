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
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

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
	
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "MEMBER_ID")
	private long id;
	
	private String email;
	
	private String name;
	
	// 값타입 Address 클래스
	@Embedded
	private Address address;
	
	// mbti는 16개로 고정이기 때문에 Enum 타입으로 엔티티생성
	private Mbti mbti;
	
	@ManyToOne
	@JoinColumn(name="DRINKGROUP_NO")
	private DrinkGroup drinkGroup;
	
	
}
