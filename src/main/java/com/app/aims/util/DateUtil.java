package com.app.aims.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.app.aims.Exceptions.InvalidRequestException;

public class DateUtil {
	
	public static Map<String,Integer> monthMap = new HashMap<String,Integer>();
	public static Map<Integer,String> monthValueMap = new HashMap<Integer,String>();
	
	static {
		monthMap.put("JANUARY", 1);
		monthMap.put("FEBRUARY", 2);
		monthMap.put("MARCH", 3);
		monthMap.put("APRIL", 4);
		monthMap.put("MAY", 5);
		monthMap.put("JUNE", 6);
		monthMap.put("JULY", 7);
		monthMap.put("AUGUST", 8);
		monthMap.put("SEPTEMBER", 9);
		monthMap.put("OCTOBER", 10);
		monthMap.put("NOVEMBER", 11);
		monthMap.put("DECEMBER", 12);
		
		monthValueMap.put(1,"JANUARY");
		monthValueMap.put(2,"FEBRUARY");
		monthValueMap.put(3,"MARCH");
		monthValueMap.put(4,"APRIL");
		monthValueMap.put(5,"MAY");
		monthValueMap.put(6,"JUNE");
		monthValueMap.put(7,"JULY");
		monthValueMap.put(8,"AUGUST");
		monthValueMap.put(9,"SEPTEMBER");
		monthValueMap.put(10,"OCTOBER");
		monthValueMap.put(11,"NOVEMBER");
		monthValueMap.put(12,"DECEMBER");
		
	}

	public static Date parseDate(String date) throws InvalidRequestException {
		try {
			String testDate =  date;
			DateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
			return formatter.parse(testDate);
		}catch(Exception e) {
			throw new InvalidRequestException("Invalid date provided. Please provide date in dd-MM-yyyy");
		}
	}
	
}
