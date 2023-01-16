package com.example.user.service;

import com.example.user.model.User;

public interface UserService {

	void registration(User user);
	User getUserProfile(Long userId);
	void login(String username, String pass);
	boolean existsByEmail(String email);
	boolean existsByUsername(String username);
	User getUser(Long id);
	User getUserByUsername(String username);
}
