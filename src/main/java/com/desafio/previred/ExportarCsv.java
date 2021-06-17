package com.desafio.previred;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;

import au.com.bytecode.opencsv.CSVWriter;

import com.previred.desafio.tres.uf.vo.Uf;

/**
 * Clase mantiene lo relacionado a la generación del archivo de salida
 * 
 * @author ablanco
 *
 */
public class ExportarCsv {
	
	private static final Logger log = Logger.getLogger( ExportarCsv.class.getName() );
	private  SimpleDateFormat formatoFeha = new SimpleDateFormat("yyyy-MM-dd");
	private  String nombreArchivo;
	private  String filaUno;
	private  String filaDos;

	public ExportarCsv(String nombreArchivo,String filaUno ,String filaDos) {
		this.nombreArchivo=nombreArchivo;
		this.filaUno=filaUno;
		this.filaDos=filaDos;
	}


	/**
	 * Se genera la primera fila del archivo
	 * 
	 * @param fechaInicio
	 * @param fechaFin
	 * @return
	 */
	private   String[] generarCabecera(Date fechaInicio,Date fechaFin) {
		
		
		
     	return new String[]{filaUno, formatoFeha.format(fechaInicio), formatoFeha.format(fechaFin)}; 

     }
	
	/**
	 * Metodo que convierte la lista lista de Uf a una lista de arreglo de String para
	 *  escribirla en el archivo de salida
	 * 
	 * @param ufOrdenadas
	 * @return
	 */
	private    List<String[]> parseListToString(List<Uf> ufOrdenadas) {
		
		log.info("Metodo: parseListToString ");
		List<String[]> listaDatos = new ArrayList<>();
		

		for (Uf uf : ufOrdenadas) {
			String fecha=formatoFeha.format(uf.getFecha());
			String registro = "";
			registro += filaDos + ";";
			registro += fecha + ";";
			registro += uf.getValor();
			listaDatos.add(registro.split(";"));

		}
		return listaDatos;
	}
	
	/**
	 * Metodo que genera el archivo de salida .csv
	 * 
	 * @param ufOrdenadas
	 * @param fechaInicio
	 * @param fechaFin
	 * @return
	 */
	public File generarArchivo(List<Uf> ufOrdenadas,Date fechaInicio, Date fechaFin) {
		
		 log.info("Metodo: generarArchivo ");
    	  File file = new File(nombreArchivo);

 
    		log.debug("ruta del Archivo: " + file.getAbsolutePath());

    	    // GENERA CABECERA
    	    String[] cabecera = generarCabecera(fechaInicio,fechaFin);
    	    List<String[]> ufs=parseListToString(ufOrdenadas);
    	    try (CSVWriter writer = new CSVWriter(new OutputStreamWriter(new FileOutputStream(nombreArchivo)), ';')){
    	    	 writer.writeNext(cabecera);
    	    	    for (String[] fila : ufs) {
    	    	    	writer.writeNext(fila);
    	    	    }
    	    	   
    	    } catch (Exception e) {
    	    	log.error(e.getMessage(), e);
    	    	return null;

    	    }
    	    log.info("Fin de generarArchivo");
    	return file;
        }

	public String getNombreArchivo() {
		return nombreArchivo;
	}

	public void setNombreArchivo(String nombreArchivo) {
		this.nombreArchivo = nombreArchivo;
	}


	public SimpleDateFormat getFormatoFeha() {
		return formatoFeha;
	}


	public void setFormatoFeha(SimpleDateFormat formatoFeha) {
		this.formatoFeha = formatoFeha;
	}


	public String getFilaUno() {
		return filaUno;
	}


	public void setFilaUno(String filaUno) {
		this.filaUno = filaUno;
	}


	public String getFilaDos() {
		return filaDos;
	}


	public void setFilaDos(String filaDos) {
		this.filaDos = filaDos;
	}

    
	

}
