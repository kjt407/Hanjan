package com.jongtk.hanjan.security.dto;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import lombok.Data;

@Data
public class AuthMemberDTO extends User{	//security.core.userdetails.User를 상속해서 커스텀
	
	private Long id;
			
	private String name;
	
	private String email;

	public AuthMemberDTO(String username, String password, String name, String email, Collection<? extends GrantedAuthority> authorities) {
		super(username, password, authorities);
		this.name = name;
		this.email = email;
	}	
	
}
