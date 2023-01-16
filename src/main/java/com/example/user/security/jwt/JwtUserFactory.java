package com.example.user.security.jwt;

import com.example.user.model.User;


public final class JwtUserFactory {

    private JwtUserFactory() {
    }

    public static JwtUser create(User  user) {
    	return new JwtUser
    	(user.getId(), user.getUsername(), user.getEmail(), user.getPassword(), null);
       
    
    }
    
}
