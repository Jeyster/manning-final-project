package com.sopra;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("go-to-angular")
@SuppressWarnings("serial")
public class GoToAngularServlet extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		User user = (User) req.getSession().getAttribute(Constants.CONNECTED_USER_ATTRIBUTE);
		Long userRank = user.getRank();

		resp.sendRedirect("http://localhost:4200");
	}
	
	

}
