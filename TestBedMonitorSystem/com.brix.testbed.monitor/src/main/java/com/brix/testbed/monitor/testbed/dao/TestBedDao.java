package com.brix.testbed.monitor.testbed.dao;

import java.util.List;
import com.brix.testbed.monitor.bean.TestBed;

public interface TestBedDao {
	public void create(TestBed testBed)throws TestBedDaoException;
	
	public List<TestBed> getAll()throws TestBedDaoException;
	
	public void update(TestBed testBed)throws TestBedDaoException;
	
	public boolean delete(long id)throws TestBedDaoException;
	
	public TestBed getTestBedDetailsById(long id)throws TestBedDaoException;
	
	public boolean checkTestBedAlreadyExist(String testBedName)throws TestBedDaoException;

	public boolean checkUsersExistsForTestbed(long id) throws TestBedDaoException;
}
