package com.navastud.complementouf.utls;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

public class Utils {

	private static final ZoneId ZONE_ID = ZoneId.systemDefault();

	public static LocalDate dateToLocalDate(Date date) {

		if (date == null) {
			throw new NullPointerException("Date es nula");
		}
		Instant instant = date.toInstant();
		return instant.atZone(ZONE_ID).toLocalDate();
	}

	public static Date localDateToDate(LocalDate date) {

		if (date == null) {
			throw new NullPointerException("Date es nula");
		}
		return Date.from(date.atStartOfDay(ZONE_ID).toInstant());
	}

}
