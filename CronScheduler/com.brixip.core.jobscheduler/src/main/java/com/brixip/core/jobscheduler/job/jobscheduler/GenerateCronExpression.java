package com.brixip.core.jobscheduler.job.jobscheduler;

import java.util.List;

import com.brixip.core.library.NameValueUnit;

public class GenerateCronExpression {
	
	public static String generateCronExpression(List<NameValueUnit> nameValueUnits, String selectedTab) {
		String cronExpression = "";
		switch(selectedTab){  
		case "secondsView":  
			NameValueUnit secondNameValue =
			nameValueUnits.stream().filter(x->x.getName().equalsIgnoreCase("secondsText")).findFirst().orElse(null); 
			if(secondNameValue!=null){
				String secondValue = secondNameValue.getValue().toString();
				if(secondValue!=null && secondValue.length()!=0){
					int seconds = Integer.parseInt(secondValue);
					cronExpression = "0/"+seconds+" * * * * ?";
				}
			}
			break;  
		case "minutesView":  
			NameValueUnit minuteNameValue =
			nameValueUnits.stream().filter(x->x.getName().equalsIgnoreCase("minutesText")).findFirst().orElse(null); 
			if(minuteNameValue!=null){
				String minuteValue = minuteNameValue.getValue().toString();
				if(minuteValue!=null && minuteValue.length()!=0){
					int minutes = Integer.parseInt(minuteValue);
					cronExpression = "0"+" 0/"+minutes+" * 1/1 * ?";
				}
			} 
			break;  
		case "hourlyView":
			NameValueUnit hourNameValue =
			nameValueUnits.stream().filter(x->x.getName().equalsIgnoreCase("everyHoursRadio")).findFirst().orElse(null); 
			if(hourNameValue!=null){
				String hoursValue = hourNameValue.getValue().toString();
				if(hoursValue!=null && hoursValue.length()!=0){
					int hours = getIntegerValue(nameValueUnits, "hoursText");
					cronExpression = "0 0 0/"+hours+" 1/1 * ?";
				}else{
					int hours = getIntegerValue(nameValueUnits, "hourselect");
					int minutes = getIntegerValue(nameValueUnits, "minuteselect");
					cronExpression = "0 "+minutes+" "+hours+" 1/1 * ?";
				}
			}  
			break;
		case "dailyView":
			NameValueUnit everyDayNameValue =
			nameValueUnits.stream().filter(x->x.getName().equalsIgnoreCase("everyDayRadio")).findFirst().orElse(null); 
			if(everyDayNameValue!=null){
				String dayNameValue = everyDayNameValue.getValue().toString();
				if(dayNameValue!=null && dayNameValue.length()!=0){
					int day = getIntegerValue(nameValueUnits, "dayText");
					cronExpression = "0 0 12 1/"+day+" * ?";
				}else{
					int hours = getIntegerValue(nameValueUnits, "hourselectindaily");
					int minutes = getIntegerValue(nameValueUnits, "minuteselectindaily");
					cronExpression = "0 "+minutes+" "+hours+" ? * MON-FRI";
				}
			}  
			break;
		case "weekelyView":
			int weekDay;
			String weekDayStr = "";
			int hour = 0;
			int minute = 0;
			weekDay = getIntegerValue(nameValueUnits, "Monday");
			if(weekDay == 1)
				weekDayStr = weekDayStr+"MON"+",";
			weekDay = getIntegerValue(nameValueUnits, "Thuesday");
			if(weekDay == 1)
				weekDayStr = weekDayStr+"TUE"+",";
			weekDay = getIntegerValue(nameValueUnits, "Wednsday");
			if(weekDay == 1)
				weekDayStr = weekDayStr+"WED"+",";
			weekDay = getIntegerValue(nameValueUnits, "Thursday");
			if(weekDay == 1)
				weekDayStr = weekDayStr+"THU"+",";
			weekDay = getIntegerValue(nameValueUnits, "Friday");
			if(weekDay == 1)
				weekDayStr = weekDayStr+"FRI"+",";
			weekDay = getIntegerValue(nameValueUnits, "Saturday");
			if(weekDay == 1)
				weekDayStr = weekDayStr+"SAT"+",";
			weekDay = getIntegerValue(nameValueUnits, "Sunday");
			if(weekDay == 1)
				weekDayStr = weekDayStr+"SUN"+",";
			hour = getIntegerValue(nameValueUnits, "hourselectinweekely");
			minute = getIntegerValue(nameValueUnits, "minuteselectinweekely");
			weekDayStr = weekDayStr.replace("/,\\s*$/", "");
			cronExpression = "0 "+minute+" "+hour+" ? * "+weekDayStr;
			break;
		case "monthlyView": 
			int hours = 0;
			int minutes = 0;
			NameValueUnit everyDayRadio = 
					nameValueUnits.stream().filter(x->x.getName().equalsIgnoreCase("everyDayInMonthRadio")).findAny().orElse(null);
			hours = getIntegerValue(nameValueUnits, "hoursInMonthly");
			minutes = getIntegerValue(nameValueUnits, "minutesInMonthly");
			if(everyDayRadio!=null){  
				String everyDayValue = everyDayRadio.getValue().toString();
				if(everyDayValue!=null && everyDayValue.length()!=0){
					int	day = getIntegerValue(nameValueUnits, "dayMonthText");
					int	month = getIntegerValue(nameValueUnits, "dayInMonthText");
					cronExpression = "0 "+minutes+" "+hours+" "+day+" 1/"+month+" ?";
				}else{
					System.out.println();
					int weekcount = getIntegerValue(nameValueUnits, "countingText");
					int days = getIntegerValue(nameValueUnits, "daysText");
					int month = getIntegerValue(nameValueUnits, "dayInMonthText1");
					cronExpression = "0 "+minutes+" "+hours+" ? 1/"+month+" "+days+"#"+weekcount;
				}
			}
			break;
		case "yearlyView":
			int hourss = 0;
			int minutess = 0;
			NameValueUnit everyDayInYearRadio = 
					nameValueUnits.stream().filter(x->x.getName().equalsIgnoreCase("everyDayInYearRadio")).findAny().orElse(null);
			hourss = getIntegerValue(nameValueUnits, "hoursInYearly");
			minutess = getIntegerValue(nameValueUnits, "minutesInYearly");
			if(everyDayInYearRadio!=null){
				String everyDayValue = everyDayInYearRadio.getValue().toString();
				if(everyDayValue!=null && everyDayValue.length()!=0){
					int	day = getIntegerValue(nameValueUnits, "dayInYearText");
					String	month = getStringValue(nameValueUnits, "monthYearText1");
					cronExpression = "0 "+minutess+" "+hourss+" "+day+" "+month+" ?";
				}else{
					int weekdaytype = getIntegerValue(nameValueUnits, "countingTextInYear");
					int days = getIntegerValue(nameValueUnits, "daysTextInYear");
					int month = getMonthIntegerValue(nameValueUnits, "monthYearText");
					cronExpression = "0 "+minutess+" "+hourss+" ? 1/"+month+" "+days+"#"+weekdaytype;
				}
			}
			break;
		}
		return cronExpression;
	}
	
	private static int getMonthIntegerValue(List<NameValueUnit> nameValueUnits, String string) {
		String monthStr = getStringValue(nameValueUnits, string);
		int monthInt = 0;
		switch(monthStr){
		case "JAN":
			monthInt = 1;
			break;
		case "FEB":
			monthInt =  2;
			break;
		case "MAR":
			monthInt =  3;
			break;
		case "APR":
			monthInt =  4;
			break;
		case "MAY":
			monthInt =  5;
			break;
		case "JUN":
			monthInt =  6;
			break;
		case "JUL":
			monthInt =  7;
			break;
		case "AUG":
			monthInt =  8;
			break;
		case "SEP":
			monthInt =  9;
			break;
		case "OCT":
			monthInt =  10;
			break;
		case "NOV":
			monthInt =  11;
			break;
		case "DEC":
			monthInt =  12;
			break;
		}
		return monthInt;
	}

	private static String getStringValue(List<NameValueUnit> nameValueUnits, String valueToBeStream) {
		String textValue="";
		NameValueUnit textNameValue =
				nameValueUnits.stream().filter(x->x.getName().equalsIgnoreCase(valueToBeStream)).findFirst().orElse(null); 
		if(textNameValue!=null){
			textValue = textNameValue.getValue().toString();
			if(textValue!=null && textValue.length()!=0)
				return textValue;
		}
		return textValue;
	}

	private static int getIntegerValue(List<NameValueUnit> nameValueUnits, String valueToBeStream){
		int integerValue = 0;
		NameValueUnit textNameValue =
				nameValueUnits.stream().filter(x->x.getName().equalsIgnoreCase(valueToBeStream)).findFirst().orElse(null); 
		if(textNameValue!=null){
			String textValue = textNameValue.getValue().toString();
			if(textValue!=null && textValue.length()!=0)
				integerValue = Integer.parseInt(textValue);
		}
		return integerValue;
	}
}
