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
		
		if(req.getParameterMap().containsKey("login")){
			String login = req.getParameter("login");
			if(login.isEmpty()){
				setAlert(Constants.EMPTY_FIELD_ALERT);
				resp.sendRedirect(Constants.EDITION_PAGE);
			} else if(userManagement.findByLogin(login)!=null){
				setAlert(Constants.LOGIN_ALREADY_USED_ALERT);
				resp.sendRedirect(Constants.EDITION_PAGE);
			} else {
				user.setLogin(req.getParameter("login"));
				userManagement.updateUser(user);
			}
		}
		if (req.getParameterMap().containsKey("password")){
			String password = req.getParameter("password");
			String passwordConfirm = req.getParameter("password-confirmation");
			if(password.isEmpty() || passwordConfirm.isEmpty()){
				setAlert(Constants.EMPTY_FIELD_ALERT);
				resp.sendRedirect(Constants.EDITION_PAGE);
			} else if (!password.equals(passwordConfirm)){
				setAlert(Constants.NOT_SAME_PASSWORD_ALERT);
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