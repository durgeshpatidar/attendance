package com.incture.attendance.service;

import java.util.Date;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import com.incture.attendance.dao.EmployeeDao;
import com.incture.attendance.dto.EmployeeDto;
import com.incture.attendance.utils.ResponseDto;

@Service
@Transactional
@PropertySource(value = { "classpath:application.properties" })
public class EmailService {

	@Autowired
	private OtpService otpService;
	@Autowired
	private Environment environment;

	@Autowired
	private EmployeeDao employeeDao;
	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	public ResponseDto sendMail(EmployeeDto employeeDto) {
		logger.info("EmailService | sendMail | Execution start input " + employeeDto);
		ResponseDto responseDto = new ResponseDto();
		responseDto.setStatus(Boolean.TRUE);
		responseDto.setStatusCode(200);
		boolean status = employeeDao.verifyEmail(employeeDto);
		if (status == false) {
			responseDto.setStatus(Boolean.FALSE);
			responseDto.setStatusCode(500);
			responseDto.setMessage("Email is not registered !");
			return responseDto;
		}
		int otp = otpService.generateOTP(employeeDto.getEmail());
		String to = employeeDto.getEmail();
		String from = environment.getRequiredProperty("mail.user");
		String password = environment.getRequiredProperty("mail.password");
		Properties properties = new Properties();

		properties.put("mail.smtp.host", environment.getRequiredProperty("mail.smtp.host"));
		properties.put("mail.smtp.port", environment.getRequiredProperty("mail.smtp.port"));
		properties.put("mail.smtp.auth", environment.getRequiredProperty("mail.smtp.auth"));
		properties.put("mail.smtp.starttls.enable", environment.getRequiredProperty("mail.smtp.starttls.enable"));

		try {

			Authenticator auth = new Authenticator() {
				public PasswordAuthentication getPasswordAuthentication() {
					return new PasswordAuthentication(from, password);
				}
			};
			// Get the default Session object.
			javax.mail.Session session = javax.mail.Session.getInstance(properties, auth);

			// Create a default MimeMessage object.
			MimeMessage message = new MimeMessage(session);

			// Set From: header field of the header.
			message.setFrom(new InternetAddress(from, "NoReply-Time & Attendance"));

			// Set To: header field of the header.
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));

			// Set Subject: header field
			message.setSubject("Password Reset Request:Time & Attendance");

			// Now set the actual message
			message.setText("Hi, \nGreetings! \nYour OTP : " + otp
					+ " for forgot password\nYou are just a step away from accessing your TIME & ATTENDANCE APP account. \nThe code is valid for 3 minutes and usable only once. \nOnce you have verified the code,you'll prompted to set a new password immediately.This is to ensure that only you have access to your account. \nDon't share with anyone\n\nBest Regards,\nTime & Attendance Team");
			message.setSentDate(new Date());
			// Send message
			Transport.send(message);
			System.out.println("Sent message successfully....");
		} catch (Exception e) {
			e.printStackTrace();
			responseDto.setStatus(Boolean.FALSE);
			responseDto.setStatusCode(500);
			responseDto.setMessage("Some problem in sending mail");
			System.out.println(e.toString());
		}
		return responseDto;
	}
}
