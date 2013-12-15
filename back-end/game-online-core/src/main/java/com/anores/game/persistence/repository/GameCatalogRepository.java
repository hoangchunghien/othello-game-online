package com.anores.game.persistence.repository;

import java.util.List;

import com.anores.game.persistence.domain.GameCatalog;

public interface GameCatalogRepository {

	void save(GameCatalog gameCatalog);
		
	GameCatalog findById(Long id);
		
	List<GameCatalog> findAll();
		
	void delete(Long id);
	
}
