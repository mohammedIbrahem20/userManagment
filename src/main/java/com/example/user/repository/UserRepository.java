package com.example.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.example.user.model.User;

@Repository
@Transactional
public interface UserRepository extends JpaRepository<User, Long> {

	Boolean existsByUsername(String username);
	Boolean existsByEmail(String email);
	
	User findByUsername(String User);
}