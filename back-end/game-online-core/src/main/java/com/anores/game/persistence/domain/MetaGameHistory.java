package com.anores.game.persistence.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "meta_game_history")
public class MetaGameHistory extends BaseEntityAudit {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -165782267442157534L;

	@Column(name = "meta_key")
	private String key;
	
	@Column(name = "meta_value")
	private String value;
	
	@Column(name = "description")
	private String description;
	
	public MetaGameHistory() {
		
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
