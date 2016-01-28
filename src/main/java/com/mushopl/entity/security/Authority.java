package com.mushopl.entity.security;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by anaida on 1/27/16.
 */
@Entity
@Table(name = "authorities", uniqueConstraints = {@UniqueConstraint(columnNames = {"username", "authority"})})
public class Authority implements Serializable {

	@Id
	@ManyToOne(fetch = FetchType.EAGER, optional = false)
	@JoinColumn(name = "username", nullable = false)
	private User user;

	@Id
	@Column(name = "authority", nullable = false, length = 50)
	private String authority;

	public String getAuthority() {
		return authority;
	}

	public void setAuthority(String authority) {
		this.authority = authority;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
}
