package com.anores.game.persistence.domain;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="game")
public class Game extends BaseEntityAudit {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -5298875949632600752L;

	@Column(name = "name")
	private String Name;
	
	@Column(name = "description")
	private String Description;

	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="game_catalog_id")
	private GameCatalog gameCatalog;
	
	public Game() {
		initialize();
	}
	
	private void initialize() {
		
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
