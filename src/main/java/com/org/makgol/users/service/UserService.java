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
	
	//?��메일 번호 ?��?��
	public boolean checkEmail(String email) {
		int authNumber= mailSendUtil.makeRandomNumber();
		String key = String.valueOf(authNumber);
		
		//boolean result = userDao.insertAuthNumber(authNumber);
		
		String setFrom = ".com"; // email-config?�� ?��?��?�� ?��?��?�� ?��메일 주소�? ?��?�� 
		String toMail = email;
		String title = "?��?�� �??�� ?���? ?��메일 ?��?��?��."; // ?��메일 ?���? 
		String content = 
				"?��?��?���?�? 방문?��주셔?�� 감사?��?��?��." + 	//html ?��?��?���? ?��?�� ! 
                "<br><br>" + 
			    "?���? 번호?�� " + authNumber + "?��?��?��." + 
			    "<br>" + 
			    "?��?�� ?��증번?���? ?��증번?�� ?��?��???�� 기입?��?�� 주세?��."; //?��메일 ?��?�� ?��?��
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
