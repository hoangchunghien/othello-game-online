package com.anores.game.persistence.domain;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "user")
public class User extends BaseEntityAudit {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -2516349381580464214L;

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
	
	@OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Profile profile;
	
	@OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Role role;
	
	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name="user_id")
	private Set<GameHistory> gameHistories;
	
	public User() {
		Initialize();
	}
	
	private void Initialize() {
		gameHistories = new HashSet<GameHistory>();
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

	public Profile getProfile() {
		return profile;
	}

	public void setProfile(Profile profile) {
		this.profile = profile;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public Set<GameHistory> getGameHistories() {
		return gameHistories;
	}

	public void setGameHistories(Set<GameHistory> gameHistories) {
		this.gameHistories = gameHistories;
	}
}
