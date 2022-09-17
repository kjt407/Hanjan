package com.jongtk.hanjan.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
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

/**
 * Member:Group = M:N
 * 관계 해석을 위한 연결 테이블
 */
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class MemberGroup extends BaseEntity{

	// 연결테이블을 Entity로 승격 => 대리키 사용
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "MEMBER_GROUP_ID")
	private long id;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "MEMBER_ID")
	private Member member;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "GROUP_ID")
	private Group group;
	
	
	public static MemberGroup createMemberGroup(Member member) {
		MemberGroup memberGroup = new MemberGroup();
		memberGroup.member = member;
		
		return memberGroup;
	}

	public void setGroup(Group group) {
		this.group = group;
	}
	
}
