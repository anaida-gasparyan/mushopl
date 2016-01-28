package com.mushopl.entity.security;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "users", uniqueConstraints = {@UniqueConstraint(columnNames = {"username"})})
public class User implements Serializable {

	@Id
	@Basic(optional = false)
	@Column(name = "username", nullable = false, length = 50, unique = true)
	private String username;

	@Basic(optional = false)
	@Column(name = "password", nullable = false, length = 60, updatable = false)
	private String password;

	@Basic(optional = false)
	@Column(name = "enabled", nullable = false)
	private boolean enabled = true;

	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private List<Authority> authorities;

	public User() {
	}

	public User(String username) {
		this.username = username;
	}

	public List<Authority> getAuthorities() {
		return authorities;
	}

	public void setAuthorities(List<Authority> authorities) {
		this.authorities = authorities;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}
}