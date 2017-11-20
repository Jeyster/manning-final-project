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

	// Initialisation du message d'alerte
	private String alert = "";

	public String getAlert() {
		return alert;
	}

	public void setAlert(String alert) {
		this.alert = alert;
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		req.setAttribute(Constants.ALERT_ATTRIBUTE, getAlert());

		// Envoie vers la page d'édition des users
		req.getRequestDispatcher("/WEB-INF/userEdition.jsp").forward(req, resp);

	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		// Récupération des données d'édition
		User user = (User) req.getSession().getAttribute(Constants.CONNECTED_USER_ATTRIBUTE);
		String login = req.getParameter("login");
		String password = req.getParameter("password");
		String passwordConfirm = req.getParameter("password-confirmation");

		// Guards
		if (password.equals(passwordConfirm) && userManagement.findByLogin(login) == null) {

			user.setPassword(req.getParameter("password"));
			user.setLogin(req.getParameter("login"));
			// On update les modifications
			userManagement.updateUser(user);
			// On renvoi sur la page "home"
			resp.sendRedirect("home.html");
		} else if (!password.equals(passwordConfirm)) {
			setAlert(Constants.NOT_SAME_PASSWORD_ALERT);
			resp.sendRedirect("edition.html");

		} else if (login == null || password == null || passwordConfirm == null) {
			setAlert(Constants.EMPTY_FIELD_ALERT);
			resp.sendRedirect("edition.html");

		} else if (userManagement.findByLogin(login) != null) {
			setAlert(Constants.LOGIN_ALREADY_USED_ALERT);
			resp.sendRedirect("edition.html");
		}

	}

}
