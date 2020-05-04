package previred.desafio.tres;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import org.codehaus.jackson.map.ObjectMapper;

import com.google.gson.Gson;
import com.previred.desafio.tres.uf.Valores;
import com.previred.desafio.tres.uf.vo.Uf;
import com.previred.desafio.tres.uf.vo.Ufs;


public class Solucion 
{
    public static void main( String[] args ) throws ParseException, IOException
    {
    	Valores valores = new Valores();
    	Ufs ufs = valores.getRango();
    	procesaListaUfs(ufs);
    	System.out.println( "Termino! ---> nombre del archivo [valores.json]");
    }
    
    public static void procesaListaUfs(Ufs ufs) throws ParseException, IOException {
    	ObjectMapper mapper = new ObjectMapper();
    	String json = new Gson().toJson(ufs);
    	System.out.println(json);
    	
    	List<Uf> items = new ArrayList<Uf>();
    	for (Uf e : ufs.getUfs())
    		items.add(e);
    	
    	Collections.sort(items, new Comparator<Uf>() {
    		public int compare(Uf o1, Uf o2) {
    		return o2.getFecha().compareTo(o1.getFecha());
		  }
		});
    	String json2 = new Gson().toJson(items);
    	System.out.println(json2);

    	
		Resultado resultado = new Resultado();
		resultado.setUfs(completaValores(items));
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");  
		if(!resultado.getUfs().isEmpty()) {
			resultado.setInicio(dateFormat.format(ufs.getInicio()));
			resultado.setFin(dateFormat.format(ufs.getFin()));
			mapper.writeValue(new File("d:\\valores.json"), resultado );
		}

    }
    
    public static List<UfNueva> completaValores(List<Uf> listaUfs) throws ParseException {
    	try {
    		List<UfNueva> nuevaLista = new ArrayList<UfNueva>();
    		
        	DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");  
        	for (int i = 0; i < listaUfs.size() -1 ; i++) {
				if(listaUfs.size() > i) {
					Date fecha1 = listaUfs.get(i).getFecha();
					Date fecha2;
					if(listaUfs.size()==i)
						fecha2 = listaUfs.get(i).getFecha();
					else
						fecha2 = listaUfs.get(i+1).getFecha();
					Double valor = listaUfs.get(i).getValor();
					List<Date> fechasEntre = getDatesBetween(fecha1, fecha2);
					for (Date date : fechasEntre) {
						UfNueva ufNueva = new UfNueva();
						if(fecha1.equals(date)) {
							ufNueva.setFecha(dateFormat.format(fecha1));
	        				ufNueva.setDato(valor);
	            			nuevaLista.add(ufNueva);
						}
						else {
							ufNueva.setFecha(dateFormat.format(date));
            				ufNueva.setDato(valor + 1.0);
            				nuevaLista.add(ufNueva);
						}
					}
				}
			}
        	return nuevaLista;
    	}catch(Exception e) {
    		return new ArrayList<UfNueva>();
    	}
    }
    
    public static List<Date> getDatesBetween(Date startDate, Date endDate) {
	    List<Date> datesInRange = new ArrayList<Date>();
	    Calendar calendar = new GregorianCalendar();
	    calendar.setTime(startDate);
	     
	    Calendar endCalendar = new GregorianCalendar();
	    endCalendar.setTime(endDate);
	 
	    while (calendar.before(endCalendar)) {
	        Date result = calendar.getTime();
	        datesInRange.add(result);
	        calendar.add(Calendar.DATE, 1);
	    }
	    return datesInRange;
	}
    
    public static Date getNextDate(Date curDate) throws ParseException {
    	final Date date = curDate;
    	final Calendar calendar = Calendar.getInstance();
    	calendar.setTime(date);
    	calendar.add(Calendar.DAY_OF_YEAR, 1);
    	return calendar.getTime(); 
	}
    
    public static Date parseDate(String date) {
        try {
            return new SimpleDateFormat("yyyy-MM-dd").parse(date);
        } catch (ParseException e) {
            return null;
        }
     }
}
