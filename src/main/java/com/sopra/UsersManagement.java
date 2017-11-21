package com.sopra;

import java.security.SecureRandom;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

@Stateless
public class UsersManagement {

	@PersistenceContext(name="Login")
	EntityManager em;
	
	public User findByLogin(String login){
		Query query = em.createQuery("from User u where u.login=:login")
				.setParameter("login", login);
		
		try {
			User user = (User) query.getSingleResult();
			return user;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}		
	}
	
	public User addUser(String login, String password){
		User user = new User();
		Password mypassword = new Password();
		user.setLogin(login);
		user.setSalt(mypassword.getSalt());
		user.setPassword(mypassword.generateStorngPasswordHash(password, user));
		em.merge(user);
		return user;
	}

	public void updateUser(User user) {
		em.merge(user);
		
	}
}
