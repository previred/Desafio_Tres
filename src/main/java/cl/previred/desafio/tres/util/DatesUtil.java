package cl.previred.desafio.tres.util;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;

import cl.previred.desafio.tres.constants.JSONConstants;

public class DatesUtil {
	private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern(JSONConstants.JSON_DATE_FORMAT);
	
	public static LocalDate toLocalDate(Date date) {
		return date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
	}
	
	public static Date toDate(LocalDate date) {
		return Date.from(date.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant());
	}
	
	public static String toDateFormat(LocalDate date) {
		return date.format(formatter);
	}
}
