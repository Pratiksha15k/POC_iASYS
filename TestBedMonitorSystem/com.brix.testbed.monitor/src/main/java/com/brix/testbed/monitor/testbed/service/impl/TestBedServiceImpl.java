package com.brix.testbed.monitor.testbed.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.brix.testbed.monitor.bean.TestBed;
import com.brix.testbed.monitor.testbed.dao.TestBedDao;
import com.brix.testbed.monitor.testbed.service.TestBedException;
import com.brix.testbed.monitor.testbed.service.TestBedService;

@Service
public class TestBedServiceImpl implements TestBedService{

	@Autowired
	TestBedDao testbedDao;

	@Override
	public void create(TestBed testBed) throws TestBedException {
		try {
			testbedDao.create(testBed);
		} catch (Exception e) {
			throw new TestBedException(e.getMessage(),e.getCause());
		}
	}

	@Override
	public List<TestBed> getAll() throws TestBedException {
		List<TestBed> testbeds=null;
		try {
			testbeds=testbedDao.getAll();
		} catch (Exception e) {
			throw new TestBedException(e.getMessage(),e.getCause());
		}
		return testbeds;
	}

	@Override
	public void update(TestBed testBed) throws TestBedException {
		try {
			testbedDao.update(testBed);
		} catch (Exception e) {
			throw new TestBedException(e.getMessage(),e.getCause());
		}
	}

	@Override
	public boolean delete(long id) throws TestBedException {
		boolean flag = false;
		try {
			flag = testbedDao.delete(id);
		} catch (Exception e) {
			throw new TestBedException(e.getMessage(),e.getCause());
		}
		return flag;
	}

	@Override
	public TestBed getTestBedDetailsById(long id) throws TestBedException {
		TestBed testBed=new TestBed();
		try {
			testBed=testbedDao.getTestBedDetailsById(id);
		} catch (Exception e) {
			throw new TestBedException(e.getMessage(),e.getCause());
		}
		return testBed;
	}

	@Override
	public boolean checkTestBedAlreadyExist(String testBedName) throws TestBedException {
		boolean flag=false;
		try {
			flag=testbedDao.checkTestBedAlreadyExist(testBedName);
		} catch (Exception e) {
			throw new TestBedException(e.getMessage(),e.getCause());
		}
		return flag;
	}
	
	

}
