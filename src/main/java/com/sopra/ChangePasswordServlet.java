package com.sopra;

import java.io.IOException;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@SuppressWarnings("serial")
@WebServlet("/changePassword")
public class ChangePasswordServlet extends HttpServlet {

	@EJB
	private UsersManagement userManagement;

	// doGet : Deconnecte automatiquement la personne et envoie sur le page
	// "changePassword" via le lien reçu par email.

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		req.getSession().removeAttribute(Constants.CONNECTED_USER_ATTRIBUTE);
		String token = req.getParameter("token");
		User user = userManagement.findByToken(token);
		if (user != null){
			req.getSession().setAttribute(Constants.CONNECTED_USER_ATTRIBUTE, user);
			req.setAttribute(Constants.ALERT_ATTRIBUTE, Alert.getAlert());
			req.getRequestDispatcher("/WEB-INF/changePassword.jsp").forward(req, resp);
		} else {
			resp.sendRedirect(Constants.LOGIN_PAGE);
		}
	}

	// doPost : On récupère le token du user qui veut changer son password.
	// on vérifie les guards et on modifie le password.
	// Si c'est OK, on envoit ensuite vers la page login.

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		User user = (User) req.getSession().getAttribute(Constants.CONNECTED_USER_ATTRIBUTE);

		if (req.getParameterMap().containsKey("password")) {

			String password = req.getParameter("password");
			String passwordConfirmation = req.getParameter("password-confirmation");

			if (password.isEmpty() || passwordConfirmation.isEmpty()) {
				Alert.setAlert(Constants.EMPTY_FIELD_ALERT);
				resp.sendRedirect(
						"http://localhost:8080/projet-final-1.0-SNAPSHOT/changePassword?token="+user.getToken());
			} else if (!password.equals(passwordConfirmation)) {
				Alert.setAlert(Constants.NOT_SAME_PASSWORD_ALERT);
				resp.sendRedirect(
						"http://localhost:8080/projet-final-1.0-SNAPSHOT/changePassword?token="+user.getToken());
			} else {
				Password mypassword = new Password();
				user.setSalt(mypassword.getSalt());
				user.setPassword(mypassword.generateStorngPasswordHash(req.getParameter("password"), user));
				user.setToken(mypassword.generateToken());
				userManagement.updateUser(user);
			}
		}

		resp.sendRedirect(Constants.LOGIN_PAGE);
	}

}
