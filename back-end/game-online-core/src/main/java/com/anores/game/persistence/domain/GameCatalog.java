package com.anores.game.persistence.domain;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public class GameCatalog extends BaseEntityAudit {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3747739551491534506L;

	@Column(name = "name")
	private String Name;
	
	@Column(name = "description")
	private String Description;
	
	public GameCatalog() {
		
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