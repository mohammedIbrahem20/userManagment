package com.example.user.security.service;

import java.util.List;

import org.springframework.security.core.userdetails.UserDetails;

import com.example.user.security.jwt.JwtUser;

public interface SecurityUserService {
	UserDetails getUserByUserName(String username);
}
