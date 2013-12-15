package com.anores.game.persistence.domain;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "game_history")
public class GameHistory extends BaseEntityAudit {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7560832805299553120L;
	
	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name="game_history_id")
	private Set<MetaGameHistory> metaGameHistories;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="game_id")
	private Game game;
	
	public GameHistory() {
		initialize();
	}
	
	private void initialize() {
		metaGameHistories = new HashSet<MetaGameHistory>();
	}

	public Game getGame() {
		return game;
	}

	public void setGame(Game game) {
		this.game = game;
	}
}
