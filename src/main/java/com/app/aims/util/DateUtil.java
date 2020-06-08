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
		monthMap.put("JAN", 1);
		monthMap.put("FEB", 2);
		monthMap.put("MAR", 3);
		monthMap.put("APR", 4);
		monthMap.put("MAY", 5);
		monthMap.put("JUN", 6);
		monthMap.put("JUL", 7);
		monthMap.put("AUG", 8);
		monthMap.put("SEP", 9);
		monthMap.put("OCT", 10);
		monthMap.put("NOV", 11);
		monthMap.put("DEC", 12);
		
		monthValueMap.put(1,"JAN");
		monthValueMap.put(2,"FEB");
		monthValueMap.put(3,"MAR");
		monthValueMap.put(4,"APR");
		monthValueMap.put(5,"MAY");
		monthValueMap.put(6,"JUN");
		monthValueMap.put(7,"JUL");
		monthValueMap.put(8,"AUG");
		monthValueMap.put(9,"SEP");
		monthValueMap.put(10,"OCT");
		monthValueMap.put(11,"NOV");
		monthValueMap.put(12,"DEC");
		
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
