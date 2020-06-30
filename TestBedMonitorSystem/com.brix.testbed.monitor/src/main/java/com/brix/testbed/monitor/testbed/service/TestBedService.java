package com.brix.testbed.monitor.testbed.service;

import java.util.List;

import com.brix.testbed.monitor.bean.TestBed;

public interface TestBedService {

	public void create(TestBed testBed)throws TestBedException;
	
	public List<TestBed> getAll()throws TestBedException;
	
	public void update(TestBed testBed)throws TestBedException;
	
	public boolean delete(long id)throws TestBedException;
	
	public TestBed getTestBedDetailsById(long id)throws TestBedException;
	
	public boolean checkTestBedAlreadyExist(String testBedName)throws TestBedException;
}
