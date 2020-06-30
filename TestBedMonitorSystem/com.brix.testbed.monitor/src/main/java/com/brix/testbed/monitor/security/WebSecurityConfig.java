package com.brix.testbed.monitor.security;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	DataSource dataSource;

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.jdbcAuthentication().dataSource(dataSource)
				.usersByUsernameQuery("select username,password, '1' from users where username=?")
				.authoritiesByUsernameQuery("select username,'ROLE_USER' from users where username=?");
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return NoOpPasswordEncoder.getInstance();
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable().authorizeRequests().antMatchers("/","/resources/**", "/v1/**","/h2/**")
		.permitAll()
		.anyRequest().authenticated()
		.and().formLogin()
		.loginPage("/login") 
		.defaultSuccessUrl("/dashboard")
		.failureUrl("/login?error=true")
		.permitAll()
		.and().logout().logoutSuccessUrl("/log-out?logout=true")
		.invalidateHttpSession(true)
		.permitAll()
		.and()
		.headers().frameOptions().sameOrigin();
	}
}
