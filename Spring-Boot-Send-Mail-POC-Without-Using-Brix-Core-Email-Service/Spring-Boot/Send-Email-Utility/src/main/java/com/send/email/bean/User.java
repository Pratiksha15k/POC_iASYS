package com.send.email.bean;

import java.io.Serializable;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
@JsonIgnoreProperties(ignoreUnknown = true)
public class User implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@JsonProperty("toUser")
	String toUser;
	
	@JsonProperty("toCCUser")
	String toCCUser;
	
	@JsonProperty("subject")
	String subject;
	
	@JsonProperty("body")
	String body;
	
	@JsonProperty("toUser")
	public String getToUser() {
		return toUser;
	}
	@JsonProperty("toUser")
	public void setToUser(String toUser) {
		this.toUser = toUser;
	}
	@JsonProperty("toCCUser")
	public String getToCCUser() {
		return toCCUser;
	}
	@JsonProperty("toCCUser")
	public void setToCCUser(String toCCUser) {
		this.toCCUser = toCCUser;
	}
	@JsonProperty("subject")
	public String getSubject() {
		return subject;
	}
	@JsonProperty("subject")
	public void setSubject(String subject) {
		this.subject = subject;
	}
	@JsonProperty("body")
	public String getBody() {
		return body;
	}
	@JsonProperty("body")
	public void setBody(String body) {
		this.body = body;
	}
}
