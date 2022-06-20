package com.jongtk.hanjan.entity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

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
	
	@ManyToOne
	private Member host;
	
	//그룹 구성원 - 회원의 관계를 다대다 관계로 구현
	/* 
	@JoinTable 활용하여 관계 연결 테이블을 맵핑
	@ManyToMany를 활용시 관계연결만을 위한 엔티티를 직접 구현할 필요가 없다. 
	또한 객체관점에서 둘은 서로를 참조하는 다대다 양방향 관계로서 활용이 가능하다 
	*/
	@ManyToMany
	@JoinTable(name = "MemberGroup", 
			   joinColumns = @JoinColumn(name="MEMBER_ID"),
			   inverseJoinColumns = @JoinColumn(name="DRINKGROUP_NO"))
	private List<Member> members = new ArrayList<Member>();
	
	
	// JPA 편의 메서드
	public void addMember(Member member) {
		log.info(member.getName());
		
		// members가 NullPointerException이 발생
		this.members.add(member);
	}
}
