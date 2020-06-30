package com.brix.testbed.monitor.role.service;

import java.util.List;
import com.brix.testbed.monitor.bean.Role;

public interface RoleService {

	public void create(Role Role)throws RoleException;
	
	public List<Role> getAll()throws RoleException;
	
	public void update(Role Role)throws RoleException;
	
	public boolean delete(long id)throws RoleException;
	
	public Role getRoleDetailsById(long id)throws RoleException;
	
	public boolean checkRoleAlreadyExist(String RoleName)throws RoleException;

}
