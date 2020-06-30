package com.brix.testbed.monitor.utility;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateConverterUtility {

	public static Date getConvertedDate(String strDate,String pattern) throws Exception {
		Date date = null;
		SimpleDateFormat formatter = new SimpleDateFormat(pattern);
		try {
			date = formatter.parse(strDate);
		} catch (Exception e) {
			throw new Exception(e);
		}
		return date;
	}
	
	@SuppressWarnings("unused")
	public static Date getConvertedDate(Date dDate) throws Exception {
		Date date = null;
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		try {
			date = getConvertedDate(formatter.format(dDate),"dd/MM/yyyy");
		} catch (Exception e) {
			throw new Exception(e);
		}
		return date;
	}
	
	public static String getConvertedDateToString(Date date,String pattern) throws Exception {
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
