package demo;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Date;

import com.brix.licence.server.protobuf.LicenceServerProto.ProductInfo;
import com.brix.licence.server.protobuf.LicenceServerProto.UserInfo;



public class RestClient {

	public static void main(String[] args) {
		try {
			for(int i=0;i<10;i++){
				restClient();
			}
			//killLicence();
			//restClientServerDetails();
		} catch (Exception e) {
			e.printStackTrace();
		}
			
	}
	
	
	private static void restClient(){
		String output="";
		String out="";
		try {
			URL url = new URL("http://localhost:8888/brixserver/getlicence"); 
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("POST");
			conn.setRequestProperty("Content-Type", "application/x-protobuf");
			conn.setRequestProperty("Accept-Charset", "UTF-8");
			
			UserInfo userInfo=UserInfo.newBuilder().setFunctionGroup("11").setHost("12.33253.336").
					setInTime(new Date().getTime()).setUser("Vijay").setProduct("HODS").setVersion("V1.0").build();
			
			ByteArrayOutputStream outputStream=new ByteArrayOutputStream();
			userInfo.writeTo(outputStream);
			conn.setDoOutput(true);
			OutputStream os = conn.getOutputStream();
			os.write(outputStream.toByteArray());
			os.flush();
			
			if (conn.getResponseCode() != 200) {
				throw new RuntimeException("Failed : HTTP error code : " + conn.getResponseCode());
			}
			
			
			BufferedReader br = new BufferedReader(new InputStreamReader((conn.getInputStream())));
			
			System.out.println("Output from Server .... \n");
			while ((out = br.readLine()) != null) {
				output=out;
			}

			conn.disconnect();
			
			if(output!=null && !output.isEmpty()){
				System.out.println(output);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	private static void killLicence(){
		String output="";
		String out="";
		try {
			URL url = new URL("http://localhost:8888/brixserver/killlicence"); 
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("POST");
			conn.setRequestProperty("Content-Type", "application/x-protobuf");
			conn.setRequestProperty("Accept-Charset", "UTF-8");
			String key="bf9888eb-442a-4feb-8910-4ab141403bc5";
			conn.setDoOutput(true);
			OutputStream os = conn.getOutputStream();
			os.write(key.getBytes());
			os.flush();
			
			if (conn.getResponseCode() != 200) {
				throw new RuntimeException("Failed : HTTP error code : " + conn.getResponseCode());
			}
			
			
			BufferedReader br = new BufferedReader(new InputStreamReader((conn.getInputStream())));
			
			System.out.println("Output from Server .... \n");
			while ((out = br.readLine()) != null) {
				output=out;
			}

			conn.disconnect();
			
			if(output!=null && !output.isEmpty()){
				System.out.println(output);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	private static void restClient2(){
		String output="";
		String out="";
		try {
			URL url = new URL("http://localhost:8888/brixserver/getlicence"); 
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("POST");
			conn.setRequestProperty("Content-Type", "application/x-protobuf");
			conn.setRequestProperty("Accept-Charset", "UTF-8");
			
			UserInfo userInfo=UserInfo.newBuilder().setFunctionGroup("11").setHost("12.33253.336").
					setInTime(new Date().getTime()).setUser("Vijay222").setProduct("HODS").setVersion("V1.0").build();
			
			ByteArrayOutputStream outputStream=new ByteArrayOutputStream();
			userInfo.writeTo(outputStream);
			conn.setDoOutput(true);
			OutputStream os = conn.getOutputStream();
			os.write(outputStream.toByteArray());
			os.flush();
			
			if (conn.getResponseCode() != 200) {
				throw new RuntimeException("Failed : HTTP error code : " + conn.getResponseCode());
			}
			
			
			BufferedReader br = new BufferedReader(new InputStreamReader((conn.getInputStream())));
			
			System.out.println("Output from Server .... \n");
			while ((out = br.readLine()) != null) {
				output=out;
			}

			conn.disconnect();
			
			if(output!=null && !output.isEmpty()){
				System.out.println(output);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	private static void restClientServerDetails(){
		String output="";
		String out="";
		try {
			URL url = new URL("http://localhost:8888/brixserver/nodeserverdetails"); 
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("POST");
			conn.setRequestProperty("Content-Type", "application/x-protobuf");
			conn.setRequestProperty("Accept-Charset", "UTF-8");
			ProductInfo productInfo=ProductInfo.newBuilder().setProduct("BRIXLAB").setVersion("001").setHost("172.16.200.59").
					setFunctionGroup("Transmission").setLicenceCount(30).setExpires("Perment").setLicenseDuration("LIFETIME").build();
			
			ByteArrayOutputStream outputStream=new ByteArrayOutputStream();
			productInfo.writeTo(outputStream);
			conn.setDoOutput(true);
			OutputStream os = conn.getOutputStream();
			os.write(outputStream.toByteArray());
			os.flush();
			
			if (conn.getResponseCode() != 200) {
				throw new RuntimeException("Failed : HTTP error code : " + conn.getResponseCode());
			}
			
			
			BufferedReader br = new BufferedReader(new InputStreamReader((conn.getInputStream())));
			
			System.out.println("Output from Server .... \n");
			while ((out = br.readLine()) != null) {
				output=out;
			}

			conn.disconnect();
			
			if(output!=null && !output.isEmpty()){
				System.out.println(output);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
}
