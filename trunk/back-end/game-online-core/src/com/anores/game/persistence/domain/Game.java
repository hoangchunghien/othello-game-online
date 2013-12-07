package com.anores.game.persistence.domain;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public class Game extends BaseEntityAudit {
	
	@Column(name = "name")
	private String Name;
	
	@Column(name = "description")
	private String Description;
	
	public Game() {
		
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
