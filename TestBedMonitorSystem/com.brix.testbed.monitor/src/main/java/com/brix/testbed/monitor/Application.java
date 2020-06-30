package com.brix.testbed.monitor;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
@ComponentScan(basePackages = {"com.brix"})
@PropertySources({
	@PropertySource("file:${user.dir}/configuration/application.properties"),
	@PropertySource("file:${user.dir}/configuration/${spring.profiles.active}.properties")
})
public class Application {

	private static final Logger logger = LogManager.getLogger(Application.class);	
	
	public static void main(String[] args) {
		
		System.out.println("############ Server strted #############");
		SpringApplication.run(Application.class, args);
		
		//Setting log configuration from application.properties
		PropertyConfigurator.configure(System.getProperty("user.dir")+"/configuration/application.properties");
		logger.info("Log4j");
		System.out.println("############ Application Log Level: "+logger.getEffectiveLevel()+" ##########");
	}
	
}
