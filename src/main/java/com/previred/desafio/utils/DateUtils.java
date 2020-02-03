package com.previred.desafio.utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;

import org.springframework.stereotype.Service;

@Service
public class DateUtils {

	private static final int FEBRERO = 2;
	private static final int VEINTINUEVE = 29;
	private static final int VEINTEOCHO = 28;
	private static final int TREINTA = 30;
	private static final int TREINTAYUNO = 31;

	public int getTotalDayPerMont(HashMap<Integer, Integer> daysXmonth, int month, int year) {

		return (month == FEBRERO) ? (isLeap(year) ? VEINTINUEVE : VEINTEOCHO) : daysXmonth.get(month);

	}

	public boolean isLeap(int year) {
		GregorianCalendar calendar = new GregorianCalendar();
		if (calendar.isLeapYear(year)) {
			return true;
		}
		return false;
	}

	public HashMap<Integer, Integer> getDaysXmonth() {

		HashMap<Integer, Integer> daysMonth = new HashMap<Integer, Integer>();
		int thirtyOneDays[] = { 1, 3, 5, 7, 8, 10, 12 };
		int thirtyDays[] = { 4, 6, 9, 11 };

		for (int i : thirtyDays) {
			daysMonth.put(i, TREINTA);
		}

		for (int i : thirtyOneDays) {
			daysMonth.put(i, TREINTAYUNO);
		}

		return daysMonth;
	}

	public SimpleDateFormat dateFormat() {

		String strDateFormat = "yyyy-MM-dd";

		SimpleDateFormat objSDF = new SimpleDateFormat(strDateFormat);

		return objSDF;
	}

	public Date getMoreDays(Date date, int cantDias) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.DATE, cantDias);
		return cal.getTime();
	}
}
