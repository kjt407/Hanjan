package com.jongtk.hanjan.dto;

import com.jongtk.hanjan.entity.Address;
import com.jongtk.hanjan.entity.Gender;
import com.jongtk.hanjan.entity.Mbti;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MemberDTO {

	private long id;
	
	private String username;
	
	private String password;
	
	private String email;

	private String name;
	
	private String gender;
	
	private String message;
	
	private int drinkCapacity;
	
	private Mbti mbti;
		
	private Address address;	
	
}
