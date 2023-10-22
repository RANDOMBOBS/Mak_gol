package com.org.makgol.users.controller;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.org.makgol.users.service.UserService;
import com.org.makgol.users.vo.AuthNumberVo;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping("/user")
public class userController {
	
	private final UserService userService;
	
	@PostMapping("/findId")
	public void userFindId() {
		String userEmail = "tjsgus223@naver.com";
		
	} // userFindId_END
	
	@PostMapping("/findPassword")
	public void userFindPassword() {
		String userEmail = "tjsgus223@naver.com";
		
		
	} // userFindPassword_END
	
	@PostMapping("/mailCheck")
	 @ResponseBody
	public ResponseEntity<?> mailCheck(@Valid @RequestBody AuthNumberVo authNumberVo) {
		
		
		Boolean result = userService.checkEmail(authNumberVo.getEmail());
		
		//인증번호 송신 성공
		if(result) {
			return new ResponseEntity<>("true", HttpStatus.OK);	
		
		//실패
		} else {
			return new ResponseEntity<>("false", HttpStatus.OK);
		}
		
	} // mailCheck_END
	
	@PostMapping("/authNumberCheck")
	public ResponseEntity<?>  authNumberCheck(@Valid @RequestBody AuthNumberVo authNumberVo) {
		//int number = Integer.parseInt(auth_number);
		boolean result = userService.checkNumber(authNumberVo.getAuth_number(), authNumberVo.getEmail());
		
		System.out.println(result);
		//인증 성공
		if(result) {
			return new ResponseEntity<>("true", HttpStatus.OK);	
		
		//실패
		} else {
			return new ResponseEntity<>("false", HttpStatus.OK);
		
		}
	} //authNumberCheck_END
	
	
	@GetMapping("/join")
	public String userJoinPage() {
		
		String nextPage = "user/user_join";
		return nextPage;
	} // userJoinPage_END
	
}
