package com.anores.game.persistence;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.anores.game.persistence.HibernateUtil;
import com.anores.game.persistence.domain.Profile;
import com.anores.game.persistence.domain.Role;
import com.anores.game.persistence.domain.User;

public class StartPoint {
	public static void main(String[] args) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
			User user = new User();
			session.save(user);
			transaction.commit();
		} catch (HibernateException e) {
			transaction.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}

	}
}
