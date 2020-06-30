package com.brix.testbed.monitor.user.service;

import java.util.List;
import com.brix.testbed.monitor.bean.TestBed;
import com.brix.testbed.monitor.bean.User;

public interface UserService{

	public void create(User user)throws UserException;
	
	public List<User> getAll()throws UserException;
	
	public void update(User user)throws UserException;
	
	public boolean delete(long id, String username)throws UserException;
	
	public User getUserDetailsById(long id)throws UserException;
	
	public boolean checkUserAlreadyExist(String userName)throws UserException;
	
	public List<TestBed> getTestBedByUser(String username)throws UserException;
}
