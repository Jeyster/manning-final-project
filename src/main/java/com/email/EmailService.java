package com.email;

import java.util.*;
import javax.mail.*;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class EmailService {
 
    
 
     public static void main (String args[]) {
        try {
        	
        	String host="smtp.gmail.com";
        	String user = "samanthadray@gmail.com";
        	String password = "#il0v3COCONUTS";
        	String to = "baboutata2009@gmail.com";
        	String from = "samanthadray@gmail.com";
        	String subject = "this is a test";
        	String messageText = "If you want to reset your password click here";
        	
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
            java.security.Security.addProvider(new com.sun.net.ssl.internal.ssl.Provider());
            Session mailSession = Session.getDefaultInstance(props, null);
            mailSession.setDebug(sessionDebug);
            
            Message message = new MimeMessage(mailSession);
            message.setFrom(new InternetAddress(from));
            //String[] recipientList = to.split(",");
            InternetAddress[] address = {new InternetAddress(to)};
           // int counter = 0;
            /*for (String recipient: recipientList) {
                recipientAddress[counter] = new InternetAddress(recipient.trim());
                counter++;
            }*/
            message.setRecipients(Message.RecipientType.TO, address);
            message.setSubject(subject); 
            message.setSentDate(new Date());
            message.setContent(messageText, "text/html");
            
            // Send smtp message
            Transport tr = mailSession.getTransport("smtp");
            tr.connect(host, user, password);
            message.saveChanges();
            tr.sendMessage(message, message.getAllRecipients());
            tr.close();
 
            System.out.println("Mail sent successfully.");
 
        } catch (MessagingException e) {
           System.out.println("Error in method sendEmailNotification: " + e);
        }}}