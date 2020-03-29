package com.previred.desafio.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.Date;

public class DateConverter {

	public static LocalDate convertDateToLocalDate(Date date) {
		Instant instant = Instant.ofEpochMilli(date.getTime());
		LocalDateTime localDateTime = LocalDateTime.ofInstant(instant, ZoneId.systemDefault());
		LocalDate localDate = localDateTime.toLocalDate();

		return localDate;
	}
	
	public static Date convertLocalDateToDate(LocalDate localDate) {
		ZoneId defaultZoneId = ZoneId.systemDefault();
		Date date = Date.from(localDate.atStartOfDay(defaultZoneId).toInstant());
		
		return date;
	}
	
	public static long calculateNumberOfDays(Date inicio, Date fin) {
		
		LocalDate localInicio = DateConverter.convertDateToLocalDate(inicio);
		LocalDate localFin = DateConverter.convertDateToLocalDate(fin);
		
		return ChronoUnit.DAYS.between(localInicio, localFin) + 1;
	}
	
	public static String prettyPrint(Date date) {
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		return format.format(date);
	}
}
