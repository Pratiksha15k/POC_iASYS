package com.brix.testbed.monitor.role.dao;

import java.util.List;
import com.brix.testbed.monitor.bean.Role;

public interface RoleDao {
public void create(Role role)throws RoleDaoException;
	
	public List<Role> getAll()throws RoleDaoException;
	
	public void update(Role Role)throws RoleDaoException;
	
	public boolean delete(long id)throws RoleDaoException;
	
	public Role getRoleDetailsById(long id)throws RoleDaoException;
	
	public boolean checkRoleAlreadyExist(String RoleName)throws RoleDaoException;
	
	public boolean checkUsersExistsForRole(long id) throws RoleDaoException;
}
