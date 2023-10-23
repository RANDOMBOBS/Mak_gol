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
	
	//?´ë©”ì¼ ë²ˆí˜¸ ?†¡?‹ 
	public boolean checkEmail(String email) {
		int authNumber= mailSendUtil.makeRandomNumber();
		String key = String.valueOf(authNumber);
		
		//boolean result = userDao.insertAuthNumber(authNumber);
		
		String setFrom = ".com"; // email-config?— ?„¤? •?•œ ??‹ ?˜ ?´ë©”ì¼ ì£¼ì†Œë¥? ?…? ¥ 
		String toMail = email;
		String title = "?šŒ?› ê°??… ?¸ì¦? ?´ë©”ì¼ ?…?‹ˆ?‹¤."; // ?´ë©”ì¼ ? œëª? 
		String content = 
				"?™ˆ?˜?´ì§?ë¥? ë°©ë¬¸?•´ì£¼ì…”?„œ ê°ì‚¬?•©?‹ˆ?‹¤." + 	//html ?˜•?‹?œ¼ë¡? ?‘?„± ! 
                "<br><br>" + 
			    "?¸ì¦? ë²ˆí˜¸?Š” " + authNumber + "?…?‹ˆ?‹¤." + 
			    "<br>" + 
			    "?•´?‹¹ ?¸ì¦ë²ˆ?˜¸ë¥? ?¸ì¦ë²ˆ?˜¸ ?™•?¸???— ê¸°ì…?•˜?—¬ ì£¼ì„¸?š”."; //?´ë©”ì¼ ?‚´?š© ?‚½?…
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
