package com.brix.testbed.monitor.utility;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.brix.testbed.monitor.protobuf.TestBedProto.Screen;
import com.brix.testbed.monitor.protobuf.TestBedProto.TestBed;

public class TestBedMonitorUtility {
	
	private static Map<String, TestBed> map=new ConcurrentHashMap<>();
	
	public static void storeTestBed(TestBed testBed)throws TestBedMonitorUtilityException{
		try {
			map.put(testBed.getHostName(), testBed);
		} catch (Exception e) {
			throw new TestBedMonitorUtilityException(e.getMessage(),e.getCause());
		}
		
	}
	

	public static List<TestBed> getTestBedList()throws TestBedMonitorUtilityException{
		List<TestBed> listTestBed=null;
		try {
			if(!map.isEmpty()){
				listTestBed = new ArrayList(map.values());
			}
		} catch (Exception e) {
			throw new TestBedMonitorUtilityException(e.getMessage(),e.getCause());
		}
		return listTestBed;
	}
	
	public static List<Screen> getScreenListByHostName(String hostName)throws TestBedMonitorUtilityException{
		List<Screen> list = new ArrayList<>();
		try {
			TestBed testBed = map.get(hostName);
			if (testBed != null) {
				list = testBed.getScreensList();
			}
		} catch (Exception e) {
			throw new TestBedMonitorUtilityException(e.getMessage(), e.getCause());
		}
		return list;
	}


	public static Screen getScreenByHostNameAndScreenName(String hostname, String screens)
			throws TestBedMonitorUtilityException {
		Screen screen = null;
		try {
			TestBed bed = map.get(hostname);
			if (bed != null) {
				List<Screen> listScreen = bed.getScreensList();
				if (listScreen != null) {
					screen = listScreen.stream().filter(x -> x.getName().equalsIgnoreCase(screens)).findFirst()
							.orElse(null);
				}
			}
		} catch (Exception e) {
			throw new TestBedMonitorUtilityException(e.getMessage(), e.getCause());
		}
		return screen;
	}

	public static int getAlarmActiveForScreen(String hostname) throws TestBedMonitorUtilityException {
		int alarmActive = 0;
		try {
			TestBed bed = map.get(hostname);
			if(bed != null)
				alarmActive = bed.getAlarmActive();
		}catch(Exception e) {
			throw new TestBedMonitorUtilityException(e.getMessage(), e.getCause());
		}
		return alarmActive;
	}
	
}
