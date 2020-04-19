package prueba.previred.asotom;

import com.google.gson.Gson;
import com.opencsv.CSVWriter;
import com.previred.desafio.tres.uf.DatosUf;
import com.previred.desafio.tres.uf.Valores;
import com.previred.desafio.tres.uf.vo.Uf;

import java.io.File;
import java.io.FileWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.json.Json;
import javax.json.JsonObject;

import org.apache.log4j.Logger;

public class App 
{
	public 	static final Logger logger		=	Logger.getLogger(App.class);
	
	static Valores 	valores		=	new Valores();
	static DatosUf 	datos 		=	DatosUf.getInstance();
	//Valores: inicio y fin
	static Date 	inicio		=	inicio();
	static Date 	fin			=	fin();
	
	static final String	ruta	=	"C:/Users/Alex/Documents/valores.json";
	
	public static void main( String[] args ){
		//Formato fecha
    	SimpleDateFormat sdf	= 	new SimpleDateFormat("yyyy/MM/dd");
    	
    	logger.debug("Fecha Inicio:	" 	+	sdf.format(inicio));
    	logger.debug("Fecha Fin: 	"	+ 	sdf.format(fin));    	
    	logger.debug( "Tamaño json:	"	+ 	cantMax());    	
    	logger.debug( "Json UFs: 	"	+ 	valoresUf());  	

    	try {
        	
    		logger.debug(sdf.format(inicio));
    		
    		String 	fecIni			=	sdf.format(inicio);
    		String 	fecFin			=	sdf.format(fin);
    		Date 	fecha			=	sdf.parse("2010/01/01");
            
    		//El método getUf retorna el valor UF para una fecha
            logger.debug("valor UF fecha: " + datos.getUf(fecha));
            
            //El método getUfs retorna una lista de valores de UF para un rango dado
            logger.debug("Lista valores UF rango(" + fecIni + " - " + fecFin + ") = " + datos.getUfs(inicio, fin));    
            
            //Tamaño getUfs
            logger.debug("Tamaño getUfs:	" + datos.getUfs(inicio, fin).size());   
            
            //Completa Lista uf a 100
            Set<Uf> listaCompleta		=	completarValores(inicio, fin, valoresUf());
            
            logger.debug("COMPLETAR VALORES: " + listaCompleta.size());
            
            //Ordenar lista por fecha
            ordenarLista(listaCompleta);
            
            Gson 		gson 			= 	new Gson();
            List<Uf> 	listaOrdenada 	=  	ordenarLista(listaCompleta);
            String 		lista 			= 	gson.toJson(listaOrdenada);
           
                        
            logger.debug("Lista ordenada:	" + lista);
            
            //Crear Archivo Json
            //generarArchivoJson(lista, "C:/Users/Alex/Documents/jsonUFs.json");
            
            //Completa y modifica json solicitado
            lista 	= 	completaArchivoJSON(fecIni, fecFin, lista);
            
            
            //Genera Archivo json completo
            generarArchivoJson(lista , ruta);
            
            //Genera CSV
            //generarArchivoCSV(listaOrdenada);
            
    	}catch (ParseException  e) {
    		logger.debug(e);
		}  	
    }
	
	/*********************************************************************** DESARROLLO *******************************************************************/
    
    //La lista de UF están dentro del rango de fechas (inicio y fin)
    public static Date inicio(){
    	return valores.getRango().getInicio();
    }    
    public static Date fin(){
    	return valores.getRango().getFin();
    }
   
    //La cantidad de valores para uf son máximo 100 y min 90
   public static int cantMax(){
	   return valores.getRango().getUfs().size();
   }
   
   //El listado entregado con los valores UF no son secuenciales (contiene laguna de valores) y no se encuentra ordenado
    public static Set<Uf> valoresUf(){
    	return valores.getRango().getUfs();
    }
    
    //Escribir un algoritmo para complementar los valores de UF para las fechas faltantes en la lista contenidas en la clase Ufs que retorna getRango
    //Para complementar los valores de UF se pueden utilizar los métodos getUf y getUfs de la clase com.previred.desafio.tres.uf.DatosUf.
    public static Set<Uf> completarValores(Date ini, Date fin, Set<Uf> ufs) {
    	List<Uf> 	datosUf =  	datos.getUfs(ini, fin);	
    	Set<Uf>		auxSet	=	new HashSet<Uf>();
    	
    	//datosUf.stream().sorted().collect(Collectors.toList()); 	
    	//logger.debug("*******: " + ufs.size());
    	
    	datosUf.stream().filter(data -> auxSet.size() < 100).forEach(datUf->{
			for(Uf uf : ufs) {
				auxSet.add(uf);					
				if(!(uf.getFecha().equals(datUf.getFecha()))) {				
					auxSet.add(datos.getUf(datUf.getFecha()));									
				}	
			}
    	});
    	
    	return auxSet;
    }
    
    //La lista de salida debe esta ordenada de forma descendente.
    public static List<Uf> ordenarLista(Set<Uf> set){
    	List<Uf> mainList = new ArrayList<Uf>();
    	mainList.addAll(set);    	
    	//Collections.sort(mainList, Comparator.comparing(Uf::getFecha)); 
    	
    	Collections.sort(
    			mainList,
    		    new Comparator<Uf>() {
    		        public int compare(Uf p1, Uf p2){
    		            return p1.getFecha().compareTo(p2.getFecha());
    		        }
    		    }
    		);
    	
    	return mainList;
    }
    
    // Para la implementación debe elegir uno de los siguientes formatos de salida.
    //Crear un archivo JSON 
    public static void generarArchivoJson(String data, String ruta) {
    	try {
    		FileWriter fileWriter = new FileWriter(ruta);
            fileWriter.write(data);
            fileWriter.close();            
    	}catch (Exception e) {
			logger.debug(e);
		}
    }
    
    //Debe contener la fecha de inicio “inicio”
    //Debe contener la fecha de fin “fin”
    //La lista de valores de “UFs” con los valores de fecha de uf “fecha” y valor de la uf “dato”
    public static String completaArchivoJSON(String fecIni, String fecFin, String lista) {
    	// JSON-P
        JsonObject jsonp = Json.createObjectBuilder()
        		.add("inicio", fecIni)
        		.add("fin", fecFin)
        		.add("UFs", lista)
        		.build();
        lista 	= 	jsonp.toString();            
        lista 	= 	lista.replaceAll("valor", "dato");
        return lista;
    }
    
    // Para la implementación debe elegir uno de los siguientes formatos de salida.
    //Crear un archivo CSV  
    public static void generarArchivoCSV(List<Uf> data) {
    	String ruta	=	"C:/Users/Alex/Documents/archivoCSV.csv";
    	try {
    		
    		boolean existe = new File(ruta).exists();

    	    File fichero = new File("C:/Users/Alex/Documents","archivoCSV.csv");
    	    CSVWriter escritura;
    	    if(!existe){
    	        if(fichero.createNewFile()){
    	            escritura = new CSVWriter(new FileWriter(fichero));
    	            List<String[]> arra = toArrayString(data);
    	            escritura.writeAll(arra);
    	            escritura.close();
    	        }
    	    }else{
    	        escritura = new CSVWriter(new FileWriter(fichero));
    	        List<String[]> arra = toArrayString(data);
    	        escritura.writeAll(arra);
    	        escritura.close();
    	    }

    	}catch (Exception e) {
			logger.debug(e);
		}
    }
    //Transformar Lista a String arrray
    private static List<String[]> toArrayString(List<Uf> lista){
        List<String[]> records = new ArrayList<String[]>();
        //Cabecera del archivo
        records.add(new String[]{"fecha","valor"});
        Iterator<Uf> it = lista.iterator();
        while(it.hasNext()){
            Uf s = it.next();
            records.add(new String[] {s.getFecha().toString(), s.getValor().toString()});
        }
        return records;
    }
    
}
