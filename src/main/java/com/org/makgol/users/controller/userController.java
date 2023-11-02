package com.org.makgol.users.controller;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.org.makgol.users.service.UserService;
import com.org.makgol.users.vo.AuthNumberVo;
import com.org.makgol.users.vo.UsersRequestVo;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping("/user")
public class userController {
	
	private final UserService userService;
	
	@PostMapping("/findPassword")
	public void userFindPassword() {
		String userEmail = "tjsgus223@naver.com";
		userService.userFindPassword(userEmail);
		
	} // userFindPassword_END
	
	//joinUser
     @PostMapping("/join")
	 public ResponseEntity<?> joinUser(@RequestBody @Valid UsersRequestVo usersRequestVo) {
    	 Boolean result = userService.joinUser(usersRequestVo);
    	 
    	 return new ResponseEntity<>(result, HttpStatus.OK); 
	 }// ResponseEntity_END
	
	@PostMapping("/mailCheck")
	@ResponseBody
	public ResponseEntity<?> mailCheck(@Valid @RequestBody AuthNumberVo authNumberVo) {
		
		
		Boolean result = userService.checkEmail(authNumberVo.getEmail());
		
		if(result) {
			return new ResponseEntity<>("true", HttpStatus.OK);	
		
		} else {
			return new ResponseEntity<>("false", HttpStatus.OK);
		}
		
	} // mailCheck_END
	
	@PostMapping("/authNumberCheck")
	public ResponseEntity<?>  authNumberCheck(@Valid @RequestBody AuthNumberVo authNumberVo) {
		//int number = Integer.parseInt(auth_number);
		boolean result = userService.checkNumber(authNumberVo.getAuth_number(), authNumberVo.getEmail());
		if(result) {
			return new ResponseEntity<>("true", HttpStatus.OK);	
		} else {
			return new ResponseEntity<>("false", HttpStatus.OK);
		
		}
	} //authNumberCheck_END
	
	@GetMapping("/join")
	public String userJoinPage() {
		String nextPage = "user/user_join";
		return nextPage;
	} // userJoinPage_END
	
	
	// ¼ºÈñ¾¾ ÄÚµå
	@GetMapping("/login")
	public String loginForm() {
	    return "user/user_login";
	}
		
		@PostMapping("/loginConfirm")	
	public String loginConfirm(UsersRequestVo usersRequestVo, HttpSession session) {
	    String nextPage = "home";
	    
	    UsersRequestVo loginedUsersRequestVo = userService.loginConfirm(usersRequestVo);
	    
	    if (loginedUsersRequestVo == null) {
	        nextPage = "user/user_login_ng";
	    } else {
	        session.setAttribute("loginedUsersRequestVo", loginedUsersRequestVo);
	    }
	    
	    return nextPage;
	}
}
