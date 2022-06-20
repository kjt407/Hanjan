package com.jongtk.hanjan.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
public class DrinkGroup {
	
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "DRINKGROUP_NO")
	private long no;
	
	@ManyToOne
	private Member host;
	
	@OneToMany(mappedBy = "drinkGroup")
	private List<Member> members = new ArrayList<Member>();
	
	
}
