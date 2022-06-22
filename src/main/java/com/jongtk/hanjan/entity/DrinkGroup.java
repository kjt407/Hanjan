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
	
	private long hostId;

	@ManyToMany
	@ElementCollection(fetch = FetchType.LAZY)
	@JoinTable(name = "MemberGroup", 
			   joinColumns = @JoinColumn(name="MEMBER_ID"),
			   inverseJoinColumns = @JoinColumn(name="DRINKGROUP_NO"))
	@Builder.Default
	private Set<Member> members = new HashSet<>();
	
	@Column(nullable = false)
	private String title;

	@Column(nullable = false)
	private String content;
	
	private LocalDateTime dueDate;
	
	
	public static DrinkGroup createGroup(Member member, String title, String content) {
		DrinkGroup dg= new DrinkGroup();
		dg.hostId = member.getId();
		dg.members.add(member);
		dg.title = title;
		dg.content = content;
		
		return dg;
	}
}
