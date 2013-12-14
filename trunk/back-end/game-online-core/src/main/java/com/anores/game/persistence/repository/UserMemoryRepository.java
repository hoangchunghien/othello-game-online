package com.anores.game.persistence.repository;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.anores.game.persistence.HibernateUtil;
import com.anores.game.persistence.domain.User;

public class UserMemoryRepository implements UserRepository {

	public void save(User user) {
		Transaction transaction = null;
		Session session = HibernateUtil.getSessionFactory().openSession();
		try {
			transaction = session.beginTransaction();
			if (user.getId() == null)
				session.save(user);
			else
				session.update(user);
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

	public User findById(Long id) {
		User user = null;
		Session session = HibernateUtil.getSessionFactory().openSession();
		try {
			String queryString = "from User where id = :id";
			Query query = session.createQuery(queryString);
			query.setLong("id", id);
			user = (User)query.uniqueResult();
			
		} catch (RuntimeException e) {
			e.printStackTrace();
		} finally {
			if (session != null)
				session.close();
		}
		return user;
	}

	public List<User> findAll() {
		List<User> users = new ArrayList<User>();
		Session session = HibernateUtil.getSessionFactory().openSession();
		try {
			users = session.createQuery("from User").list();
		} catch (RuntimeException e) {
			e.printStackTrace();
		} finally {
			if (session != null)
				session.close();
		}
		return users;
	}

	public void delete(Long id) {
		Transaction transaction = null;
		Session session = HibernateUtil.getSessionFactory().openSession();
		try {
			transaction = session.beginTransaction();
			User user = (User) session.load(User.class, new Long(id));
			session.delete(user);
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
