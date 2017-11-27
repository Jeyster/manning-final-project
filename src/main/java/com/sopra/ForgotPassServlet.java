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
		req.getRequestDispatcher("/WEB-INF/forgotPass.jsp").forward(req, resp);
	}
	
@SuppressWarnings("restriction")
protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	User user = new User();
		
		//récuperer tous les paramètres de la JSP
		String email= req.getParameter("email");
		
		if (email.equals(null)||email==""){
			req.setAttribute("msg", "All Fields are mendatory" );
			getServletContext().getRequestDispatcher("/forgotPass.jsp").forward(req, resp);
		}
		
		else if (!email.equals(User.class)){
			req.setAttribute("msg", "email isn't register in database" );
			getServletContext().getRequestDispatcher("/resetPass.jsp").forward(req, resp);
		}
		else{
			user = userManagement.findByEmail(email);
			user = userManagement.findByToken(req.getParameter("token"));
			
		}

   try {
   	
   	String host="smtp.gmail.com";
   	String password = "dada4848";
   	String to = user.getEmail();
   	String from = "achemenides486@gmail.com";
   	String subject = "";
   	String messageText = "";
   	
   	boolean sessionDebug= false;
   	
   	
       // Use javamail api, set parameters from registration.properties file
       // set the session properties
       Properties props = System.getProperties();
       props.put("mail.smtp.starttls.enable", "true");
       props.put("mail.smtp.host", "smtp.gmail.com");
       props.put("mail.smtp.port", "587");
       props.put("mail.smtp.auth", "true");
       props.put("mail.smtp.starttls.required", "true");
       Session session = Session.getDefaultInstance(props, null);

       // Create email message
       //java.security.Security.addProvider(new com.sun.net.ssl.internal.ssl.Provider());
       Session mailSession = Session.getDefaultInstance(props, null);
       mailSession.setDebug(sessionDebug);
       
       Message message = new MimeMessage(mailSession);
       message.setFrom(new InternetAddress(from));
   
       InternetAddress[] address = {new InternetAddress(to)};
      
       message.setRecipients(Message.RecipientType.TO, address);
       message.setSubject(subject); 
       message.setSentDate(new Date());
       message.setContent(messageText, "text/html");
       
       // Send smtp message
       Transport tr = mailSession.getTransport("smtp");
       tr.connect(host, password);
       message.saveChanges();
       tr.sendMessage(message, message.getAllRecipients());
       tr.close();
       System.out.println("Mail sent successfully.");
       message.setText("<html><body>Hello " + user.getLogin() + " <br/> copy the link below to change your password. "
				+ "If you have an other problem, contact the administrator.   " + "\n\n"
				+ "http://localhost:8080/projet-final-1.0-SNAPSHOT/resetPass?token="+user.getToken());

   }catch (MessagingException e) {
      System.out.println("Error in method sendEmailNotification: " + e);
   }}}
