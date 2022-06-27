package com.jongtk.hanjan.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

import com.jongtk.hanjan.entity.Address;
import com.jongtk.hanjan.entity.Mbti;

import lombok.Getter;

@Getter
public class SignUpDTO {

    @Email(message = "올바른 Email 형식이 아닙니다.")
    @NotBlank(message = "이메일은 공백을 포함할 수 없습니다.")
	private String email;
	
    @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[$@$!%*#?&])[A-Za-z\\d$@$!%*#?&]{8,20}$", message = "비밀번호는 문자,숫자,특수문자를 포함해야합니다.")
    @NotBlank(message = "비밀번호는 공백을 포함할 수 없습니다.")
	private String password;
	
    @NotBlank(message = "이름은 공백을 포함할 수 없습니다.")
	private String name;	
	
}
