package com.example.user.security.jwt;

import java.security.acl.Permission;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.example.user.model.User;
import com.fasterxml.jackson.annotation.JsonIgnore;

public class JwtUser implements UserDetails {

	private static final long serialVersionUID = 15225762647540176L;
	private final Long id;
	private final String username;
	@JsonIgnore
	private final String password;
	private final String email;

	public User getUser() {
		User user = new User();
		user.setId(getId());
		user.setUsername(getUsername());
		return user;
	}

	public JwtUser(Long id, String username, String email, String password,
			Collection<? extends GrantedAuthority> authorities, boolean enabled, List<Permission> permissions,
			String roleName) {

		this.id = id;
		this.username = username;
		this.email = email;
		this.password = password;
	}

	public JwtUser(Long id, String username, String email, String password, List<Permission> permissions) {

		this.id = id;
		this.username = username;
		this.email = email;
		this.password = password;

	}

	@JsonIgnore
	public Long getId() {
		return id;
	}

	@Override
	public String getUsername() {
		return username;
	}

	@JsonIgnore
	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@JsonIgnore
	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@JsonIgnore
	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	public String getEmail() {
		return email;
	}

	@JsonIgnore
	@Override
	public String getPassword() {
		return password;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}

}
