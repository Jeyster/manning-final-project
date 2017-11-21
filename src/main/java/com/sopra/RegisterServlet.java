package com.sopra;

import java.io.IOException;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@SuppressWarnings("serial")
@WebServlet("/register")
public class RegisterServlet extends HttpServlet {

	@EJB
	private UsersManagement userManagement;

	// Initialisation du message d'erreur, permettant d'avertir l'utilisateur si celui-ci ne remplie pas les conditions pour s'inscrire.
	// La classe "Constants.java" contient la liste des messages d'erreur disponible.
	
	private String alert = "";

	public String getAlert() {
		return alert;
	}

	public void setAlert(String alert) {
		this.alert = alert;
	}

	
	// doGet : Envoie vers la page register.jsp (page d'inscription) en récupérant et affichant le message d'erreur si l'utilisateur à mal saisie
    //         le(s) champ(s) login et/ou password.
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		req.setAttribute(Constants.ALERT_ATTRIBUTE, getAlert());
		
		req.getRequestDispatcher("/WEB-INF/register.jsp").forward(req, resp);
		;
	}
	
	// doPost : Récupération des données "login / password / password-confirmation" pour l'inscription de l'utilisateur.
	// Guards : - Premièrement, on vérifie si le login est déjà présent ou non dans la Base de donnée.
	//          - Deuxièmement, on vérifie que l'utilisateur a bien remplie les champs requis, et non laissé des blancs.
	//          - Ensuite, on regarde si le password correspond à la confirmation du password
	//          - Puis on termine en vérifiant que l'utilisateur n'a pas rempli le champ "login" avec des caractères spéciaux ou d'espaces, 
	//            pour celà, une classe Tools a été créé, avec une fonction permettant de vérifier si le login contient un de ces caractères spéciaux.
	//            Si les guards sont passées, l'utilisateur est enregistré dans la base de donnée, il se connecte et est renvoyé sur la page "home".

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String login = req.getParameter("login");
		String password = req.getParameter("password");
		String passwordConfirmation = req.getParameter("password-confirmation");
		
		Tools tools = new Tools();
		
		if (userManagement.findByLogin(login) != null) { 
															
			setAlert(Constants.LOGIN_ALREADY_USED_ALERT);
			resp.sendRedirect(Constants.REGISTER_PAGE);
			
		} else if (login.isEmpty() || password.isEmpty()) {
			
			setAlert(Constants.EMPTY_FIELD_ALERT);
			resp.sendRedirect(Constants.REGISTER_PAGE);
			
		} else if (!password.equals(passwordConfirmation)) {
															
			setAlert(Constants.NOT_SAME_PASSWORD_ALERT);
			resp.sendRedirect(Constants.REGISTER_PAGE);
			
		} else if ( tools.checkStringsPresence(Constants.listChar, login)) {
			
			setAlert(Constants.LOGIN_IS_NOT_CORRECT);
			resp.sendRedirect(Constants.REGISTER_PAGE);
			
		} else {
			
			    
		
			User user = userManagement.addUser(login, password);
			req.getSession().setAttribute(Constants.CONNECTED_USER_ATTRIBUTE, user);
			resp.sendRedirect(Constants.HOME_PAGE);
		}

	}
}