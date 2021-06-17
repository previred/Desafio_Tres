package com.desafio.previred;


import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Properties;
import java.util.Set;

import org.apache.log4j.Logger;

import com.previred.desafio.tres.uf.Valores;
import com.previred.desafio.tres.uf.vo.Uf;
import com.previred.desafio.tres.uf.vo.Ufs;


/**
 * Clase principal encarga de orquestar los metodo para la generacion del archivo de salida de la lista de UF
 * @author ablanco
 *
 */
public class App{
	
    private static final Logger log = Logger.getLogger( App.class.getName() );
    private static final String PROPERTIES_PATH="config.properties";

	
	static CompletarValores completarValores = new CompletarValores();
	static ExportarCsv exportarCsv;
	static Properties properties;
	static int maximo;
	
	
	
    public static void main ( String[] args ){
    	
    	log.info("Method: main - iniciando Aplicacion ");
    	
    	properties=cargarPropertie();  	
    	maximo= Integer.valueOf(properties.getProperty("maximo"));
    	exportarCsv = new ExportarCsv(properties.getProperty("nombre.archivo"),properties.getProperty("fila.uno"),properties.getProperty("fila.dos"));
    	Valores valores = new Valores();
    	Ufs ufs;
    	
    	ufs=valores.getRango(); 
    	Set<Uf> setUf = ufs.getUfs();
    	log.debug("Tamaño de ufs: " + ufs.getUfs().size());
    	
    	int cantUfFaltantes=maximo-ufs.getUfs().size();
    	log.debug("Uf faltantes: " +cantUfFaltantes );
    	completarValores.getValoresfaltantes(cantUfFaltantes,ufs.getInicio(),ufs.getFin(),setUf);
        List<Uf> ufOrdenados=completarValores.ordenarUf(setUf);
       
        exportarCsv.generarArchivo(ufOrdenados,ufs.getInicio(),ufs.getFin());
        
        log.info("Method: main - Fin de Aplicacion ");
           
    }


    /**
     * Carga la propiedades configurables del proyecto
     * @return
     */
	public static Properties cargarPropertie() {
		log.info("Method: cargarPropertie ");
		properties  = new Properties();
		InputStream inputStream = App.class.getClassLoader().getResourceAsStream(PROPERTIES_PATH);
    	try {
			properties.load(inputStream);
		} catch (IOException e) {
			 log.error(e.getMessage(), e);
		}
    	
    	return properties;
	}
    
   
    
    
}
    
	
  

