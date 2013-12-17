package com.anores.game.persistence.domain;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;



@Entity
@Table(name="game_catalog")
public class GameCatalog extends BaseEntityAudit {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3747739551491534506L;

	@Column(name = "name")
	private String Name;
	
	@Column(name = "description")
	private String Description;
	
	@OneToMany(mappedBy="gameCatalog", fetch=FetchType.LAZY, cascade=CascadeType.ALL)
	private Set<Game> games;
	
	public GameCatalog() {
		initialize();
	}
	
	private void initialize() {
		games = new HashSet<Game>();
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

	public Set<Game> getGames() {
		return games;
	}

	public void setGames(Set<Game> games) {
		this.games = games;
	}

}