package com.send.email;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;

public class JavaMailSender {
	
	@Value("${spring.mail.host}")
	 String host;
	
	@Value("${spring.mail.username}")
	 String username;
	
	@Value("${spring.mail.password}")
	 String password;
	
	@Value("${spring.mail.port}")
	 int port;
	
	@Value("${spring.mail.properties.mail.protocol}")
	 String protocol;
	
	
	  @Value("${spring.mail.properties.mail.tls}") String tls;
	  
	  @Value("${spring.mail.properties.mail.smtp.auth}") String auth;
	  
	  @Value("${spring.mail.properties.mail.smtp.starttls.enable}") String
	  startttls;
	  
	  @Value("${spring.mail.properties.mail.smtp.ssl.trust}") String ssltrust;
	 

	@Bean
	public String host() {
		return host;
	}

	@Bean
	public String username() {
		return username;
	}

	@Bean
	public String password() {
		return password;
	}

	@Bean
	public int port() {
		return port;
	}

	@Bean
	public String protocol() {
		return protocol;
	}

	
	  @Bean public String tls() { return tls; }
	  
	  @Bean public String auth() { return auth; }
	  
	  @Bean public String startttls() { return startttls; }
	  
	  @Bean public String ssltrust() { return ssltrust; }
	 
}
