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
	// ?���?
	@NotBlank(message = "?���?(?��?��)")
	@NotEmpty
	String name;
	
	// ?��메일 
	@Email
	@NotBlank(message = "?��메일(?��?��)")
	@NotEmpty
	String email;
	
	// 비�?번호
	@NotBlank(message = "비�?번호(?��?��)")
	@NotEmpty
	String password;
	
	// ?��?��번호 
	@NotBlank(message = "?��?��번호(?��?��)")
	@NotEmpty
	String phone;
	
	// ?��로필?��진경�?
	MultipartFile photo;
	
}
