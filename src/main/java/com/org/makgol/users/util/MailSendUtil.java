package com.org.makgol.users.util;

import java.util.Random;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

@Component
public class MailSendUtil {
	@Autowired
	private JavaMailSenderImpl mailSender;
	
	// ?‚œ?ˆ˜ ë°œìƒ(?—¬?Ÿ¬ë¶„ë“¤ ë§˜ë??Ÿ¬)
	
		public int makeRandomNumber() {
			// ?‚œ?ˆ˜?˜ ë²”ìœ„ 111111 ~ 999999 (6?ë¦? ?‚œ?ˆ˜)
			Random r = new Random();
			int checkNum = r.nextInt(888888) + 111111;
			return checkNum;
		}
		
		
		//?´ë©”ì¼ ? „?†¡ ë©”ì†Œ?“œ
		public Boolean mailSend(String setFrom, String toMail, String title, String content) { 
			MimeMessage message = mailSender.createMimeMessage();
			// true ë§¤ê°œê°’ì„ ? „?‹¬?•˜ë©? multipart ?˜•?‹?˜ ë©”ì„¸ì§? ? „?‹¬?´ ê°??Š¥.ë¬¸ì ?¸ì½”ë”© ?„¤? •?„ ê°??Š¥?•˜?‹¤.
			try {
				MimeMessageHelper helper = new MimeMessageHelper(message,true,"utf-8");
				helper.setFrom(setFrom);
				helper.setTo(toMail);
				helper.setSubject(title);
				// true ? „?‹¬ > html ?˜•?‹?œ¼ë¡? ? „?†¡ , ?‘?„±?•˜ì§? ?•Š?œ¼ë©? ?‹¨?ˆœ ?…?Š¤?Š¸ë¡? ? „?‹¬.
				helper.setText(content,true);
				mailSender.send(message);
				return true;
				
			} catch (MessagingException e) {
				e.printStackTrace();
				
				return false;
			}
		}
		
	
}
