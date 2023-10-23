package com.org.makgol.users.vo;

import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

import org.springframework.web.multipart.MultipartFile;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Valid
@ToString
public class UsersRequestVo {
	// ?΄λ¦?
	@NotBlank(message = "?΄λ¦?(??)")
	@NotEmpty
	String name;
	
	// ?΄λ©μΌ 
	@Email
	@NotBlank(message = "?΄λ©μΌ(??)")
	@NotEmpty
	String email;
	
	// λΉλ?λ²νΈ
	@NotBlank(message = "λΉλ?λ²νΈ(??)")
	@NotEmpty
	String password;
	
	// ? ?λ²νΈ 
	@NotBlank(message = "? ?λ²νΈ(??)")
	@NotEmpty
	String phone;
	
	// ?λ‘ν?¬μ§κ²½λ‘?
	MultipartFile photo;
	
}
