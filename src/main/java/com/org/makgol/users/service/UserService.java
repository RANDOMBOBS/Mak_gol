package com.org.makgol.users.service;

import javax.validation.Valid;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.stereotype.Service;

import com.org.makgol.users.dao.UserDao;
import com.org.makgol.users.vo.UsersRequestVo;
import com.org.makgol.util.MailSendUtil;
import com.org.makgol.util.RedisUtil;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class UserService {
	
	private final MailSendUtil mailSendUtil;
	private final UserDao userDao;
	private final RedisUtil redisUtil;
	
	public String userFindId(String userEmail){
		return userEmail;
	}
	
	//userFindPassword
	public String userFindPassword(String userEmail){
		if(userDao.findUserEmail(userEmail)) {
			
			int randomNumber= mailSendUtil.makeRandomNumber();
			String newPassword = String.valueOf(randomNumber);
			
			if(userDao.updatePassword(newPassword, userEmail)) {
				mailSendUtil.sendMail(randomNumber, userEmail);
				
			}
			return newPassword;
			
		} else {
			return "회원가입된 이메일이 아닙니다.";
		}
		
	}// userFindPassword_END
	
	
	
	//이메일 번호 송신
	public boolean checkEmail(String email) {
		int authNumber= mailSendUtil.makeRandomNumber();
		String key = String.valueOf(authNumber);
		mailSendUtil.sendMail(authNumber, email);
		
		return redisUtil.setDataExpire(key, email, 60 * 3L);
		
	} //checkEmail_END
	
	public boolean checkNumber(int auth_number, String email) {
		String key = String.valueOf(auth_number);
		Boolean result = (email.equals(redisUtil.getData(key)));
		//result = userDao.checkNumber(auth_number);
		
		return result;
	}//checkEmail_END

	
	//joinUser 
	public Boolean joinUser(UsersRequestVo usersRequestVo) {
		usersRequestVo.setPassword(BCrypt.hashpw(usersRequestVo.getPassword(), BCrypt.gensalt()));
		
		return userDao.createDao(usersRequestVo);
		

		
	}// joinUser_END

	 // 로그인 확인
	public UsersRequestVo loginConfirm(UsersRequestVo usersRequestVo) {
	    String encryptedPassword = userDao.selectUser(usersRequestVo);

	    if (encryptedPassword != null) {
	        if (BCrypt.checkpw(usersRequestVo.getPassword(), encryptedPassword)) {
	            System.out.println("로그인 성공");

	            // 로그인 성공 시 UsersRequestVo에 id 값을 설정
	            UsersRequestVo loginedInUsersRequestVo = new UsersRequestVo();
	            loginedInUsersRequestVo.setId(usersRequestVo.getId()); // id 설정
	            // 다른 필요한 정보도 설정할 수 있습니다.
	            
	            if (usersRequestVo.getGrade() != null && usersRequestVo.getGrade().equalsIgnoreCase("블랙리스트")) {
	                System.out.println("블랙리스트 회원입니다. 로그인 실패.");
	            } else {
	            	System.out.println("로그인성공이라고?");
					return loginedInUsersRequestVo;
	            }
	        }
	    }

	    // 로그인 실패
	    return null;
	}
	
}
