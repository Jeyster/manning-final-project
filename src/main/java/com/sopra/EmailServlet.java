package com.sopra;

import java.io.IOException;
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

@SuppressWarnings("serial")
@WebServlet("/sendEmail")

// Il faut télécharger smtp4dev pour simuler une boite de reception de mail : https://smtp4dev.codeplex.com/
// La servlet permet de générer un email et de l'envoyer à l'utilisateur qui recevra un lien pour changer son password.
// Pour cela on envoie un lien contenant le "token" du user qui veut changer son password, ce qui permet de connaitre l'identité de la personne directement.

public class EmailServlet extends HttpServlet {

	@EJB
	private UsersManagement userManagement;

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		User user = userManagement.findByToken(req.getParameter("token"));
		
		User superAdmin = userManagement.findSuperAdmin();

		// Adresse Email d'envoi
		String to = user.getEmail();

		// Adresse Email de reception
		String from = superAdmin.getEmail() ;

		// On suppose que l'on envoit un email de localhost
		String host = "localhost";

		// Get system properties
		Properties properties = System.getProperties();

		// Setup mail server
		properties.setProperty("mail.smtp.host", host);

		// Get the default Session object.
		Session session = Session.getDefaultInstance(properties);

		try {
			// Create a default MimeMessage object.
			MimeMessage message = new MimeMessage(session);

			// Set From: header field of the header.
			message.setFrom(new InternetAddress(from));

			// Set To: header field of the header.
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));

			// Sujet du message
			message.setSubject("Change your password !");

			// Contenu du message
			message.setText("Hello " + user.getLogin() + " , copy the link below to change your password. "
					+ "If you have an other problem, contact the administrator.   " + "\n\n"
					+ "http://localhost:8080/projet-final-1.0-SNAPSHOT/changePassword?token="+user.getToken());

			// Send message
			Transport.send(message);
			System.out.println("Sent message successfully....");
		} catch (MessagingException mex) {
			mex.printStackTrace();
		}

		resp.sendRedirect("http://localhost:8080/projet-final-1.0-SNAPSHOT/users");

	}

}
