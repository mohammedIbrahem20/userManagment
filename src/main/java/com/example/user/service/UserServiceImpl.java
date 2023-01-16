package com.example.user.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.user.model.User;
import com.example.user.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	UserRepository userRepository;
	
	@Autowired
	MailService mailService;

	@Override
	public void registration(User user) {
		userRepository.save(user);
		
		mailService.send(user.getEmail(), "mohammed.elhusary@gmail.com", null, "Registration", createMailBody(user), true);
	}


	private String createMailBody(User user) {

		StringBuilder body = new StringBuilder();
		body.append("Hello " + user.getUsername() +",").append("</br>");
		body.append("Your Registration Dine").append("</br>");
		
		return body.toString();
	}
	
	@Override
	public User getUserProfile(Long userId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void login(String username, String pass) {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean existsByUsername(String username) {
		return userRepository.existsByUsername(username);
	}

	@Override
	public boolean existsByEmail(String email) {
		return userRepository.existsByEmail(email);
	}


	@Override
	public User getUser(Long id) {
		return userRepository.findById(id).get();
	}


	@Override
	public User getUserByUsername(String username) {
		return userRepository.findByUsername(username);
	}
}
