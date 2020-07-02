package com.send.email;

import java.util.Properties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSenderImpl;

public class JavaMailSenderService{
	
	@Autowired
	private String host;
	
	@Autowired
	private String username;
	
	@Autowired
	private String password;
	
	@Autowired
	private int port;
	
	@Autowired
	private String protocol;
	
	@Autowired
	private String tls;
	
	@Autowired
	private String auth;
	
	@Autowired
	private String startttls;
	
	@Autowired
	private String ssltrust;
	
	public JavaMailSenderImpl javaMailSender() {
		JavaMailSenderImpl javaMailSender = new JavaMailSenderImpl();
		Properties properties = new Properties();
		
		properties.put("spring.mail.properties.mail.tls", tls);
		properties.put("spring.mail.properties.mail.smtp.auth", auth);
		properties.put("spring.mail.properties.mail.smtp.starttls.enable", startttls);
		properties.put("spring.mail.properties.mail.smtp.ssl.trust", ssltrust);
		
		javaMailSender.setHost(host);
		javaMailSender.setPassword(password);
		javaMailSender.setPort(port);
		javaMailSender.setProtocol(protocol);
		javaMailSender.setUsername(username);
		javaMailSender.setJavaMailProperties(properties);
		
		return javaMailSender;
	}
	
}