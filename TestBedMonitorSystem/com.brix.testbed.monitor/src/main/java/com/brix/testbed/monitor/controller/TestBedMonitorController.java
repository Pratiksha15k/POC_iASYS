package com.brix.testbed.monitor.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.converter.protobuf.ProtobufHttpMessageConverter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.brix.testbed.monitor.bean.Result;
import com.brix.testbed.monitor.protobuf.TestBedLogMessageProto.LogMessage;
import com.brix.testbed.monitor.protobuf.TestBedLogMessageProto.TestBedLog;
import com.brix.testbed.monitor.protobuf.TestBedAlarmProto.Alarm;
import com.brix.testbed.monitor.protobuf.TestBedAlarmProto.TestBedAlarm;
import com.brix.testbed.monitor.protobuf.TestBedProto;
import com.brix.testbed.monitor.protobuf.TestBedProto.TestBed;
import com.brix.testbed.monitor.user.service.UserService;
import com.brix.testbed.monitor.protobuf.TestBedProto.Screen;
import com.brix.testbed.monitor.utility.TestBedAlarmsUtility;
import com.brix.testbed.monitor.utility.TestBedLogUtility;
import com.brix.testbed.monitor.utility.TestBedMonitorUtility;
import com.google.gson.Gson;
import com.google.protobuf.util.JsonFormat.Printer;;

@RestController
@RequestMapping("/v1")
public class TestBedMonitorController implements EnumerationIF {

	@Autowired
	private UserService userService;
	
	private static final Logger logger = LogManager.getLogger(TestBedMonitorController.class);	
	
	@Bean
	ProtobufHttpMessageConverter protobufHttpMessageConverter() {
		return new ProtobufHttpMessageConverter();
	}
	
	
	@PostMapping(path="/testbeds/channeldata",consumes = "application/x-protobuf", produces = "application/json")
	public Result<String> testBedInfo(@RequestBody TestBedProto.TestBed testBed) {
		Result<String> result = new Result<>();
		try {
			TestBedMonitorUtility.storeTestBed(testBed);
			result.setReturnCode(I_SUCCESS);
			//logger.info(testBed.getHostName()+" : "+testBed.toString());
			System.out.println(testBed.getHostName()+" : "+testBed.toString());
		} catch (Exception e) {
			result.setReturnCode(I_EXCEPTION);
			result.setMessage(e.getMessage());
			logger.error(e);
		}
		return result;
	}
	
	@PostMapping(path="/testbeds/channeldata",consumes = "application/json", produces = "application/json")
	public Result<String> testBedInfo(@RequestBody String str) {
		Result<String> result = new Result<>();
		try {
			TestBed.Builder testBed = TestBed.newBuilder();
			com.google.protobuf.util.JsonFormat.parser().merge(str, testBed);
			TestBedMonitorUtility.storeTestBed(testBed.build());
			result.setReturnCode(I_SUCCESS);
			result.setMessage("Test Bed info Accepted");
		} catch (Exception e) {
			result.setReturnCode(I_EXCEPTION);
			result.setMessage(e.getMessage());
			logger.error(e);
		}
		return result;
	}
	
	@GetMapping("/testbeds")
	public Result<List<Map<String, String>>> getTestBedList() {
		Result<List<Map<String, String>>> result = new Result<>();
		try {
			List<TestBed> listTestBed = TestBedMonitorUtility.getTestBedList();
			List<Map<String, String>> list = new ArrayList<>();
			if (listTestBed != null) {
				listTestBed.forEach(x -> {
					Map<String, String> map = new HashMap<>();
					map.put("Testbed", x.getName());
					map.put("Host", x.getHostName());
					list.add(map);
				});
			}
			result.setResult(list);
			result.setReturnCode(I_SUCCESS);
		} catch (Exception e) {
			result.setMessage(e.getMessage());
			result.setReturnCode(I_EXCEPTION);
			logger.error(e.getMessage());
		}
		return result;
	}
	
	
	@GetMapping("/testbeds/user")
	public Result<List<Map<String, String>>> getTestBedByUser(){
		Result<List<Map<String, String>>> result = new Result<>();
		List<Map<String, String>> testbedlist = new ArrayList<>();
		try {
			Authentication auth = SecurityContextHolder.getContext().getAuthentication();
			UserDetails userDetails = (UserDetails) auth.getPrincipal();
			String username = userDetails.getUsername();
			List<com.brix.testbed.monitor.bean.TestBed> list = userService.getTestBedByUser(username);
			if(list!=null && !list.isEmpty()) {
				list.forEach(x -> {
					Map<String, String> map = new HashMap<>();
					map.put("Testbed", x.getName()); 
					map.put("Host",x.getHostname());
					testbedlist.add(map);
				});
				result.setResult(testbedlist);
				result.setReturnCode(I_SUCCESS);
			}
		}catch(Exception e) {
			result.setReturnCode(I_EXCEPTION);
			result.setMessage(e.getMessage());
			logger.error(e);
		}
		return result;
	}
	
	@GetMapping("/testbeds/host/{hostname}/screens")
	public Result<List<Map<String, String>>> getScreens(@PathVariable String hostname){
		Result<List<Map<String, String>>> result = new Result<>();
		try {
			List<Screen> listScreen = TestBedMonitorUtility.getScreenListByHostName(hostname);	
			List<Map<String, String>> list = new ArrayList<>();
			if(listScreen!=null && !listScreen.isEmpty()){
				listScreen.forEach(x -> {
					Map<String, String> map = new HashMap<>();
					map.put("screen", x.getName());
					map.put("hostname", hostname);
					list.add(map);
				});
			}
			result.setResult(list);
			result.setReturnCode(I_SUCCESS);
		} catch (Exception e) {
			result.setMessage(e.getMessage());
			result.setReturnCode(I_EXCEPTION);
			logger.error(e.getMessage());
		}
		return result;
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@GetMapping("/testbeds/host/{hostname}/screens/{screens}/channels")
	public Result<Map> getChannels(@PathVariable String hostname,@PathVariable String screens){
		Result<Map> result=new Result<>();
		Screen screen=null;
		int alarmActive = 0;
		Gson gson = new Gson();
		Map map =null;
		try {
			screen = TestBedMonitorUtility.getScreenByHostNameAndScreenName(hostname,screens);
			alarmActive = TestBedMonitorUtility.getAlarmActiveForScreen(hostname);
			if(screen!=null){
				Printer printer = com.google.protobuf.util.JsonFormat.printer().includingDefaultValueFields() ;
				String sformated = printer.print(screen);
				map = gson.fromJson(sformated, Map.class);
				map.put("alarmActive", alarmActive);
			}
			result.setResult(map);
			result.setReturnCode(I_SUCCESS);
		} catch (Exception e) {
			result.setMessage(e.getMessage());
			result.setReturnCode(I_EXCEPTION);
			logger.error(e.getMessage());
		}
		return result;
	}
	
	@PostMapping(path = "/testbeds/logmessage", consumes = "application/x-protobuf", produces = "application/json")
	public Result<String> testBedLogMessage(@RequestBody TestBedLog testBedLog) {
		Result<String> result = new Result<>();
		try {
			TestBedLogUtility.storeLog(testBedLog.getHostName(), testBedLog.getLogMessageList());
			result.setReturnCode(I_SUCCESS);
		} catch (Exception e) {
			result.setReturnCode(I_EXCEPTION);
			result.setMessage(e.getMessage());
			logger.error(e);
		}
		return result;
	}
	
	@PostMapping(path = "/testbeds/logmessage", consumes = "application/json", produces = "application/json")
	public Result<String> testBedLogMessage(@RequestBody String str) {
		Result<String> result = new Result<>();
		try {
			TestBedLog.Builder builder=TestBedLog.newBuilder();
			com.google.protobuf.util.JsonFormat.parser().merge(str, builder);
			TestBedLog log=builder.build();
			TestBedLogUtility.storeLog(log.getHostName(), log.getLogMessageList());
			result.setReturnCode(I_SUCCESS);
			result.setMessage("Test Bed Log");
			System.out.println("log message --------"+str);
		} catch (Exception e) {
			result.setReturnCode(I_EXCEPTION);
			result.setMessage(e.getMessage());
			logger.error(e);
		}
		return result;
	}
	
	@PostMapping(path = "/testbeds/status/{status}", consumes = "application/json", produces = "application/json")
	public Result<String> testBedStatus(@PathVariable int status,@RequestBody String str) {
		Result<String> result = new Result<>();
		try {
			System.out.println(str);
			result.setReturnCode(I_SUCCESS);
			result.setMessage("Test Bed Status");
		} catch (Exception e) {
			result.setReturnCode(I_EXCEPTION);
			result.setMessage(e.getMessage());
			logger.error(e);
		}
		return result;
	}
	
	@SuppressWarnings("rawtypes")
	@GetMapping("/testbeds/host/{hostname}/logmessage")
	public Result<Map> getLogMessage(@PathVariable String hostname){
		Result<Map> result=new Result<>();
		Gson gson = new Gson();
		Map map =null;
		List<LogMessage> list=null;
		try {
			list= TestBedLogUtility.getLog(hostname);
			if(list==null){
				list=new ArrayList<>();
			}
			TestBedLog testBedLog=TestBedLog.newBuilder().setHostName(hostname).addAllLogMessage(list).build();
			if(testBedLog!=null){
				Printer printer = com.google.protobuf.util.JsonFormat.printer().includingDefaultValueFields();
				String sformated = printer.print(testBedLog);
				map = gson.fromJson(sformated, Map.class);
			}
			result.setResult(map);
			result.setReturnCode(I_SUCCESS);
		} catch (Exception e) {
			result.setMessage(e.getMessage());
			result.setReturnCode(I_EXCEPTION);
			logger.error(e.getMessage());
		}
		return result;
	}
	
	@SuppressWarnings("rawtypes")
	@PostMapping(path = "/testbeds/tests/teststart", consumes = "application/json", produces = "application/json")
	public Result<String> testStart(@RequestBody String str) {
		Result<String> result = new Result<>();
		Gson gson = new Gson();
		try {
			Map map = gson.fromJson(str, Map.class);
			if (map != null && !map.isEmpty()) {
				String hostName=map.get("hostName").toString();
				TestBedLogUtility.clearLog(hostName);
				//TestBedAlarmsUtility.clearAlarm(hostName);
			}
			result.setReturnCode(I_SUCCESS);
			result.setMessage("Log clear");
		} catch (Exception e) {
			result.setReturnCode(I_EXCEPTION);
			result.setMessage(e.getMessage());
			logger.error(e);
		}
		return result;
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@GetMapping("/testbeds/host/{hostname}/alarms")
	public Result<Map<String, String>> getAlarms(@PathVariable String hostname){
		Result<Map<String, String>> result=new Result<>();
		Gson gson = new Gson();
		Map map =null;
		List<Alarm> list=null;
		try {
			list = TestBedAlarmsUtility.getTestBedAlarm(hostname);
			if(list==null){
				list=new ArrayList<>();
			}
			TestBedAlarm testBedAlarm = TestBedAlarm.newBuilder().setHostName(hostname).addAllAlarms(list).build();
			if(testBedAlarm!=null){
				Printer printer = com.google.protobuf.util.JsonFormat.printer().includingDefaultValueFields();
				String sformated = printer.print(testBedAlarm);
				map = gson.fromJson(sformated, Map.class);
			}
			result.setResult(map);
			result.setReturnCode(I_SUCCESS);
		} catch (Exception e) {
			result.setMessage(e.getMessage());
			result.setReturnCode(I_EXCEPTION);
			logger.error(e.getMessage());
		}
		return result;
	}
	
	@PostMapping(path = "/testbeds/alarms", consumes = "application/x-protobuf", produces = "application/json")
	public Result<String> testBedAlarms(@RequestBody TestBedAlarm testBedAlarm) {
		Result<String> result = new Result<>();
		try {
			TestBedAlarmsUtility.storeTestBedAlarm(testBedAlarm.getHostName(), testBedAlarm.getAlarmsList());
			result.setReturnCode(I_SUCCESS);
		} catch (Exception e) {
			result.setReturnCode(I_EXCEPTION);
			result.setMessage(e.getMessage());
			logger.error(e);
		}
		return result;
	}
	
	@PostMapping(path = "/testbeds/alarms", consumes = "application/json", produces = "application/json")
	public Result<String> testBedAlarms(@RequestBody String str) {
		Result<String> result = new Result<>();
		try {
			TestBedAlarm.Builder builder=TestBedAlarm.newBuilder();
			com.google.protobuf.util.JsonFormat.parser().merge(str, builder);
			TestBedAlarm alarm=builder.build();
			TestBedAlarmsUtility.storeTestBedAlarm(alarm.getHostName(), alarm.getAlarmsList());
			result.setReturnCode(I_SUCCESS);
			result.setMessage("Test Bed Alarm");
			System.out.println("str"+str);
		} catch (Exception e) {
			result.setReturnCode(I_EXCEPTION);
			result.setMessage(e.getMessage());
			logger.error(e);
		}
		return result;
	}
	
}
