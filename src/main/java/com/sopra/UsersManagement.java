
package com.sopra;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

@Stateless
public class UsersManagement {

	@PersistenceContext(name="Login")
	EntityManager em;
	
	public Long countNumberOfUsers(){
		System.out.println("success 2");
		Query query = em.createQuery("select count(u) from User u");
		return (Long) query.getSingleResult();
		
	}
	
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
	
	public User addUser(String login, String password, String email){
		User user = new User();
		Password mypassword = new Password();
		user.setLogin(login);
		user.setEmail(email);
		System.out.println("success 1");
		if (countNumberOfUsers()==0){
			user.setRank(Constants.ADMIN_MAX_RANK);
		} else {
			user.setRank((long) 1);
		}
		System.out.println("success 3");
		user.setSalt(mypassword.getSalt());
		user.setPassword(mypassword.generateStorngPasswordHash(password, user));
		em.merge(user);
		return user;
	}

	public void updateUser(User user) {
		em.merge(user);
		
	}

	public User findByEmail(String connexionField) {
		Query query = em.createQuery("from User u where u.email=:email")
				.setParameter("email", connexionField);
		
		try {
			User user = (User) query.getSingleResult();
			return user;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}		
	}
}

