package com.jongtk.hanjan.dto;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import com.jongtk.hanjan.entity.Address;
import com.jongtk.hanjan.entity.Gender;
import com.jongtk.hanjan.entity.Mbti;

import lombok.Data;

@Data
public class AuthMemberDTO extends User{	//security.core.userdetails.User를 상속해서 커스텀
	
	private Long id;
	
	private String username;
	
	private String password;
	
	private String name;
	
	private String email;
	
	private Gender gender;
	
	private String message;	
	
	private Mbti mbti;
	
	private Address address;


	public AuthMemberDTO(String username, String password, String name, String email, Gender gender, Collection<? extends GrantedAuthority> authorities) {
		super(username, password, authorities);
		this.username = username;
	}	

	
//    public AuthMemberDTO(String username, String password, boolean fromSocial, Collection<? extends GrantedAuthority> authorities) {
//        super(username, password, authorities);
//        this.email = username;
//        this.password = password;
//    }
	
	
	
	
}
