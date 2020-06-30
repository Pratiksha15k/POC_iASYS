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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.brix.testbed.monitor.bean.Result;
import com.brix.testbed.monitor.bean.TestBed;
import com.brix.testbed.monitor.testbed.service.TestBedService;

@RestController
@RequestMapping("/testbeds")
public class TestBedController implements EnumerationIF{

	@Autowired
	private TestBedService testBedService;
	
	private static final Logger logger = LogManager.getLogger(UserController.class);	
	
	@PostMapping(path="/testbed",consumes = "application/json", produces = "application/json")
	public Result<String> createTestBed(@RequestBody TestBed testBed){
		Result<String> result=new Result<>();
		try {
			String testBedName=testBed.getName();
			if(testBedName!=null && !testBedName.isEmpty()){
				if(testBedService.checkTestBedAlreadyExist(testBedName)){
					result.setReturnCode(I_DUPLICATE);
					result.setMessage("TestBed Already Exist");
				}else{
					testBedService.create(testBed);
					result.setReturnCode(I_SUCCESS);
					result.setMessage("TestBed Created");
				}
			}
		} catch (Exception e) {
			result.setReturnCode(I_EXCEPTION);
			result.setMessage(e.getMessage());
			logger.error(e);
		}
		return result;
	}
	
	@GetMapping(path="/testbed",produces = "application/json")
	public Result<List<TestBed>> getTestBeds() {
		Result<List<TestBed>> result = new Result<>();
		List<TestBed> list = new ArrayList<TestBed>();
		try {
			list = testBedService.getAll();
			result.setResult(list);
			result.setReturnCode(I_SUCCESS);
			result.setMessage("TestBed List");
			logger.info("Get testbed:");
		} catch (Exception e) {
			result.setReturnCode(I_EXCEPTION);
			result.setMessage(e.getMessage());
			logger.error(e);
		}
		return result;
	}
	
	@GetMapping(path="/testbed/testbedid/{id}")
	public Result<String> deleteTestBed(@PathVariable Long id) {
		Result<String> result=new Result<>();
		boolean flag = false;
		try {
			flag = testBedService.delete(id);
			if(flag) {
				result.setReturnCode(I_INUSED);
				result.setMessage("TestBed in use");
			}else {
				result.setReturnCode(I_SUCCESS);
				result.setMessage("TestBed delete");
			}
		} catch (Exception e) {
			result.setReturnCode(I_EXCEPTION);
			result.setMessage(e.getMessage());
			logger.error(e);
		}
		return result;
	}
	
	@GetMapping(path="/testbed/{id}")
	public Result<TestBed> getTestBedDetailsById(@PathVariable Long id) {
		Result<TestBed> result=new Result<>();
		try {
			TestBed testBed=testBedService.getTestBedDetailsById(id);
			result.setResult(testBed);
			result.setReturnCode(I_SUCCESS);
			result.setMessage("TestBed Details");
		} catch (Exception e) {
			result.setReturnCode(I_EXCEPTION);
			result.setMessage(e.getMessage());
			logger.error(e);
		}
		return result;
	}
	
	@PostMapping(path="/testbed/update/",consumes = "application/json", produces = "application/json")
	public Result<String> updateTestBed(@RequestBody TestBed testBed){
		Result<String> result=new Result<>();
		try {
			testBedService.update(testBed);
			result.setReturnCode(I_SUCCESS);
			result.setMessage("TestBed Updated");
		} catch (Exception e) {
			result.setReturnCode(I_EXCEPTION);
			result.setMessage(e.getMessage());
			logger.error(e);
		}
		return result;
	}
	
}
