
package com.sopra;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.servlet.http.HttpServletRequest;

@Stateless
public class UsersManagement {

	@PersistenceContext(name = "Login")
	EntityManager em;

	public Long countNumberOfUsers() {
		System.out.println("success 2");
		Query query = em.createQuery("select count(u) from User u");
		return (Long) query.getSingleResult();

	}

	public User findByLogin(String login) {
		Query query = em.createQuery("from User u where u.login=:login").setParameter("login", login);

		try {
			User user = (User) query.getSingleResult();
			return user;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	
	public User findByToken(String token) {
		Query query = em.createQuery("from User u where u.token=:token").setParameter("token", token);

		try {
			User user = (User) query.getSingleResult();
			return user;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public User addUser(String login, String password, String email) {
		User user = new User();
		Password mypassword = new Password();
		user.setLogin(login);
		user.setEmail(email);
		System.out.println("success 1");
		if (countNumberOfUsers() == 0) {
			user.setRank(Constants.ADMIN_MAX_RANK);
		} else {
			user.setRank((long) 1);
		}
		System.out.println("success 3");
		user.setSalt(mypassword.getSalt());
		user.setPassword(mypassword.generateStorngPasswordHash(password, user));
		user.setToken(mypassword.generateToken(user, login));
		em.merge(user);
		return user;
	}

	public void updateUser(User user) {
		em.merge(user);

	}

	public User findByEmail(String connexionField) {
		Query query = em.createQuery("from User u where u.email=:email").setParameter("email", connexionField);

		try {
			User user = (User) query.getSingleResult();
			return user;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public boolean validUserIsConnected(HttpServletRequest req) {
		if (req.getSession().getAttribute(Constants.CONNECTED_USER_ATTRIBUTE) != null) {
			User user = (User) req.getSession().getAttribute(Constants.CONNECTED_USER_ATTRIBUTE);
			if (findByLogin(user.getLogin()) != null) {
				return true;
			}
		} else {
			return false;
		}
		return false;
	}

	public List<User> findAllUsers() {
		TypedQuery<User> query = em.createQuery("from User", User.class);
		return query.getResultList();
	}

	public boolean connectedUserIsAdmin(HttpServletRequest request) {
		if (validUserIsConnected(request)) {
			User user = (User) request.getSession().getAttribute(Constants.CONNECTED_USER_ATTRIBUTE);
			if (user.getRank() > 1) {
				return true;
			} else {
				return false;
			}
		} else {
			return false;
		}
	}

}
