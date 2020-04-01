package com.previred.desafio.tres.prueba.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Clase Utilitaria.
 * 
 * @author rsepulveda
 *
 */
public class Utility {

	/**
	 * Convierte una fecha Date a String con formato deseado.
	 * 
	 * @param date
	 * @param format
	 * @return formatedDate
	 */
	public static String convertDateToString(Date date, String format) {
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		return sdf.format(date);
	}

	/**
	 * Convierte un Set a List.
	 * 
	 * @param <T>
	 * @param set
	 * @return list
	 */
	public static <T> List<T> convertToList(Set<T> set) {
		return set.stream().collect(Collectors.toList());
	}

}
