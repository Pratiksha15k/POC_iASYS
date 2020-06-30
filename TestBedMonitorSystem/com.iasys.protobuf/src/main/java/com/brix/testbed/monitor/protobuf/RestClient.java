package com.brix.testbed.monitor.protobuf;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;
import com.brix.testbed.monitor.protobuf.TestBedLogMessageProto.LogMessage;
import com.brix.testbed.monitor.protobuf.TestBedLogMessageProto.TestBedLog;
import com.brix.testbed.monitor.protobuf.TestBedProto.Channel;
import com.brix.testbed.monitor.protobuf.TestBedProto.Screen;
import com.brix.testbed.monitor.protobuf.TestBedProto.TestBed;
import com.brix.testbed.monitor.protobuf.TestBedAlarmProto.Alarm;
import com.brix.testbed.monitor.protobuf.TestBedAlarmProto.TestBedAlarm;



public class RestClient {

	public static void main(String[] args) {
		try {
			
				Thread t=new Thread(()->{
					System.out.println("############## Thread 1 ###############");
					while(true){
						try {
							testBedOne();
							Thread.sleep(1000*2);
							
						} catch (Exception e) {
						}
					}
				});
				
				Thread t2=new Thread(()->{
					System.out.println("############## Thread 2 ###############");
					while(true){
						try {
							testBedTwo();
							Thread.sleep(1000*2);
							
						} catch (Exception e) {
						}
						
					}
				});
				
				Thread t3=new Thread(()->{
					System.out.println("############## Thread 2 ###############");
					while(true){
						try {
							testBedLog();
							Thread.sleep(1000);
						} catch (Exception e) {
						}
						
					}
				});
				
				Thread t4=new Thread(()->{
					System.out.println("############## Thread 2 ###############");
					while(true){
						try {
							testBedLog();
							Thread.sleep(1000);
						} catch (Exception e) {
						}
						
					}
				});
				
				t.start();
				t2.start();
				t3.start();
				t4.start();
				
		} catch (Exception e) {
			e.printStackTrace();
		}
			
	}
	
	
	private static void testBedOne(){
		String output="";
		String out="";
		try {
			URL url = new URL("http://localhost:8090/tmsapp/v1/testbeds/channeldata"); 
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("POST");
			conn.setRequestProperty("Content-Type", "application/x-protobuf");
			conn.setRequestProperty("Accept-Charset", "UTF-8");
			
			List<Channel> channelList=new ArrayList();
			List<Channel> channelListScreen2=new ArrayList();
			
			for(int ii=0;ii<200;ii++){
				int random =new Random().nextInt(100) ;
				int alrm =-1;
				int ack = -1;
				if(ii%5==0){
					alrm =new Random().nextInt(4) ;
				}
				if(ii % 10==0)
					ack = new Random().nextInt((3 - 2) + 1) + 2;
				
			    channelList.add(Channel.newBuilder().setName("Speed"+ii).setDataType(0).setUnit("RPM").setDoubleValue(new Random().nextInt(200)).setAlarmAckflag(ack).setAlarmValue(alrm).setSeqNo(++ii).build());
//			    channelList.add(Channel.newBuilder().setName("Speed"+ii).setDataType(1).setUnit("-").setStrValue(generateString()).setAlarmAckflag(-1).setAlarmValue(-1).build());
			    channelListScreen2.add(Channel.newBuilder().setName("Speed :"+ii).setDataType(0).setUnit("RPM").setDoubleValue(new Random().nextInt(800)).setAlarmAckflag(ack).setAlarmValue(alrm).setSeqNo(++ii).build());
			    //channelListScreen2.add(Channel.newBuilder().setName("Speed000"+ii).setDataType(0).setUnit("RPM").setDoubleValue(new Random().nextInt(800)).setAlarmAckflag(ack).setAlarmValue(alrm).build());
			}
			
			
			Screen screen=Screen.newBuilder().setName("Screen 1").addAllChannelList(channelList).build();
			Screen screen2=Screen.newBuilder().setName("Screen 2").addAllChannelList(channelListScreen2).build();
			List<Screen> lst=new ArrayList();
			lst.add(screen);
			lst.add(screen2);
			TestBed testBed=TestBed.newBuilder().setName("TC-07").setHostName("NSWIN8IAVCDE04").setAlarmActive(1).addAllScreens(lst).build();
			
		
			ByteArrayOutputStream outputStream=new ByteArrayOutputStream();
			testBed.writeTo(outputStream);
			conn.setDoOutput(true);
			OutputStream os = conn.getOutputStream();
			os.write(outputStream.toByteArray());
			os.flush();
			
			if (conn.getResponseCode() != 200) {
				throw new RuntimeException("Failed : HTTP error code : " + conn.getResponseCode());
			}
			
			
			BufferedReader br = new BufferedReader(new InputStreamReader((conn.getInputStream())));
			
			//System.out.println("Output from Server .... \n");
			while ((out = br.readLine()) != null) {
				output=out;
			}

			conn.disconnect();
			
			if(output!=null && !output.isEmpty()){
				//System.out.println(output);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	private static void testBedTwo(){
		String output="";
		String out="";
		try {
			URL url = new URL("http://localhost:8090/tmsapp/v1/testbeds/channeldata"); 
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("POST");
			conn.setRequestProperty("Content-Type", "application/x-protobuf");
			conn.setRequestProperty("Accept-Charset", "UTF-8");
			
			List<Channel> channelList=new ArrayList();
			List<Channel> channelListScreen2=new ArrayList();
			
			for(int ii=0;ii<30;ii++){
				int alrm =-1;
				int ack = -1;
				if(ii%5==0){
					alrm =new Random().nextInt(4) ;
				}
				if(ii % 10==0)
					ack =1 ;
				
			    channelList.add(Channel.newBuilder().setName("Speed"+ii).setDataType(0).setUnit("RPM").setDoubleValue(new Random().nextInt(200)).setAlarmAckflag(ack).setAlarmValue(alrm).build());
			    channelListScreen2.add(Channel.newBuilder().setName("Speed"+ii).setDataType(0).setUnit("RPM").setDoubleValue(new Random().nextInt(800)).setAlarmAckflag(ack).setAlarmValue(alrm).build());

			}		
			
			Screen screen=Screen.newBuilder().setName("Screen 1").addAllChannelList(channelList).build();
			Screen screen2=Screen.newBuilder().setName("Screen 2").addAllChannelList(channelListScreen2).build();
			List<Screen> lst=new ArrayList<>();
			lst.add(screen);
			lst.add(screen2);
			TestBed testBed=TestBed.newBuilder().setName("TC-06").setHostName("VCDEVELOPEMENT-01").addAllScreens(lst).build();
			
		
			ByteArrayOutputStream outputStream=new ByteArrayOutputStream();
			testBed.writeTo(outputStream);
			conn.setDoOutput(true);
			OutputStream os = conn.getOutputStream();
			os.write(outputStream.toByteArray());
			os.flush();
			
			if (conn.getResponseCode() != 200) {
				throw new RuntimeException("Failed : HTTP error code : " + conn.getResponseCode());
			}
			
			
			BufferedReader br = new BufferedReader(new InputStreamReader((conn.getInputStream())));
			
			//System.out.println("Output from Server .... \n");
			while ((out = br.readLine()) != null) {
				output=out;
			}

			conn.disconnect();
			
			if(output!=null && !output.isEmpty()){
				//System.out.println(output);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	private static void testBedLog(){
		String output="";
		String out="";
		try {
			URL url = new URL("http://localhost:8090/tmsapp/v1/testbeds/logmessage"); 
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("POST");
			conn.setRequestProperty("Content-Type", "application/x-protobuf");
			conn.setRequestProperty("Accept-Charset", "UTF-8");
			
			
			List<LogMessage> list=new ArrayList<>();
			int count=0;
			for(int i=0;i<new Random().nextInt(190);i++){
				count=count+1;
				int type=new Random().nextInt(4);
				if(count%4 == 0)
					type=4;
				LogMessage logMessage=LogMessage.newBuilder().setSeqNo(count).setType(type).setSeverity(new Random().nextInt(4)).
						setValue("Engine Started with RPM "+new Random().nextInt(100)).setDateTime(getConvertedDateToString(new Date())).build();
				list.add(logMessage);
			}
			TestBedLog bedLog=TestBedLog.newBuilder().setName("TC-07").setHostName("NSWIN8IAVCDE04").addAllLogMessage(list).build();
			
		
			ByteArrayOutputStream outputStream=new ByteArrayOutputStream();
			bedLog.writeTo(outputStream);
			conn.setDoOutput(true);
			OutputStream os = conn.getOutputStream();
			os.write(outputStream.toByteArray());
			os.flush();
			
			if (conn.getResponseCode() != 200) {
				throw new RuntimeException("Failed : HTTP error code : " + conn.getResponseCode());
			}
			
			
			BufferedReader br = new BufferedReader(new InputStreamReader((conn.getInputStream())));
			
			//System.out.println("Output from Server .... \n");
			while ((out = br.readLine()) != null) {
				output=out;
			}

			conn.disconnect();
			
			if(output!=null && !output.isEmpty()){
				//System.out.println(output);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private static void getTestBedAlarms() {
		String output="";
		String out="";
		try {
			URL url = new URL("http://localhost:8090/tmsapp/v1/testbeds/alarms"); 
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("POST");
			conn.setRequestProperty("Content-Type", "application/x-protobuf");
			conn.setRequestProperty("Accept-Charset", "UTF-8");
			List<Alarm> list=new ArrayList<>();
			for(int i=0;i<new Random().nextInt(4);i++){
				int alrm =-1;
				int ack = -1;
				if(i%5==0){
					alrm =new Random().nextInt(4) ;
				}
				if(i % 10==0)
					ack =1 ;
				Alarm alarm=Alarm.newBuilder().setName("Speed"+i).setDoubleValue(new Random().nextInt(200)).
						setDataType(1).setUnit("RPM").setAlarmValue(alrm).setAlarmAckflag(ack).setSeqNo(++i).build();
				list.add(alarm);
			}
			
			TestBedAlarm bedAlarm=TestBedAlarm.newBuilder().setName("TC-07").setHostName("NSWIN8IAVCDE04").addAllAlarms(list).build();
			
			
			ByteArrayOutputStream outputStream=new ByteArrayOutputStream();
			bedAlarm.writeTo(outputStream);
			conn.setDoOutput(true);
			OutputStream os = conn.getOutputStream();
			os.write(outputStream.toByteArray());
			os.flush();
			
			if (conn.getResponseCode() != 200) {
				throw new RuntimeException("Failed : HTTP error code : " + conn.getResponseCode());
			}
			
			
			BufferedReader br = new BufferedReader(new InputStreamReader((conn.getInputStream())));
			
			//System.out.println("Output from Server .... \n");
			while ((out = br.readLine()) != null) {
				output=out;
			}

			conn.disconnect();
			
			if(output!=null && !output.isEmpty()){
				//System.out.println(output);
			}

		}catch(Exception e) {
			
		}
	}
	
	private static void testBed2Log(){
		String output="";
		String out="";
		try {
			URL url = new URL("http://localhost:8090/tmsapp/v1/testbeds/logmessage"); 
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("POST");
			conn.setRequestProperty("Content-Type", "application/x-protobuf");
			conn.setRequestProperty("Accept-Charset", "UTF-8");
			
			List<LogMessage> list=new ArrayList<>();
			for(int i=0;i<new Random().nextInt(190);i++){
				LogMessage logMessage=LogMessage.newBuilder().setType(1).setSeverity(1).
						setValue("Engine Started with RPM "+new Random().nextInt(100)).setDateTime(getConvertedDateToString(new Date())).build();
				list.add(logMessage);
			}
			TestBedLog bedLog=TestBedLog.newBuilder().setName("TestBed 2").setHostName("PVM").addAllLogMessage(list).build();
			
		
			ByteArrayOutputStream outputStream=new ByteArrayOutputStream();
			bedLog.writeTo(outputStream);
			conn.setDoOutput(true);
			OutputStream os = conn.getOutputStream();
			os.write(outputStream.toByteArray());
			os.flush();
			
			if (conn.getResponseCode() != 200) {
				throw new RuntimeException("Failed : HTTP error code : " + conn.getResponseCode());
			}
			
			
			BufferedReader br = new BufferedReader(new InputStreamReader((conn.getInputStream())));
			
			//System.out.println("Output from Server .... \n");
			while ((out = br.readLine()) != null) {
				output=out;
			}

			conn.disconnect();
			
			if(output!=null && !output.isEmpty()){
				//System.out.println(output);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static String generateString() {
		int leftLimit = 97; // letter 'a'
		int rightLimit = 122; // letter 'z'
		int targetStringLength = 5;
		Random random = new Random();

		String generatedString = random.ints(leftLimit, rightLimit + 1).limit(targetStringLength)
				.collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append).toString();

		return generatedString;
	}
	
	public static String getConvertedDateToString(Date date) throws Exception {
		SimpleDateFormat formatter = new SimpleDateFormat("dd-mm-yyyy hh:mm:ss");
		String strDate=null;
		try {
			strDate=formatter.format(date);
		} catch (Exception e) {
			throw new Exception(e);
		}
		return strDate;
	}

	
	
}
