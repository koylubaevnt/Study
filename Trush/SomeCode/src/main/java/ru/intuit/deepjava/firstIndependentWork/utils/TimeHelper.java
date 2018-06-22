package ru.intuit.deepjava.firstIndependentWork.utils;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

public class TimeHelper {

	public static void sleep(long millis) {
		try {
			Thread.sleep(millis);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public static Date getCurrentTime() {
		return new Date();
	}
	
	public static long getTimeInMillis() {
		return getCurrentTime().getTime(); 
	}
	
	public static int getPOSIX() {
		int millisInSecond = 1000;
		return (int) (getCurrentTime().getTime() / millisInSecond);
	}
	
	public static String getUserDateFull(Locale locale) {
		return getUserDate(locale, DateFormat.FULL, getCurrentTime());
	}
	
	public static String getUserDate(Locale locale, int dateFormatType, long dateInMillis) {
		return getUserDate(locale, dateFormatType, new Date(dateInMillis));
	}
	
	public static String getUserDate(Locale locale, int dateFormatType, Date date) {
		return DateFormat.getDateInstance(dateFormatType, locale).format(date);
	}
	
	
}
