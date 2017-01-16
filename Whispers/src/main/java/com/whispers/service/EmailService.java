package com.whispers.service;

/**
 * Created on 25 Mar, 2015
 */

import java.util.Properties;

import javax.mail.PasswordAuthentication;
import javax.mail.Session;

import com.whispers.utils.*;

/**
 * @author anka technology solutions private limited
 * 
 *         Service class accepts incoming requests and processes the incoming
 *         requests
 */

public class EmailService {

	public Session sendEmail() throws Exception {
		Log.logMessage("INFO", this.getClass().getName(),
				"Entering sendEmail()...");

		Properties props = System.getProperties();

		final String username = PropertiesFinder.getValue("SMTPUSERNAME",
				"email");
		final String password = PropertiesFinder.getValue("SMTPPASSWORD",
				"email");

		// String firstName = mnbUser.getFirstName();
		// String lastName = mnbUser.getLastName();

		Session session1;
		String host = PropertiesFinder.getValue("SMTPHOST", "email");
		String port = PropertiesFinder.getValue("SMTPPORT", "email");

		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", host); // 3
		props.put("mail.smtp.port", port); // 4
		// props.put("mail.smtp.host","smtp.gmail.com"); // 3
		// props.put("mail.smtp.port", "25"); // 4

		try {
			session1 = Session.getInstance(props,
					new javax.mail.Authenticator() {
						protected PasswordAuthentication getPasswordAuthentication() {
							return new PasswordAuthentication(username,
									password);
						}
					});
			
		} catch (Exception exception) {
			Log.logMessage("ERROR", this.getClass().getName(),
					"Error occured while sending mail from server. Error: "
							+ exception.getMessage());
			throw exception;
		}
		Log.logMessage("INFO", this.getClass().getName(),
				"Exiting sendEmail(): call successfull...");

		return session1;

	}
}
