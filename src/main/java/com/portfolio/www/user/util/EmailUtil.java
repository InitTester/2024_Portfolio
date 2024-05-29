package com.portfolio.www.user.util;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import com.portfolio.www.user.dto.EmailDto;

import lombok.extern.apachecommons.CommonsLog;

@Component
public class EmailUtil {
	
	@Autowired
	private JavaMailSender javaMailSender;
	
	public String sendMail(EmailDto dto, boolean isHtml) {
		
		try {
			MimeMessage message = javaMailSender.createMimeMessage();
			MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");
			
			helper.setTo(dto.getReceiver());
			helper.setFrom(dto.getFrom());
			helper.setSubject(dto.getSubject());
			helper.setText(dto.getText(), isHtml);
			
			javaMailSender.send(message);			
			
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
//			e.printStackTrace();
			return "Error";
		}
		return "Sucess";
	}
}
