package com.anores.game.persistence.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public class User extends BaseEntityAudit {
	
	@Column(name = "username", length = 60)
	private String username;

	@Column(name = "password", length = 64)
	private String password;
	
	@Column(name = "display_name", length = 250)
	private String displayName;
	
	@Column(name = "email", length = 100)
	private String email;
	
	@Column(name = "registered_at")
	private Date registeredAt;
	
	@Column(name = "activation_key", length = 60)
	private String activationKey;

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

	public String getDisplayName() {
		return displayName;
	}

	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Date getRegisteredAt() {
		return registeredAt;
	}

	public void setRegisteredAt(Date registeredAt) {
		this.registeredAt = registeredAt;
	}

	public String getActivationKey() {
		return activationKey;
	}

	public void setActivationKey(String activationKey) {
		this.activationKey = activationKey;
	}
}
