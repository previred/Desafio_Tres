package com.cromero.previred.util;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class UtilDates {
	public static Date addDays(Date date, int days) {
		GregorianCalendar cal = new GregorianCalendar();
		date.setHours(0);
		date.setMinutes(0);
		date.setSeconds(0);
		cal.setTime(date);
		cal.add(Calendar.DATE, days);
				
		return cal.getTime();
	}
}
