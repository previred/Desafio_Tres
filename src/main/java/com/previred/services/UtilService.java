package com.previred.services;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;


public class UtilService {

/*
 * Metodo utilizado para convertir Date a LocalDate para el uso del comparador between entre fechas
 */
	public static LocalDate toLocalDate(Date dateToConvert) {
	    return dateToConvert.toInstant()
	      .atZone(ZoneId.systemDefault())
	      .toLocalDate();
	}

	/*
	 * Metodo para sumar un número determinado de dias a una fecha
	 */
	public static Date sumarDia(Date fecha, int incremento) {
		Calendar c = Calendar.getInstance();
        c.setTime(fecha);
        c.add(Calendar.DAY_OF_MONTH, incremento);
        return c.getTime();
	}
	
	/*
	 * Metodo para sumar un dia a uan fecha
	 */
	
	public static Date sumarDia(Date fecha) {
		return sumarDia(fecha, 1);
	}
	
	/*
	 * Format del valor de la uf en formato xx.xxx,xx
	 */
	public static String formatNumber (double valor) {
		NumberFormat nf = NumberFormat.getCurrencyInstance();
		DecimalFormatSymbols decimalFormatSymbols = ((DecimalFormat) nf).getDecimalFormatSymbols();
		decimalFormatSymbols.setCurrencySymbol("");
		decimalFormatSymbols.setPerMill('.');
		nf.setMaximumFractionDigits(2); 
		decimalFormatSymbols.setGroupingSeparator('.');
		((DecimalFormat) nf).setDecimalFormatSymbols(decimalFormatSymbols);
		return nf.format(valor).trim();
	}
	
	private UtilService() {

	}

}
