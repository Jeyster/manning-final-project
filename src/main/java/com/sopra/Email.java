package com.sopra;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

//Class qui permet d'envoyer des Emails (C'est juste un test)
// Il faut télécharger smtp4dev pour simuler une boite de reception de mail : https://smtp4dev.codeplex.com/


public class Email {

	 public static void main(String[] args) {    
	      // Adresse Email d'envoie
	      String to = "jojo@hotmail.fr";

	      // Adresse Email de reception
	      String from = "jordan@jordan.fr";

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
	         message.setText("Hello user, click on the link below to change your password. "
	         		+ "If you have an other problem, contact the administrator.   " +"\n\n" 
	        		 + "http://localhost:8080/projet-final-1.0-SNAPSHOT/edition.html");
	         

	         // Send message
	         Transport.send(message);
	         System.out.println("Sent message successfully....");
	      } catch (MessagingException mex) {
	         mex.printStackTrace();
	      }
	   }
	
	
	
	
}
