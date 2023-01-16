package com.example.user.service;

public interface MailService {
	void send(String to, String from, String cc, String subject, String text, boolean html);
}
