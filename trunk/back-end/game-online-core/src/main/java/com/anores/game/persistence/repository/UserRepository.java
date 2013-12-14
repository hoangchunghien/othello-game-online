package com.anores.game.persistence.repository;

import java.util.List;

import com.anores.game.persistence.domain.User;

public interface UserRepository {
	void save(User user);
	
	User findById(Long id);
	
	List<User> findAll();
	
	void delete(Long id);
}
