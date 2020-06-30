package com.brix.testbed.monitor.bean;

import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name="users")
public class User {

	@Id
	@GeneratedValue
	@Column(name="ID")
	private long id;
	
	@Column(name="NAME")
    private String name;
     
    @Column(name="SURNAME")
    private String surName;
    
    @Column(name="username")
    private String userName;
    
    @Column(name="password")
    private String password;
    
    @Column(name="EMAIL_ID")
    private String emailId;
    
    @Column(name="CREATED_DATE")
    private Date createdDate;
    
    @Column(name="UPDATED_DATE")
    private Date updatedDate;
    
    @Column(name="MOBILE_NUMBER")
    private String mobileNumber;
    
    @Column(name="DEPARTMENT")
    private String department;
    
    @Column(name="enabled")
    private int enabled;
    
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name="USER_X_ROLE", joinColumns={@JoinColumn(referencedColumnName="ID")}, 
    inverseJoinColumns={@JoinColumn(referencedColumnName="ID",updatable = false)})  
    private Set<Role> roles;
    
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name="USER_X_TESTBED", joinColumns={@JoinColumn(referencedColumnName="ID")}, 
    inverseJoinColumns={@JoinColumn(referencedColumnName="ID",updatable = false)})  
    private List<TestBed> testbeds;

	public void setTestbeds(List<TestBed> testbeds) {
		this.testbeds = testbeds;
	}

	public List<TestBed> getTestbeds() {
		return testbeds;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurName() {
		return surName;
	}

	public void setSurName(String surName) {
		this.surName = surName;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public Date getUpdatedDate() {
		return updatedDate;
	}

	public void setUpdatedDate(Date updatedDate) {
		this.updatedDate = updatedDate;
	}

	public String getMobileNumber() {
		return mobileNumber;
	}

	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

	public Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public int getEnabled() {
		return enabled;
	}

	public void setEnabled(int enabled) {
		this.enabled = enabled;
	}



}
