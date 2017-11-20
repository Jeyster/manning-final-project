package com.sopra;

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
	
	public void addUser(String login, String password){
		User user = new User();
		user.setLogin(login);
		user.setPassword(password);
		em.merge(user);
	}

	public void updateUser(User user) {
		em.merge(user);
		
	}
}
