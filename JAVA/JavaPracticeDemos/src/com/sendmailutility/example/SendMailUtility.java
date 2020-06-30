package com.sendmailutility.example;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;

public class SendMailUtility {

	public static void main(String args[]){
		SendMailUtility send = new SendMailUtility();
	}
	
	public SendMailUtility(){
		getDaysForSendingMail();
	}
	/*public String getSubscriptionEndDate() {
		
		return null;
	}*/
	
	
	private ArrayList<String> getDaysForSendingMail(){
		ArrayList<String> dateArray = new ArrayList<>();
		try{
			//dateArray.add(getConvertedDateToString(new Date(), "dd/MM/yyyy"));
			LocalDateTime ldt = LocalDateTime.ofInstant(new Date().toInstant(), ZoneId.systemDefault());
			LocalDateTime ldt1 = ldt.minusDays(15);
			LocalDateTime ldt2 = ldt1.minusDays(15);
			LocalDateTime ldt3 = ldt2.minusDays(15);
			dateArray.add(getConvertedDateToString(Date.from(ldt1.atZone(ZoneId.systemDefault()).toInstant()), "dd/MM/yyyy"));
			dateArray.add(getConvertedDateToString(Date.from(ldt2.atZone(ZoneId.systemDefault()).toInstant()), "dd/MM/yyyy"));
			dateArray.add(getConvertedDateToString(Date.from(ldt3.atZone(ZoneId.systemDefault()).toInstant()), "dd/MM/yyyy"));
			for (String string : dateArray) {
				System.out.println(string);
			}
			System.out.println();
		}catch(Exception e){
			System.out.println(e.getCause());
			System.out.println(e.getMessage());
		}
		return dateArray;
	}
	
	private String getConvertedDateToString(Date date,String pattern) throws Exception {
		SimpleDateFormat formatter = new SimpleDateFormat(pattern);
		String strDate=null;
		try {
			strDate=formatter.format(date);
		} catch (Exception e) {
			throw new Exception(e);
		}
		return strDate;
	}
}
