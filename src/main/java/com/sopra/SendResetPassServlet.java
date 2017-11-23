package com.sopra;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SendResetPassServlet {
	
protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		
		//récuperer tous les paramètres de la JSP
		String newPass= req.getParameter("newPass");
		String reNewPass= req.getParameter("reNewPass");
		
		
		if (newPass.equals(null)||newPass=="" || reNewPass.equals(null)||reNewPass==""){
			req.setAttribute("msg", "All Fields are mendatory" );
			//getServletContext().getRequestDispatcher("/resetPass.jsp").forward(req, resp);
		}
		
		else if (!newPass.equals(reNewPass))
		{
			req.setAttribute("msg", "Password doesn't match" );
			//getServletContext().getRequestDispatcher("/resetPass.jsp").forward(req, resp);
		}
		
		
	}

}


}
