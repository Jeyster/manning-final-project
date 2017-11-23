package com.sopra;

import java.io.IOException;
import java.util.List;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@SuppressWarnings("serial")
@WebServlet("/users")
public class UsersServlet extends HttpServlet {

	@EJB
	private UsersManagement userManagement;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		User user = (User) req.getSession().getAttribute(Constants.CONNECTED_USER_ATTRIBUTE);
		List<User> listUsers = userManagement.findAllUsers();
		req.setAttribute("listUsers", listUsers);

		if (user.getRank() < 2) {
			req.getRequestDispatcher(Constants.HOME_PAGE).forward(req, resp);
		}

		else {

			req.getRequestDispatcher("/WEB-INF/listUsers.jsp").forward(req, resp);
		}
	}

}
