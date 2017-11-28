package com.sopra;

import java.io.IOException;
import java.util.Date;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/facebook")
public class FacebookServlet extends HttpServlet {
	
	@EJB
	private UsersManagement userManagement;
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		String userName = req.getParameter("name");
		String userEmail = req.getParameter("email");
		String userFbId = req.getParameter("id");
		User user;
		if (userManagement.findByFbId(userFbId)==null){
			user = userManagement.addFbUser(userName, userFbId, userEmail);
		}
	
		 else {
			user = userManagement.findByFbId(userFbId);
			Date lastConnexion = new Date(req.getSession().getLastAccessedTime());
			user.setLastConnexion(lastConnexion);
			userManagement.updateUser(user);
		}
		req.getSession().setAttribute(Constants.CONNECTED_USER_ATTRIBUTE, user);
		resp.setStatus(204);
	}
	
}
