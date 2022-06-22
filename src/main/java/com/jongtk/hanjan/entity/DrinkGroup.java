package com.jongtk.hanjan.entity;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Log4j2
public class DrinkGroup {
	
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "DRINKGROUP_NO")
	private long no;
	
	@ManyToOne(cascade = CascadeType.ALL)
	private Member host;
	
	//	그룹 구성원 - 회원의 관계를 다대다 관계로 구현
	/* 
	 *	@JoinTable 활용하여 관계 연결 테이블을 맵핑
	 *	@ManyToMany를 활용시 관계연결만을 위한 엔티티를 직접 구현할 필요가 없다. 
	 *	또한 객체관점에서 둘은 서로를 참조하는 다대다 양방향 관계로서 활용이 가능하다 
	*/
	/*	@Builder.Default 꼭 설정해야 Builder로 객체 생성시 NullPointerException을 방지 가능
	 *  @Builder 사용시 필드에 객체타입이 있다면 @Builder.Default를 통해 초기화값 지정
	 */
	@ManyToMany
	@ElementCollection(fetch = FetchType.LAZY)
	@JoinTable(name = "MemberGroup", 
			   joinColumns = @JoinColumn(name="MEMBER_ID"),
			   inverseJoinColumns = @JoinColumn(name="DRINKGROUP_NO"))
	@Builder.Default
	private Set<Member> members = new HashSet<>();
	
	private String title;
	
	private String content;
	
	private LocalDateTime dueDate;
	

// 양방향 관계 ==> 단방향으로 구조 변경
//
//	// 모임 만들기(hosting)
//	public void host(Member member, String title) throws Exception {
//		if(this.members.contains(member)) throw new IllegalStateException("이미 구성원 입니다.");		
//		this.host = member;
//		this.title = title;
//		member.hosted.add(this);
//	}
//	
//	// 구성원 추가 메서드
//	public void addMember(Member member) throws Exception {
//		if(this.members.contains(member)) throw new IllegalStateException("이미 구성원 입니다.");		
//		this.members.add(member);
//		member.joined.add(this);
//	}
//	
//	// 구성원 추방
//	public void removeMember(Member member) throws Exception {		
//		if(!this.members.contains(member)) throw new IllegalStateException(member.getName()+"님은 구성원이 아닙니다.");
//		this.members.remove(member);
//	}
//	
//	// 모임 날짜 확정
//	public void decideDueDate(LocalDateTime dueDate) {
//		this.dueDate = dueDate;
//	}
	
}
