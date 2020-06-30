package com.send.email;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.web.client.RestTemplate;

import com.send.email.bean.User;

@SpringBootApplication
public class SendEmailUtilityApplication implements CommandLineRunner {

	static final String URL_USERDETAILS = "http://localhost:8081/getUserDetails/getDetails";
	@Autowired
	JavaMailSenderImpl javaMailSender;
	
	public static void main(String[] args) {
		SpringApplication.run(SendEmailUtilityApplication.class, args);
	}
	
	@Override
    public void run(String... args) throws Exception {
        //main(args);
		getDetails();
    }
	private void getDetails() {
		RestTemplate restTemplate = new RestTemplate();

		User[] userList = restTemplate.getForObject(URL_USERDETAILS, User[].class);

		List<User> list = Arrays.asList(userList);

		list = Collections.synchronizedList(list);
		
		sendEmail(list);
	}
	
	private void sendEmail(List<User> listUser) {
		if(listUser!=null) { 
			for (User user : listUser) {
				SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
				simpleMailMessage.setTo(user.getToUser());
				simpleMailMessage.setCc(user.getToCCUser());
				simpleMailMessage.setSubject(user.getSubject());
				simpleMailMessage.setText(user.getBody());
				
				javaMailSender.send(simpleMailMessage);
			}
		}
	}
}
