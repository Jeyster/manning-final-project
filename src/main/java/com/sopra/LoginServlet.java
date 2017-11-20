package com.sopra;

import java.io.IOException;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {

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
		req.setAttribute("alert", getAlert());
		//Renvoie vers login.jsp
		req.getRequestDispatcher("/WEB-INF/login.jsp").forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//Recuperer le login et le password envoyes
		String login = req.getParameter("login");
		String password = req.getParameter("password");
		//Verifier si on l'a dans la BDD
		User user = userManagement.findByLogin(login);
		if(user==null){
			setAlert("Error: bad password or login");
			resp.sendRedirect("login");
		}
		if(user.getPassword().equals(password)){
			resp.sendRedirect("home.html");
		} else {
			setAlert("Error: bad password or login");
			resp.sendRedirect("login");
		}
	}
	
}
