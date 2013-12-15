package com.anores.game.persistence.repository;

import java.util.List;

import com.anores.game.persistence.domain.Game;

public interface GameRepository {
	void save(Game game);
	
	Game findById(Long id);
		
	List<Game> findAll();
		
	void delete(Long id);
}
