package com.brix.testbed.monitor.user.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.brix.testbed.monitor.bean.TestBed;
import com.brix.testbed.monitor.bean.User;
import com.brix.testbed.monitor.user.dao.UserDao;
import com.brix.testbed.monitor.user.service.UserException;
import com.brix.testbed.monitor.user.service.UserService;

@Service
public class UserServiceImpl implements UserService{

	@Autowired
	UserDao userDao;
	
	@Override
	public void create(User user) throws UserException {
		try {
			userDao.create(user);
		} catch (Exception e) {
			throw new UserException(e.getMessage(),e.getCause());
		}
	}

	@Override
	public List<User> getAll() throws UserException {
		List<User> users=null;
		try {
			users=userDao.getAll();
		} catch (Exception e) {
			throw new UserException(e.getMessage(),e.getCause());
		}
		return users;
	}

	@Override
	public void update(User user) throws UserException {
		try {
			userDao.update(user);
		} catch (Exception e) {
			throw new UserException(e.getMessage(),e.getCause());
		}
	}

	@Override
	public boolean delete(long id, String username) throws UserException {
		boolean flag = false;
		try {
			flag = userDao.delete(id,username);
		} catch (Exception e) {
			throw new UserException(e.getMessage(),e.getCause());
		}
		return flag;
	}

	@Override
	public User getUserDetailsById(long id) throws UserException {
		User user=new User();
		try {
			user=userDao.getUserDetailsById(id);
		} catch (Exception e) {
			throw new UserException(e.getMessage(),e.getCause());
		}
		return user;
	}

	@Override
	public boolean checkUserAlreadyExist(String userName) throws UserException {
		boolean flag=false;
		try {
			flag=userDao.checkUserAlreadyExist(userName);
		} catch (Exception e) {
			throw new UserException(e.getMessage(),e.getCause());
		}
		return flag;
	}

	@Override
	public List<TestBed> getTestBedByUser(String username) throws UserException {
		List<TestBed> list = null;
		try {
			list = userDao.getTestBedByUser(username);
		}catch(Exception e) {
			throw new UserException(e.getMessage(), e.getCause());
		}
		return list;
	}

}
