package com.brix.testbed.monitor.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="TESTBED")
public class TestBed
{
	@Column(name="NAME")
    private String name;
	
	@Column(name="HOSTNAME")
    private String hostname;
    
	@Id
	@GeneratedValue
	@Column(name="ID")
	private long id;


	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public void setName(String name){
        this.name = name;
    }
    public String getName(){
        return this.name;
    }
	public String getHostname() {
		return hostname;
	}
	public void setHostname(String hostname) {
		this.hostname = hostname;
	}
    
  
}