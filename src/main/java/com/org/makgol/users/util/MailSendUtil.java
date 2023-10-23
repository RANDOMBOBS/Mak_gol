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
	
	// ?��?�� 발생(?��?��분들 맘�??��)
	
		public int makeRandomNumber() {
			// ?��?��?�� 범위 111111 ~ 999999 (6?���? ?��?��)
			Random r = new Random();
			int checkNum = r.nextInt(888888) + 111111;
			return checkNum;
		}
		
		
		//?��메일 ?��?�� 메소?��
		public Boolean mailSend(String setFrom, String toMail, String title, String content) { 
			MimeMessage message = mailSender.createMimeMessage();
			// true 매개값을 ?��?��?���? multipart ?��?��?�� 메세�? ?��?��?�� �??��.문자 ?��코딩 ?��?��?�� �??��?��?��.
			try {
				MimeMessageHelper helper = new MimeMessageHelper(message,true,"utf-8");
				helper.setFrom(setFrom);
				helper.setTo(toMail);
				helper.setSubject(title);
				// true ?��?�� > html ?��?��?���? ?��?�� , ?��?��?���? ?��?���? ?��?�� ?��?��?���? ?��?��.
				helper.setText(content,true);
				mailSender.send(message);
				return true;
				
			} catch (MessagingException e) {
				e.printStackTrace();
				
				return false;
			}
		}
		
	
}
