package com.brix.testbed.monitor.controller;

import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.brix.testbed.monitor.bean.Result;
import com.brix.testbed.monitor.bean.Role;
import com.brix.testbed.monitor.role.service.RoleService;

@RestController
public class RoleController implements EnumerationIF{

	@Autowired
	RoleService roleService;
	
	private static final Logger logger = LogManager.getLogger(RoleController.class);	
	
	@PostMapping(path="/role",consumes = "application/json", produces = "application/json")
	public Result<String> createRole(@RequestBody Role role){
		Result<String> result=new Result<>();
		try {
			String rolename=role.getName();
			if(rolename!=null && !rolename.isEmpty()){
				if(roleService.checkRoleAlreadyExist(rolename)){
					result.setReturnCode(I_DUPLICATE);
					result.setMessage("User Already Exist");
				}else{
					roleService.create(role);
					result.setReturnCode(I_SUCCESS);
					result.setMessage("Role Created");
				}
			}
		} catch (Exception e) {
			result.setReturnCode(I_EXCEPTION);
			result.setMessage(e.getMessage());
			logger.error(e);
		}
		return result;
	}
	
	@GetMapping(path="/role",produces = "application/json")
	public Result<List<Role>> getRole() {
		Result<List<Role>> result = new Result<>();
		List<Role> list = new ArrayList<Role>();
		try {
			list = roleService.getAll();
			result.setResult(list);
			result.setReturnCode(I_SUCCESS);
			result.setMessage("Role List");
		} catch (Exception e) {
			result.setReturnCode(I_EXCEPTION);
			result.setMessage(e.getMessage());
			logger.error(e);
		}
		return result;
	}
	
	@GetMapping(path="/role/roleid/{id}")
	public Result<String> deleteRole(@PathVariable Long id) {
		Result<String> result=new Result<>();
		boolean flag = false;
		try {
			flag = roleService.delete(id);
			if(flag) {
				result.setReturnCode(I_INUSED);
				result.setMessage("Role is in use");
			}else {
				result.setReturnCode(I_SUCCESS);
				result.setMessage("Role delete");
			}
		} catch (Exception e) {
			result.setReturnCode(I_EXCEPTION);
			result.setMessage(e.getMessage());
			logger.error(e);
		}
		return result;
	}
	
	@PostMapping(path="/role/update/",consumes = "application/json", produces = "application/json")
	public Result<String> updateRole(@RequestBody Role role){
		Result<String> result=new Result<>();
		try {
			roleService.update(role);
			result.setReturnCode(I_SUCCESS);
			result.setMessage("User Updated");
		} catch (Exception e) {
			result.setReturnCode(I_EXCEPTION);
			result.setMessage(e.getMessage());
			logger.error(e);
		}
		return result;
	}
	
	@GetMapping(path="/role/{id}")
	public Result<Role> getUserDetailsById(@PathVariable Long id) {
		Result<Role> result=new Result<>();
		try {
			Role role=roleService.getRoleDetailsById(id);
			result.setResult(role);
			result.setReturnCode(I_SUCCESS);
			result.setMessage("Role Details");
		} catch (Exception e) {
			result.setReturnCode(I_EXCEPTION);
			result.setMessage(e.getMessage());
			logger.error(e);
		}
		return result;
	}
}
