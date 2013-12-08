package com.anores.game.persistence.domain;

import javax.persistence.Entity;

@Entity
public class MetaProfile extends BaseEntityAudit {
	
	private String key;
	private String value;
	private String description;
	
	public MetaProfile() {
		
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
}
