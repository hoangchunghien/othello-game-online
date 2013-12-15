package com.anores.game.persistence.repository;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import com.anores.game.persistence.HibernateUtil;
import com.anores.game.persistence.domain.Game;

public class GameMemoryRepository implements GameRepository {

	public void save(Game game) {
		// TODO Auto-generated method stub
		
	}

	public Game findById(Long id) {
		Game game = null;
		Session session = HibernateUtil.getSessionFactory().openSession();
		try {
			String queryString = "from Game where id = :id";
			Query query = session.createQuery(queryString);
			query.setLong("id", id);
			game = (Game) query.uniqueResult();
		} catch (RuntimeException e) {
			e.printStackTrace();
		} finally {
			if (session != null)
				session.close();
		}
		return game;
	}

	public List<Game> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	public void delete(Long id) {
		// TODO Auto-generated method stub
		
	}


}
