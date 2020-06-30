package com.brix.testbed.monitor.user.dao;
import java.util.List;
import com.brix.testbed.monitor.bean.TestBed;
import com.brix.testbed.monitor.bean.User;

public interface UserDao {
	
	public void create(User user)throws UserDaoException;
	
	public List<User> getAll()throws UserDaoException;
	
	public void update(User user)throws UserDaoException;
	
	public boolean delete(long id,String username)throws UserDaoException;
	
	public User getUserDetailsById(long id) throws UserDaoException;
	
	public boolean checkUserAlreadyExist(String userName)throws UserDaoException;
	
	public User getUserByUsername(String name)throws UserDaoException;
	
	public List<TestBed> getTestBedByUser(String username)throws UserDaoException;
}
