package com.org.makgol.users.vo;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UsersRequestVo {
	// user ���̵�
	
	// 이름 
	String name;
	
	// 이메일 
	@Email
	@NotBlank(message = "이메일(필수)")
	String email;
	
	// 비밀번호 
	String password;
	
	// 전화번
	String phone;
	
	// 프로필사진경로
	String photo;
	
}
