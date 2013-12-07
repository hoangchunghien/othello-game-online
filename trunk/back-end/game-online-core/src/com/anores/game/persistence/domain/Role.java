package com.anores.game.persistence.domain;

import javax.persistence.Entity;

@Entity
public class Role extends BaseEntityAudit {
	private String Name;
	private String Description;
	
	public Role() {
		
	}
	
	public String getName() {
		return Name;
	}
	public void setName(String name) {
		Name = name;
	}
	public String getDescription() {
		return Description;
	}
	public void setDescription(String description) {
		Description = description;
	}
}
