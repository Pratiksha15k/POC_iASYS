package com.brix.testbed.monitor.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.brix.testbed.monitor.bean.ApplicationInfo;
import com.brix.testbed.monitor.bean.Result;
import com.brix.testbed.monitor.bean.User;
import com.brix.testbed.monitor.user.service.UserService;

@RestController
public class UserController implements EnumerationIF{

	@Autowired
	private UserService userService;
	
	@Autowired
	private ApplicationInfo applicationInfo;
	
	private static final Logger logger = LogManager.getLogger(UserController.class);	
	
	@PostMapping(path="/user",consumes = "application/json", produces = "application/json")
	public Result<String> createUser(@RequestBody User user){
		Result<String> result=new Result<>();
		try {
			String userName=user.getUserName();
			if(userName!=null && !userName.isEmpty()){
				if(userService.checkUserAlreadyExist(userName)){
					result.setReturnCode(I_DUPLICATE);
					result.setMessage("User Already Exist");
				}else{
					user.setEnabled(0);
					userService.create(user);
					result.setReturnCode(I_SUCCESS);
					result.setMessage("User Created");
				}
			}
		} catch (Exception e) {
			result.setReturnCode(I_EXCEPTION);
			result.setMessage(e.getMessage());
			logger.error(e);
		}
		return result;
	}
	
	@GetMapping(path="/user",produces = "application/json")
	public Result<List<User>> getUser() {
		Result<List<User>> result = new Result<>();
		List<User> list = new ArrayList<User>();
		try {
			list = userService.getAll();
			result.setResult(list);
			result.setReturnCode(I_SUCCESS);
			result.setMessage("User List");
		} catch (Exception e) {
			result.setReturnCode(I_EXCEPTION);
			result.setMessage(e.getMessage());
			logger.error(e);
		}
		return result;
	}
	
	@GetMapping(path="/user/userid/{id}/username/{username}")
	public Result<String> deleteUser(@PathVariable Long id, @PathVariable String username) {
		Result<String> result=new Result<>();
		boolean isLoggedInUser = false;
		try {
			isLoggedInUser = userService.delete(id, username);
			if(isLoggedInUser) {
				result.setReturnCode(I_INUSED);
				result.setMessage("User is in use");
			}else {
				result.setReturnCode(I_SUCCESS);
				result.setMessage("User delete");
			}
		} catch (Exception e) {
			result.setReturnCode(I_EXCEPTION);
			result.setMessage(e.getMessage());
			logger.error(e);
		}
		return result;
	}
	
	@PostMapping(path="/user/update/",consumes = "application/json", produces = "application/json")
	public Result<String> updateUser(@RequestBody User user){
		Result<String> result=new Result<>();
		try {
			userService.update(user);
			result.setReturnCode(I_SUCCESS);
			result.setMessage("User Updated");
		} catch (Exception e) {
			result.setReturnCode(I_EXCEPTION);
			result.setMessage(e.getMessage());
			logger.error(e);
		}
		return result;
	}
	
	@GetMapping(path="/user/{id}")
	public Result<User> getUserDetailsById(@PathVariable Long id) {
		Result<User> result=new Result<>();
		try {
			User user=userService.getUserDetailsById(id);
			result.setResult(user);
			result.setReturnCode(I_SUCCESS);
			result.setMessage("User Details");
		} catch (Exception e) {
			result.setReturnCode(I_EXCEPTION);
			result.setMessage(e.getMessage());
			logger.error(e);
		}
		return result;
	}
	
	@GetMapping(path="/applicationInformation")
	public Result<Map<String, Object>> getApplicationInformation(){
		Result<Map<String, Object>> result = new Result<>();
		Map<String, Object> map = new HashMap<>();
		try {
			map.put("version", applicationInfo.getVersionNumber());
			map.put("builder", applicationInfo.getBuildBy());
			map.put("name", applicationInfo.getApplicationName());
			map.put("buildDate", applicationInfo.getBuildDate());
			result.setResult(map);
			result.setMessage("Application Information");
			result.setReturnCode(I_SUCCESS);
		}catch(Exception e) {
			result.setReturnCode(I_EXCEPTION);
			result.setMessage(e.getMessage());
			logger.error(e);
		}
		return result;
	}
}
