package cl.devru.desafiotres.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtils {
	
	public static final String SHORT_FORMAT = "yyyy-MM-dd";
	
    public static synchronized String toStringFormat(Date date, String dateFormat){
        SimpleDateFormat formatter = new SimpleDateFormat(dateFormat);
        return formatter.format(date);
    }
    
    public static synchronized Date toDate(String dateString, String pattern) {
        if(dateString == null || dateString.trim().length() < 3) {
            return null;
        }
        try {
            return new SimpleDateFormat(pattern).parse(dateString);
        } catch (ParseException e) {
        	System.err.println("ParseException: Error parse date: "+e);
        	return null;
        }
    }    

    public static synchronized Date calculateDate(Date fecha, int dias){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(fecha); // Configuramos la fecha que se recibe
        calendar.add(Calendar.DAY_OF_YEAR, dias);  // numero de días a añadir, o restar en caso de días<0
        return calendar.getTime(); // Devuelve el objeto Date con los nuevos días añadidos
    }    
}
