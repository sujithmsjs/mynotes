package com.suji.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class DateUtil {
	
	public static java.util.Date toUtilDate(String date){
		return toUtilDate(date, "yyyy-MM-dd");
	}
	
	public static java.util.Date toUtilDate(LocalDate locDate){
		Date date = Date.from(locDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
		return date;
	}
	
	public static java.util.Date toUtilDate(String date, String pattern){
		
		DateFormat formatter = new SimpleDateFormat(pattern);
		Date myDate = null;

		try {
			myDate = formatter.parse(date);
		} catch (ParseException e) {
			
			e.printStackTrace();
			myDate = new Date();
			
		}

		return myDate;
	}
	
	public static java.util.Date toUtilDate(java.sql.Date date){
		return date;
	}
	
	public static java.sql.Date toSQLDate(String date, String pattern){
		
		java.util.Date utilDate = toUtilDate(date, pattern);
		java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
		
		return sqlDate;
	}
	
	public static java.sql.Date toSQLDate(String date){
		return	toSQLDate(date, "yyyy-MM-dd");
	}
	
	public static java.sql.Date toSQLDate(java.util.Date date){
		return new java.sql.Date(date.getTime());
	}
	
	public static java.time.LocalDate toLocalDate(String date, String pattern){
		
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
		LocalDate locDate = LocalDate.parse(date, formatter);
		return locDate;
	}
	
	public static java.time.LocalDate toLocatDate(java.util.Date date){
		LocalDate locDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
		return locDate;
	}

}
