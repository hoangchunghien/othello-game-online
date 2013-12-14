package com.anores.game.persistence;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.anores.game.persistence.domain.MetaProfile;
import com.anores.game.persistence.domain.Profile;
import com.anores.game.persistence.domain.User;
import com.anores.game.persistence.repository.UserMemoryRepository;
import com.anores.game.persistence.repository.UserRepository;

public class StartPoint {
	public static void main(String[] args) {
		UserRepository userRepo = new UserMemoryRepository();
		
		User user2 = new User();
		user2.setDisplayName("Nguyen Van Liep");
		Profile profile = new Profile();
		Set<MetaProfile> metaProfile = new HashSet<MetaProfile>();
		metaProfile.add(new MetaProfile("abc", "nothing"));
		profile.setMetaProfiles(metaProfile);
		user2.setProfile(profile);
		userRepo.save(user2);
		
		List<User> users = userRepo.findAll();
		for (User user : users) {
			System.out.println(user.getDisplayName());
		}
		
		System.out.print(user2.getId());
		
		/*Session session = HibernateUtil.getSessionFactory().openSession();
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
		}*/

	}
}
