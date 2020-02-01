package cl.desafio.tres.util;

import java.io.File;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import cl.desafio.tres.model.Response;

public class Utils {
	private  static String NAME_FILE="valores.json";
	public static void printToJSON(Response data) {
	
		 ObjectMapper mapper = new ObjectMapper();
		 mapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd"));
		 mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		 mapper.configure(com.fasterxml.jackson.core.JsonParser.Feature.ALLOW_UNQUOTED_FIELD_NAMES,
		   true);
         try {
              mapper.writeValue(new File(NAME_FILE), data); 
              System.out.println("creado correctamente");
         } catch (JsonProcessingException e) {
             e.printStackTrace();
         } catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	public static Date addDays(int days , Date t1) {
		if (t1==null) return t1;
		
        Calendar calendar = Calendar. getInstance();
        calendar.setTimeInMillis( t1.getTime());
        calendar.add(Calendar. DAY_OF_YEAR, days );
        return new Date(calendar.getTimeInMillis());
    }
	public static Boolean equalDate(Date t1 , Date t2) {
		if (t1==null || t2==null) return false;
        Calendar calendar = Calendar. getInstance();
        Calendar calendar2 = Calendar. getInstance();
        calendar.setTimeInMillis( t1.getTime());
        calendar2.setTimeInMillis( t2.getTime());

        return (calendar.get(Calendar.YEAR)==calendar2.get(Calendar.YEAR) && calendar.get(Calendar.MONTH)==calendar2.get(Calendar.MONTH) && calendar.get(Calendar.DAY_OF_YEAR)==calendar2.get(Calendar.DAY_OF_YEAR));
    }
	public static String separatedThousand(Double value) {
		String pattern="###,###.###";
		DecimalFormat myFormatter = new DecimalFormat(pattern);
		return myFormatter.format(value);
	}
}
