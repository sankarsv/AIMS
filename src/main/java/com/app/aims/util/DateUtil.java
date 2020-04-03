package com.app.aims.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.app.aims.Exceptions.InvalidRequestException;

public class DateUtil {

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
