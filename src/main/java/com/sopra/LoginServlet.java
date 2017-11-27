
package com.sopra;

import java.io.IOException;
import java.io.Serializable;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@SuppressWarnings("serial")
@WebServlet("/login")
@SessionScoped
public class LoginServlet extends HttpServlet implements Serializable {
		
	@EJB
	private UsersManagement userManagement;
	
	
	
	// doGet : Vérification de la session: si un user (presume VALIDE) est connecte, on le redirige vers home.html

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		if(req.getSession().getAttribute(Constants.CONNECTED_USER_ATTRIBUTE)!=null){
			
			resp.sendRedirect(Constants.HOME_PAGE);
		}

		req.setAttribute(Constants.ALERT_ATTRIBUTE, Alert.getAlert());
		
		
		req.getRequestDispatcher("/WEB-INF/login.jsp").forward(req, resp);
	}

	// doPost : Recupere le login et le password envoyes
	// Guards : On vérifie si l'utilisateur est bien présent dans la base de donnée (login + password)
	// Si c'est le cas, il est connecté est redirigé vers "home.html", sinon message d'erreur.
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//Recuperer le login/email et le password envoyes

		String connexionField = req.getParameter("connexionField");

		String password = req.getParameter("password");

		//Detecter le type de connexion (login / email)
		User user = new User(); 
		
		if (connexionField.contains("@")){
			 user = userManagement.findByEmail(connexionField);
			 
		}else{
			 user = userManagement.findByLogin(connexionField);
		}		
	
		if (user == null) {
			Alert.setAlert(Constants.BAD_PASSWORD_OR_LOGIN_ALERT);
			resp.sendRedirect(Constants.LOGIN_PAGE);
		}
		
		
		
		Password myPassword = new Password();
		if (myPassword.toHex(user.getPassword())
				.equals(myPassword.toHex(myPassword.generateStorngPasswordHash(password, user)))) {
			
			req.getSession().setAttribute(Constants.CONNECTED_USER_ATTRIBUTE, user);
			
			// Ferme la session après 10*60 secondes d'inactivité
			req.getSession().setMaxInactiveInterval(10*60);
			
			int count = user.getCount();
			user.setCount(count + 1);
			userManagement.updateUser(user);
			
			resp.sendRedirect(Constants.HOME_PAGE);
			
			
		} else {
			Alert.setAlert(Constants.BAD_PASSWORD_OR_LOGIN_ALERT);
			resp.sendRedirect(Constants.LOGIN_PAGE);
		}
	}
}
