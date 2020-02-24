package Utils;


import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;

import com.previred.desafio.tres.uf.DatosUf;
import com.previred.desafio.tres.uf.vo.Uf;

import models.UfDiariaLstModel;
import models.UfDiariaModel;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;


public class UfUtils {
	
	
	public static String dateToString( Date fechaDate ) {
		
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");  
        String strDate = dateFormat.format( fechaDate );  
				
		return strDate;
	}
	
	
	public static Long rangoDias( Date fechaInicio, Date fechaFin) {
		
		Long rango = 0L;
		rango = ( fechaFin.getTime() - fechaInicio.getTime() ) / ( 1000 * 60 * 60  * 24 );
		
		return rango;
	}
	
	public static String valorFormateado( double valor) {
		
		DecimalFormat df = new DecimalFormat("#,##0.00");
		df.setDecimalFormatSymbols(new DecimalFormatSymbols(Locale.ITALIAN));
		
		return df.format(valor) ;
	}
	
	
	public static List<UfDiariaModel> getLstUfDiaria( 	Date fechaInicio,
														Long cantidadDias,
														Map<String, Uf> ufHashMap){
		
		List<UfDiariaModel> lstUfDiaria = new ArrayList<>();
		Calendar calendar = Calendar.getInstance(); 
		DatosUf datosUf =  DatosUf.getInstance();  
		
		for( int i = 0; i <= cantidadDias; i++ ) {
			
			UfDiariaModel ufDiaria = new UfDiariaModel();

			calendar.setTime( fechaInicio ); 
			calendar.add(Calendar.DATE, i );			
			ufDiaria.setFechaUf( calendar.getTime()  );	
			ufDiaria.setFechaUfFmt( dateToString( calendar.getTime() ) );
			
			if( ufHashMap.get(  ufDiaria.getFechaUfFmt()  ) != null  ) {
				
				ufDiaria.setValorUf(  ufHashMap.get(  ufDiaria.getFechaUfFmt()  ).getValor()       );
			}else {
				
				ufDiaria.setValorUf( datosUf.getUf( ufDiaria.getFechaUf() ).getValor() );
			}
			
			lstUfDiaria.add( ufDiaria );
		}
		
		return lstUfDiaria;
	}
	
	
	public static Map<String, Uf> setUfToHashmap( Set<Uf> setUfs ){
		
		Map<String, Uf> hashMapUf = new HashMap<>();
		
		for( Uf uf : setUfs    ) {
			
			hashMapUf.put(  dateToString( uf.getFecha() ), uf );
		}
		
		return hashMapUf;
	}
	
	public static void imprimeCSV( UfDiariaLstModel ufDiaria  ) {
		
		 String UFDIARIA_CSV_FILE = "./uFsDiarias.csv";
		 
		 try {
			 
			 System.out.println( "#################"  );
			 System.out.println( "Generando archivo"  );
			 
			 BufferedWriter writer = Files.newBufferedWriter( Paths.get( UFDIARIA_CSV_FILE ) );
			 CSVPrinter csvPrinter = new CSVPrinter( writer, 
					 								 CSVFormat.DEFAULT.withHeader(	"1",
					 																ufDiaria.getFechaInicioFmt(), 
					 																ufDiaria.getFechaFinFmt() )
					 								 				  .withDelimiter( ';'));
			 
			 for( UfDiariaModel lst : ufDiaria.getLstUfDiaria()  ) {
				 
				 csvPrinter.printRecord(	"2", 
						 					lst.getFechaUfFmt(), 
						 					valorFormateado(lst.getValorUf()) );
			 }
			 
			 csvPrinter.flush();  
			 System.out.println( "Archivo generado correctamente"  );
			 System.out.println( "#################"  );
			 
		 }catch(IOException ioEx  ) {
			 
			 System.out.println( "Error :  " +  ioEx.getMessage()  );
		 }
		 
		 
		 
		 
		 
		 
		 
		 
		 
		 
		 
		 
		
		
		
	}
	
	
	
	
	

}
