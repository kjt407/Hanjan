package com.jongtk.hanjan.entity.test;

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

import com.jongtk.hanjan.entity.Address;
import com.jongtk.hanjan.entity.Gender;
import com.jongtk.hanjan.entity.Mbti;
import com.jongtk.hanjan.entity.MemberGroup;
import com.jongtk.hanjan.entity.MemberRole;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Store{

	@Id @GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "STORE_ID")
	private long id;
	
	@Column(nullable = false)
	private String name;
	
	@OneToMany(mappedBy = "store", fetch = FetchType.LAZY)
	@Builder.Default
	private List<Item> items = new ArrayList<>();
	
	// 상품 추가를 위한 메서드
	public void add(Item item) {
		this.items.add(item);
		// 상품 엔티티에도 Store 추가
		item.setStore(this);
	}
	
}
