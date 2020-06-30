package com.brix.testbed.monitor.utility;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import com.brix.testbed.monitor.protobuf.TestBedLogMessageProto.LogMessage;

@Configuration
public class TestBedLogUtility {
	
	
	private static int logLimit;

	private static Map<String, List<LogMessage>> log=new ConcurrentHashMap<>();
	
	public static void storeLog(String hostName, List<LogMessage> listMsg) throws TestBedLogUtilityException {
		try {
			if (listMsg != null && !listMsg.isEmpty()) {
				synchronized (log) {
					if (log.containsKey(hostName)) {
						List<LogMessage> list = log.get(hostName);
						if (list.size() > logLimit) {
							int removeItemSize = (list.size() - logLimit) + listMsg.size();
							for (int i = 0; i < removeItemSize; i++) {
								try {
									list.remove(i);
								} catch (Exception e) {
								}
							}
						}
						list.addAll(listMsg);
						log.put(hostName, list);
					} else {
						List<LogMessage> list = new ArrayList<>();
						list.addAll(listMsg);
						log.put(hostName, list);
					}
				}
			}
		} catch (Exception e) {
			throw new TestBedLogUtilityException(e.getMessage(), e.getCause());
		}
	}
	
	public static List<LogMessage> getLog(String hostName) throws TestBedLogUtilityException {
		List<LogMessage> list = new ArrayList<LogMessage>();
		try {
			if (log.containsKey(hostName)) {
				synchronized (log) {
					list = log.get(hostName);
				}
			}
		} catch (Exception e) {
			throw new TestBedLogUtilityException(e.getMessage(), e.getCause());
		}
		return list;
	}
	
	public static void clearLog(String hostName)throws TestBedLogUtilityException{
		try {
			if (log.containsKey(hostName)) {
				log.remove(hostName);
			}
		} catch (Exception e) {
			throw new TestBedLogUtilityException(e.getMessage(), e.getCause());
		}
	} 
	

	@Value("${log.limit}")
	public void setLogLimit(int limit) {
		logLimit = limit;
	}
	
	

	
	
	
}
