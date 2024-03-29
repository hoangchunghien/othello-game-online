package com.anores.game.persistence.repository;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.anores.game.persistence.HibernateUtil;
import com.anores.game.persistence.domain.Game;
import com.anores.game.persistence.domain.GameCatalog;

public class GameCatalogMemoryRepository implements GameCatalogRepository {

	public void save(GameCatalog gameCatalog) {
		Transaction transaction = null;
		Session session = HibernateUtil.getSessionFactory().openSession();
		try {
			transaction = session.beginTransaction();
			if (gameCatalog.getId() == null)
				session.save(gameCatalog);
			else
				session.update(gameCatalog);
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

	public GameCatalog findById(Long id) {
		GameCatalog gameCatalog = null;
		Session session = HibernateUtil.getSessionFactory().openSession();
		try {
			String queryString = "from GameCatalog where id = :id";
			Query query = session.createQuery(queryString);
			query.setLong("id", id);
			gameCatalog = (GameCatalog)query.uniqueResult();
			
		} catch (RuntimeException e) {
			e.printStackTrace();
		} finally {
			if (session != null)
				session.close();
		}
		return gameCatalog;
	}

	public List<GameCatalog> findAll() {
		List<GameCatalog> gameCatelog = new ArrayList<GameCatalog>();
		Session session = HibernateUtil.getSessionFactory().openSession();
		try {
			gameCatelog = session.createQuery("from GameCatalog").list();
		} catch (RuntimeException e) {
			e.printStackTrace();
		} finally {
			if (session != null)
				session.close();
		}
		return gameCatelog;
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
