package com.brix.testbed.monitor.protobuf;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.zip.ZipFile;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.brix.testbed.monitor.protobuf.TestBedProto.Channel;
import com.brix.testbed.monitor.protobuf.TestBedProto.Screen;
import com.brix.testbed.monitor.protobuf.TestBedProto.TestBed;





public class TestBedUseRestClient {

	public static void main(String[] args) {
		try {
			for(int ii=0 ;ii<1000 ;ii++){
				final int iii=ii ;
				Thread t=new Thread(()->{
					System.out.println("############## Thread Get Data 1 ###############");
					while(true){
						try {
							testBedOne(iii);
							Thread.sleep(1000*1);
						} catch (Exception e) {
						}
					}
				});
				
				t.start();
			}
				
			Thread.sleep(100000);	
				
				
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	
			
	}
	
	
	private static void testBedOne(int Thread){
		String output="";
		String out="";
		try {
			
			final String REST_URI 
		      = "http://localhost:8090/tmsapp/v1/testbeds/host/BRIX/screens/Screen 1/channels";
			Client client = ClientBuilder.newClient();
			Response rss =client.target(REST_URI).request(MediaType.APPLICATION_JSON).get();
			System.out.println(rss.readEntity(String.class));
			

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	
	
}
