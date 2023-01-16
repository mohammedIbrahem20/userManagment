package com.example.user.service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
public class MailServiceImpl implements MailService {

	@Autowired
	private JavaMailSender mailSendder;

	@Override
	public void send(String to, String from, String cc, String subject, String text, boolean html) {
		try {
			MimeMessage mimeMessage = mailSendder.createMimeMessage();
			MimeMessageHelper message = new MimeMessageHelper(mimeMessage, html);
			message.setTo(to);
			message.setFrom(from);
			message.setSubject(subject);
			message.setText(text, html);
			if (cc != null) {
				message.setCc(cc);
			}

			mailSendder.send(mimeMessage);
		} catch (MessagingException e) {
		}
	}
}
