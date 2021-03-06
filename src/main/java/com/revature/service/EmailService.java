package com.revature.service;

import java.io.IOException;

import org.springframework.stereotype.Service;


import com.revature.awscredential.AwsCredentials;
import com.revature.emailBuilder.EmailBuilder;
import com.revature.models.AwsEmailBuilder;

/**
 * The Class EmailService.
 * service to configure the email
 * object and send it to its required 
 * destination.
 * 
 * @author 1811-Java-Nick | 01/02/2019
 * @author Austin | Joel | Zxander
 */
@Service
public class EmailService {

	
	/** The AwsCredentials. Connection for the AWS email service.*/
	AwsCredentials ac = new AwsCredentials();
	
	/** The sender. The test email associated with the AWS console to send email */
	private String SENDER = System.getenv("REFORCE_AWS_SENDER");
	
	
	/**
	 * Send email.
	 * Method that makes an email with
	 * the param and uses AWS email service
	 * to send the created email object.
	 *
	 * @param to the destination of the email.
	 * @param subject the subject of the email.
	 * @param body the content or message of the email.
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public void sendEmail(String to, String subject, String body) throws IOException {

		EmailBuilder email = new AwsEmailBuilder(ac.createSimpleEmailService());
		email.withFrom(SENDER)
			   .withTo(to)
			   .withSubject(subject)
			   .withBody(body)
			   .send();
	}
	
	/**
	 * Send templated email.
	 * Method that sends templated email
	 * with the template data and uses 
	 * AWS email service to send the 
	 * templated email object.
	 *
	 * @param to the destination of the email.
	 * @param templateName the name of the template on AWS
	 * @param templateData the content or message of the email.
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public String sendTemplatedEmail(String to, String templateName, String templateData) throws IOException {

		EmailBuilder email = new AwsEmailBuilder(ac.createSimpleEmailService());
		email.withFrom(SENDER).withTo(to).sendTemplatedEmail(templateName, templateData);
		return templateData;
	}

}
