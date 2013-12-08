package com.anores.game.persistence.domain;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "game_history")
public class GameHistory extends BaseEntityAudit {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7560832805299553120L;
	
}
