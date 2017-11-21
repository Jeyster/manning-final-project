package com.sopra;

import java.io.IOException;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@SuppressWarnings("serial")
@WebServlet("/edition.html")
public class ModifyUserServlet extends HttpServlet {

	@EJB
	private UsersManagement userManagement;

	// doGet : Envoie vers la page modify.jsp (page de modification) en
	// récupérant et affichant le message d'erreur si l'utilisateur à mal saisi
	// le(s) champ(s) login et/ou password + confirmation password.

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		req.setAttribute(Constants.ALERT_ATTRIBUTE, Alert.getAlert());

		req.getRequestDispatcher("/WEB-INF/userEdition.jsp").forward(req, resp);

	}

	// doPost : Récupère le login du user qui est connecté à la session
	// (permettant donc d'éditer son profil)
	// Récupération des données "login / password / password-confirmation" pour
	// l'édition du profil de l'utilisateur.
	// Guards : Permet de vérifier les conditions d'édition sur le login et
	// password (idem que RegisterServlet.java).
	// Si les guards sont passées, le profil de l'utilisteur est édité et il est
	// renvoyé sur la page "home".

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		User user = (User) req.getSession().getAttribute(Constants.CONNECTED_USER_ATTRIBUTE);

		Tools tools = new Tools();

		if (req.getParameterMap().containsKey("login")) {
			String login = req.getParameter("login");
			
			if (login.isEmpty()) {
				Alert.setAlert(Constants.EMPTY_FIELD_ALERT);
				resp.sendRedirect(Constants.EDITION_PAGE);
				
			} else if (userManagement.findByLogin(login) != null) {
				Alert.setAlert(Constants.LOGIN_ALREADY_USED_ALERT);
				resp.sendRedirect(Constants.EDITION_PAGE);
			}

			else if (tools.checkStringsPresence(Constants.listChar, login)) {

				Alert.setAlert(Constants.LOGIN_IS_NOT_CORRECT);
				resp.sendRedirect(Constants.EDITION_PAGE);
				
			} else {
				user.setLogin(req.getParameter("login"));
				userManagement.updateUser(user);
			}
		}
		if (req.getParameterMap().containsKey("password")) {
			String password = req.getParameter("password");
			String passwordConfirm = req.getParameter("password-confirmation");
			if (password.isEmpty() || passwordConfirm.isEmpty()) {
				Alert.setAlert(Constants.EMPTY_FIELD_ALERT);
				resp.sendRedirect(Constants.EDITION_PAGE);
			} else if (!password.equals(passwordConfirm)) {
				Alert.setAlert(Constants.NOT_SAME_PASSWORD_ALERT);
				resp.sendRedirect(Constants.EDITION_PAGE);
			} else {
				Password mypassword = new Password();
				user.setSalt(mypassword.getSalt());
				user.setPassword(mypassword.generateStorngPasswordHash(req.getParameter("password"), user));
				userManagement.updateUser(user);
			}
		}
		resp.sendRedirect(Constants.HOME_PAGE);
	}

}