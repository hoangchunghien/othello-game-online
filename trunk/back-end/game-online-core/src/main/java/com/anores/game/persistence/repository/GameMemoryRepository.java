package com.anores.game.persistence.repository;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.anores.game.persistence.HibernateUtil;
import com.anores.game.persistence.domain.Game;

public class GameMemoryRepository implements GameRepository {

	public void save(Game game) {
		Transaction transaction = null;
		Session session = HibernateUtil.getSessionFactory().openSession();
		try {
			transaction = session.beginTransaction();
			if (game.getId() == null)
				session.save(game);
			else
				session.update(game);
			session.getTransaction().commit();
			
		} catch (RuntimeException e) {
			if (transaction != null)
				transaction.rollback();
			e.printStackTrace();
		} finally {
			if (session != null)
				session.close();
		}
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
		List<Game> games = new ArrayList<Game>();
		Session session = HibernateUtil.getSessionFactory().openSession();
		try {
			games = session.createQuery("from Game").list();
		} catch (RuntimeException e) {
			e.printStackTrace();
		} finally {
			if (session != null)
				session.close();
		}
		return games;
	}

	public void delete(Long id) {
		Transaction transaction = null;
		Session session = HibernateUtil.getSessionFactory().openSession();
		try {
			transaction = session.beginTransaction();
			Game game = (Game) session.load(Game.class, new Long(id));
			session.delete(game);
			session.getTransaction().commit();
			
		} catch (RuntimeException e) {
			if (transaction != null)
				transaction.rollback();
			e.printStackTrace();
		} finally {
			if (session != null)
				session.close();
		}
	}


}
