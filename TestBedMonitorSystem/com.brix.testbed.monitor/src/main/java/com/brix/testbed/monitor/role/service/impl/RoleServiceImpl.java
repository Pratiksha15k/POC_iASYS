package com.brix.testbed.monitor.role.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.brix.testbed.monitor.bean.Role;
import com.brix.testbed.monitor.role.dao.RoleDao;
import com.brix.testbed.monitor.role.service.RoleException;
import com.brix.testbed.monitor.role.service.RoleService;

@Service
public class RoleServiceImpl implements RoleService{

	@Autowired
	RoleDao roleDao;
	
	@Override
	public void create(Role Role) throws RoleException {
		try {
			roleDao.create(Role);
		}catch(Exception e) {
			throw new RoleException(e.getMessage(), e.getCause());
		}
	}

	@Override
	public List<Role> getAll() throws RoleException {
		List<Role> list = null;
		try {
			list = roleDao.getAll();
		}catch(Exception e) {
			throw new RoleException(e.getMessage(), e.getCause());
		}
		return list;
	}

	@Override
	public void update(Role Role) throws RoleException {
		try {
			roleDao.update(Role);
		}catch(Exception e) {
			throw new RoleException(e.getMessage(), e.getCause());
		}
	}

	@Override
	public boolean delete(long id) throws RoleException {
		boolean flag = false;
		try {
			flag = roleDao.delete(id);
		}catch(Exception e) {
			throw new RoleException(e.getMessage(), e.getCause());
		}
		return flag;
	}

	@Override
	public Role getRoleDetailsById(long id) throws RoleException {
		Role role = null;
		try {
			role = roleDao.getRoleDetailsById(id);
		}catch(Exception e) {
			throw new RoleException(e.getMessage(), e.getCause());
		}
		return role;
	}

	@Override
	public boolean checkRoleAlreadyExist(String RoleName) throws RoleException {
		boolean flag = false;
		try {
			flag = roleDao.checkRoleAlreadyExist(RoleName);
		}catch(Exception e) {
			throw new RoleException(e.getMessage(), e.getCause());
		}
		return flag;
	}

}
