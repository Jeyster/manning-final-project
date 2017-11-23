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

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		req.getSession().removeAttribute(Constants.CONNECTED_USER_ATTRIBUTE);
		req.setAttribute(Constants.ALERT_ATTRIBUTE, Alert.getAlert());
		req.getRequestDispatcher("/WEB-INF/changePassword.jsp").forward(req, resp);

	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		
		User user = userManagement.findByToken(req.getParameter("token"));
		
		if (req.getParameterMap().containsKey("password")) {
			
			String password = req.getParameter("password");
			String passwordConfirmation = req.getParameter("password-confirmation");
			
			if (password.isEmpty() || passwordConfirmation.isEmpty()) {
				Alert.setAlert(Constants.EMPTY_FIELD_ALERT);
				resp.sendRedirect("http://localhost:8080/projet-final-1.0-SNAPSHOT/changePassword?token="+user.getToken());
			} else if (!password.equals(passwordConfirmation)) {
				Alert.setAlert(Constants.NOT_SAME_PASSWORD_ALERT);
				resp.sendRedirect("http://localhost:8080/projet-final-1.0-SNAPSHOT/changePassword?token="+user.getToken());
			} else {
				Password mypassword = new Password();
				user.setSalt(mypassword.getSalt());
				user.setPassword(mypassword.generateStorngPasswordHash(req.getParameter("password"), user));
				userManagement.updateUser(user);
			}
		}
		
		resp.sendRedirect(Constants.LOGIN_PAGE);
	}

}
