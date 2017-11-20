package com.sopra;

import java.io.IOException;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/register")
public class RegisterServlet extends HttpServlet{
	
	@EJB
	private UsersManagement userManagement;
	
	private String alert="";
	
	public String getAlert() {
		return alert;
	}

	public void setAlert(String alert) {
		this.alert = alert;
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setAttribute(Constants.ALERT_ATTRIBUTE, getAlert());
		//Renvoie vers register.jsp
		req.getRequestDispatcher("/WEB-INF/register.jsp").forward(req, resp);;
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//Recuperation des donnees
		String login = req.getParameter("login");
		String password = req.getParameter("password");
		String passwordConfirmation = req.getParameter("password-confirmation");
		if (userManagement.findByLogin(login)!=null){ //test: login deja utilise?
			setAlert(Constants.LOGIN_ALREADY_USED_ALERT);
			resp.sendRedirect("register");
		} else if(login.isEmpty() || password.isEmpty()){
			setAlert(Constants.EMPTY_FIELD_ALERT);
			resp.sendRedirect("register");
		} else if (!password.equals(passwordConfirmation)){//test: password confirme?
			setAlert(Constants.NOT_SAME_PASSWORD_ALERT);
			resp.sendRedirect("register");
		} else {//enregistrement, mise en session et envoie sur la page d'accueil
			User user = userManagement.addUser(login, password);
			req.getSession().setAttribute(Constants.CONNECTED_USER_ATTRIBUTE, user);
			resp.sendRedirect("home.html");
		}
		
	}
}
