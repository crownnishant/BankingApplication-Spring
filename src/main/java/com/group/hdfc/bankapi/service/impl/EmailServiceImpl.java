package com.group.hdfc.bankapi.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.group.hdfc.bankapi.dto.EmailDTO;
import com.group.hdfc.bankapi.service.EmailService;

@Service
public class EmailServiceImpl implements EmailService {

	@Autowired
	private JavaMailSender javaMailSender;
	
	@Value("${spring.mail.username}")
	private String senderEmail;
	
	@Override
	public void sendEmailAlert(EmailDTO emailDto) {
		// TODO Auto-generated method stub
	
		try {
			SimpleMailMessage mailMessage=new SimpleMailMessage();
			mailMessage.setFrom(senderEmail);
			mailMessage.setTo(emailDto.getRecipient());
			mailMessage.setText(emailDto.getMessageBody());
			mailMessage.setSubject(emailDto.getSubject());
			
			javaMailSender.send(mailMessage);
			System.out.println("mail sent successfully");
			
		}
		catch(MailException e) {
			throw new RuntimeException();
		}
	}

}
