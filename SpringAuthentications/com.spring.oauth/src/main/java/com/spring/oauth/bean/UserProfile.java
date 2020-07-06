package com.spring.oauth.bean;

public class UserProfile {
	String name;
	String email;
	
	public void setName(String name) {
		this.name = name;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getName() {
		return name;
	}

	public String getEmail() {
		return email;
	}

	@Override
	public String toString() {
		return "UserProfile [name=" + name + ", email=" + email + "]";
	}
}