package com.anores.game.persistence.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name  = "role")
public class Role extends BaseEntityAudit {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1365922951052231829L;

	@Column(name = "name")
	private String name;
	
	@Column(name = "description")
	private String description;
	
	public Role() {
		
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
}
