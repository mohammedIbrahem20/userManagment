package com.example.user.security.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.session.SessionInformation;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.example.user.model.User;
import com.example.user.security.jwt.JwtUser;
import com.example.user.security.jwt.JwtUserFactory;
import com.example.user.service.UserService;

@Service("securityUserService")
public class SecurityUserServiceImpl implements SecurityUserService {
	@Autowired
	private UserService  userService;


	@Autowired
	private SessionRegistry sessionRegistry;

	@Override
	public UserDetails getUserByUserName(String username) {
		User user = userService.getUserByUsername(username);
		if (user != null) {

			JwtUser jUser= JwtUserFactory.create(user);
			sessionRegistry.getAllPrincipals().add(jUser);
			return jUser;
			
		}
		return null;
	}

}
