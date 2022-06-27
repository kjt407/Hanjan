package com.jongtk.hanjan.entity;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
public class Member extends BaseEntity{

	// Member 식별번호(일련번호)
	@Id @GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "MEMBER_ID")
	private long id;
	
	@Column(nullable = false, unique = true)
	private String email;
	
	@Column(nullable = false)
	private String password;

	@Column(nullable = false)
	private String name;
	
	@Column(nullable = false) @Enumerated(EnumType.STRING) 
	private Gender gender;
	
	private String message;
	
	private int drinkCapacity;
	
	// mbti는 16개로 고정인것을 고려 Enum 타입으로 지정
	@Enumerated(EnumType.STRING)
	private Mbti mbti;
	
	// 회원의 권한/등급 정보
    @ElementCollection(fetch = FetchType.LAZY)
    @Builder.Default
    private Set<MemberRole> roleSet = new HashSet<>();
	
	// 값타입 Address 객체
	@Embedded
	private Address address;	
	
	@OneToMany(mappedBy = "member", fetch = FetchType.LAZY)
	@Builder.Default
	private List<MemberGroup> memberGroups = new ArrayList<MemberGroup>();
	
	
	//Entity 생성패턴: 팩토리메소드
	public static Member createMember(String email, String name, String password, Gender gender) {
		Member member = new Member();
		member.email = email;
		member.name = name;
		member.password = password;
		member.gender = gender;

		return member;
	}
	
    public void addMemeberRole(MemberRole memberRole) {
        roleSet.add(memberRole);
    }
	
}
