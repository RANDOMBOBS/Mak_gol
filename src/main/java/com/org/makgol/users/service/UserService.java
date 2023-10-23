package com.org.makgol.users.service;

import org.springframework.stereotype.Service;

import com.org.makgol.users.dao.UserDao;
import com.org.makgol.users.util.MailSendUtil;
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
	
	//?΄λ©μΌ λ²νΈ ?‘? 
	public boolean checkEmail(String email) {
		int authNumber= mailSendUtil.makeRandomNumber();
		String key = String.valueOf(authNumber);
		
		//boolean result = userDao.insertAuthNumber(authNumber);
		
		String setFrom = ".com"; // email-config? ?€? ? ?? ? ?΄λ©μΌ μ£Όμλ₯? ?? ₯ 
		String toMail = email;
		String title = "?? κ°?? ?Έμ¦? ?΄λ©μΌ ???€."; // ?΄λ©μΌ ? λͺ? 
		String content = 
				"???΄μ§?λ₯? λ°©λ¬Έ?΄μ£Όμ? κ°μ¬?©??€." + 	//html ???Όλ‘? ??± ! 
                "<br><br>" + 
			    "?Έμ¦? λ²νΈ? " + authNumber + "???€." + 
			    "<br>" + 
			    "?΄?Ή ?Έμ¦λ²?Έλ₯? ?Έμ¦λ²?Έ ??Έ??? κΈ°μ??¬ μ£ΌμΈ?."; //?΄λ©μΌ ?΄?© ?½?
		try {
		 mailSendUtil.mailSend(setFrom, toMail, title, content);
		 
		}catch (Exception e) {
			return false;
		}
		
		
		return redisUtil.setDataExpire(key, email, 60 * 3L);
		
	} //checkEmail_END
	
	public boolean checkNumber(int auth_number, String email) {
		String key = String.valueOf(auth_number);
		Boolean result = (email.equals(redisUtil.getData(key)));
		//result = userDao.checkNumber(auth_number);
		
		return result;
	}//checkEmail_END
	
}
