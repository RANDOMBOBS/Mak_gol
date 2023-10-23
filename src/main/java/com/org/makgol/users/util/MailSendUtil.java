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
	
	// ?? λ°μ(?¬?¬λΆλ€ λ§λ??¬)
	
		public int makeRandomNumber() {
			// ??? λ²μ 111111 ~ 999999 (6?λ¦? ??)
			Random r = new Random();
			int checkNum = r.nextInt(888888) + 111111;
			return checkNum;
		}
		
		
		//?΄λ©μΌ ? ?‘ λ©μ?
		public Boolean mailSend(String setFrom, String toMail, String title, String content) { 
			MimeMessage message = mailSender.createMimeMessage();
			// true λ§€κ°κ°μ ? ?¬?λ©? multipart ??? λ©μΈμ§? ? ?¬?΄ κ°??₯.λ¬Έμ ?Έμ½λ© ?€? ? κ°??₯??€.
			try {
				MimeMessageHelper helper = new MimeMessageHelper(message,true,"utf-8");
				helper.setFrom(setFrom);
				helper.setTo(toMail);
				helper.setSubject(title);
				// true ? ?¬ > html ???Όλ‘? ? ?‘ , ??±?μ§? ??Όλ©? ?¨? ??€?Έλ‘? ? ?¬.
				helper.setText(content,true);
				mailSender.send(message);
				return true;
				
			} catch (MessagingException e) {
				e.printStackTrace();
				
				return false;
			}
		}
		
	
}
