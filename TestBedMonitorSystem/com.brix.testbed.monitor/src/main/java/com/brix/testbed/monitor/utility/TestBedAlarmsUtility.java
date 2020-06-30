package com.brix.testbed.monitor.utility;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import com.brix.testbed.monitor.protobuf.TestBedAlarmProto.Alarm;

public class TestBedAlarmsUtility {
	
	private static Map<String, List<Alarm>> alarm = new ConcurrentHashMap<>();

	public static void storeTestBedAlarm(String hostname, List<Alarm> alarmList) throws TestBedAlarmsUtilityException{
		try {
			if(alarmList != null && !alarmList.isEmpty()) {
				synchronized (alarm) {
					List<Alarm> list = new ArrayList<>();
					list.addAll(alarmList);
					alarm.put(hostname, list);
				}
			}
		}catch(Exception e) {
			throw new TestBedAlarmsUtilityException(e.getMessage(), e.getCause());
		}
	}
	
	public static List<Alarm> getTestBedAlarm(String hostname) throws TestBedAlarmsUtilityException{
		List<Alarm> alarmList = new ArrayList<>();
		try {
			if(alarm.containsKey(hostname)) {
				synchronized (alarm) {
					alarmList = alarm.get(hostname);
				}
			}
		}catch(Exception e) {
			throw new TestBedAlarmsUtilityException(e.getMessage(), e.getCause());
		}
		return alarmList;
	}
	
	public static void clearAlarm(String hostName)throws TestBedLogUtilityException{
		try {
			if (alarm.containsKey(hostName)) {
				alarm.remove(hostName);
			}
		} catch (Exception e) {
			throw new TestBedLogUtilityException(e.getMessage(), e.getCause());
		}
	} 
}
