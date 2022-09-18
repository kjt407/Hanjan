package com.jongtk.hanjan.entity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

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
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "type")
@Table(name = "GROUPS")
public class Group extends BaseEntity{
	
	@Id @GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "GROUP_ID")
	private long id;
	
	@Column(nullable = false)
	private Long hostId;
	
	@Column(nullable = false)
	private String title;

	@Column(nullable = false)
	private String content;
	
	// 그룹원 모집중 여부 
	@Column(nullable = false)
	private boolean isOpen;
		
	@OneToMany(mappedBy = "group", fetch = FetchType.LAZY)
	@Builder.Default
	private List<MemberGroup> memberGroups = new ArrayList<>();
	
	
	/**
	 * 비즈니스 메소드
	 */
	
	// 그룹 생성
	public static Group createGroup(Member member, String title, String content, MemberGroup... memberGroups) {
		Group group= new Group();
		group.hostId = member.getId();
		group.title = title;
		group.content = content;	
		for(MemberGroup memberGroup: memberGroups) {
			group.addMemberGroup(memberGroup);
		}
	
		return group;
	}

	public void addMemberGroup(MemberGroup memberGroup) {
		this.memberGroups.add(memberGroup);
		memberGroup.setGroup(this);
	}
	
}
