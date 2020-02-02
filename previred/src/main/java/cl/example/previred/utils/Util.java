package cl.example.previred.utils;

import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Set;

import com.previred.desafio.tres.uf.vo.Uf;

public class Util {
	
	public static String formatearFecha(Date date) {
		 
		 String pattern = "yyyy-MM-dd";
		 DateFormat df = new SimpleDateFormat(pattern);
		 return df.format(date);
	 }
	
	public static String formatearDouble(Double valor) {
		 
		DecimalFormat formatoNumero = new DecimalFormat("###,###.##");
		return formatoNumero.format(valor);
		
	 }
	
	public static boolean existeFecha(Set<Uf> ufs, Date fecha) {
    	return ufs.stream() 
		.anyMatch(uf -> uf.getFecha().getTime() == fecha.getTime() ); 
    }
	
}
