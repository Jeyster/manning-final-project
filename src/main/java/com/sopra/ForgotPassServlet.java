package com.sopra;

import java.io.IOException;
import java.util.Date;
import java.util.Properties;

import javax.ejb.EJB;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/forgotPass")
public class ForgotPassServlet extends HttpServlet{
	
	@EJB
	private UsersManagement userManagement;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		req.setAttribute(Constants.ALERT_ATTRIBUTE, Alert.getAlert());
		req.getRequestDispatcher("/WEB-INF/forgotPass.jsp").forward(req, resp);
	}

@SuppressWarnings("restriction")
protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	User user = new User();
		
		//récuperer tous les paramètres de la JSP
		String email= req.getParameter("email");
		
		
		if (email.isEmpty()){
			Alert.setAlert(Constants.EMPTY_EMAIL_ALERT);
			resp.sendRedirect(Constants.FORGOT_PASS);
		}
		
		else if (userManagement.findByEmail(email)==null){
			Alert.setAlert(Constants.EMAIL_IS_NOT_VALID);
			resp.sendRedirect(Constants.FORGOT_PASS);
		}
		else{
			user = userManagement.findByEmail(email);
		
   try {
   	
   	String host="localhost";
   	String to = user.getEmail();
   	String from = "samanthadray@koala.fr";
   	String subject = "Password Modification";
   	String messageText = "Hello " + user.getLogin() + "copy the link below to change your password. "
				+ "If you have an other problem, contact the administrator.   " + "\n\n"
				+ "http://localhost:8080/projet-final-1.0-SNAPSHOT/changePassword?token="+user.getToken();
   	
   	boolean sessionDebug= false;
   	
       // Use javamail api, set parameters from registration.properties file
       // set the session properties
       Properties props = System.getProperties();
       props.setProperty("mail.smtp.host", host);
       
       // Create email message
       //java.security.Security.addProvider(new com.sun.net.ssl.internal.ssl.Provider());
       Session mailSession = Session.getDefaultInstance(props, null);
       mailSession.setDebug(sessionDebug);
       
       MimeMessage message = new MimeMessage(mailSession);
       message.setFrom(new InternetAddress(from));
   
       InternetAddress[] address = {new InternetAddress(to)};
      
       message.setRecipients(Message.RecipientType.TO, address);
       message.setSubject(subject); 
       message.setSentDate(new Date());
       
       // Send smtp message
       
       
       message.setText("Hello " + user.getLogin() + "copy the link below to change your password. "
				+ "If you have an other problem, contact the administrator.   " + "\n\n"
				+ "http://localhost:8080/projet-final-1.0-SNAPSHOT/changePassword?token="+user.getToken());
       
       Transport tr = mailSession.getTransport("smtp");
       message.saveChanges();
       tr.send(message);
       tr.close();
       System.out.println("Mail sent successfully.");
       resp.sendRedirect("http://localhost:8080/projet-final-1.0-SNAPSHOT/login");

   }catch (MessagingException e) {
      System.out.println("Error in method sendEmailNotification: " + e);
   }}}
}
