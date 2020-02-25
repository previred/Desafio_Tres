package com.previred.desafio.util;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Set;
import java.util.logging.Logger;

import com.fasterxml.jackson.databind.ObjectMapper;

public class Util {

	private static final Logger LOGGER = Logger.getLogger("com.previred.desafio.util.Util");

	Util() {
	}

	public static String objectToJson(Object o) {
		ObjectMapper obj = new ObjectMapper();
		try {
			return obj.writeValueAsString(o);
		} catch (IOException e) {
			LOGGER.info(e.getMessage());
			return "";
		}
	}

	public static String parseDateToString(Date date) {
		SimpleDateFormat parseador = new SimpleDateFormat("yyyy-MM-dd");
		return parseador.format(date);
	}

	public static <T> Set mergeSet(Set<T> a, Set<T> b) {
		a.addAll(b);
		return a;
	}

}
