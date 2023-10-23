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
	// ?´ë¦?
	@NotBlank(message = "?´ë¦?(?•„?ˆ˜)")
	@NotEmpty
	String name;
	
	// ?´ë©”ì¼ 
	@Email
	@NotBlank(message = "?´ë©”ì¼(?•„?ˆ˜)")
	@NotEmpty
	String email;
	
	// ë¹„ë?ë²ˆí˜¸
	@NotBlank(message = "ë¹„ë?ë²ˆí˜¸(?•„?ˆ˜)")
	@NotEmpty
	String password;
	
	// ? „?™”ë²ˆí˜¸ 
	@NotBlank(message = "? „?™”ë²ˆí˜¸(?•„?ˆ˜)")
	@NotEmpty
	String phone;
	
	// ?”„ë¡œí•„?‚¬ì§„ê²½ë¡?
	MultipartFile photo;
	
}
