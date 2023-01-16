package com.example.user.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.user.model.User;
import com.example.user.service.UserService;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;


@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/user")
public class UserController {

	@Autowired
	PasswordEncoder encoder;
	
	@Autowired
	UserService userService;
	
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<String> saveUser(@Valid @RequestBody User user){
		user.setPassword(encoder.encode(user.getPassword()));

		if (userService.existsByUsername(user.getUsername())) {
			return new ResponseEntity<>("USERNAME_IS_ALREADY_TAKEN", HttpStatus.BAD_REQUEST);
		}
		if (userService.existsByEmail(user.getEmail())) {
			return new ResponseEntity<>("EMAIL_IS_ALREADY_INUSE", HttpStatus.BAD_REQUEST);
		}
		
		userService.registration(user);
		return new ResponseEntity<>("USER_ADDED_SUCCESSFULLY", HttpStatus.OK);
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<User> getUserById(@PathVariable Long id) throws Exception{
		User user = userService.getUser(id);
		if (user == null) {
			throw new Exception("User doesn´t exist");
		}
		
		return new ResponseEntity<>(user, HttpStatus.OK);
	}
	
}
