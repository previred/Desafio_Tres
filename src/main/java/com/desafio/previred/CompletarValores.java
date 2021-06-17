package com.desafio.previred;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.apache.log4j.Logger;
import com.previred.desafio.tres.uf.DatosUf;
import com.previred.desafio.tres.uf.tools.RandomDate;
import com.previred.desafio.tres.uf.vo.Uf;

/**
 * Encargada de generar los Uf faltante para completar los vacios por fecha
 * 
 * @author ablanco
 *
 */
public class CompletarValores {
    private static final Logger log = Logger.getLogger( CompletarValores.class.getName() );
	
	/**
	 *  Metodo para ordenar los Uf de forma descendente por fecha
	 * @param ufs
	 * @return lista ordenada
	 */
    public  List<Uf> ordenarUf(Set<Uf> ufs){
		 log.info("Metodo: ordenarUf ");
		 Stream <Uf> stream =ufs.stream();
		 return stream.sorted(Comparator.comparing(Uf::getFecha).reversed()).collect(Collectors.toList());
		}
    
    /**
     * Metodo que completa la lista de Uf cuando su tamaño es menor de 100
     * 
     * @param size
     * @param fechaInicio
     * @param fechaFin
     * @param setUf
     * @return setUf cuyo size es 100
     */
	public void getValoresfaltantes(int size, Date fechaInicio, Date fechaFin, Set<Uf> setUf) {
		
		log.info("Metodo: getValoresfaltantes ");
		LocalDate fechaInicioLocal = fechaInicio.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
	    LocalDate fechaFinLocal = fechaFin.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
	    
	    log.info("Metodo: getValoresfaltantes - generando fecha aleatoria ");
	    log.debug("Rango de Fecha -  Fecha inicio: " + fechaInicioLocal + " Fecha Fin: "+ fechaFinLocal);	
	    RandomDate randomDate= new RandomDate(fechaInicioLocal, fechaFinLocal);
	   
	    for (int i =0; i<size; i++){
	    	
	    	DatosUf datos= DatosUf.getInstance(); 
	    	LocalDate fecha=randomDate.nextDate();
	    	Date siguienteFecha = Date.from(fecha.atStartOfDay(ZoneId.systemDefault()).toInstant());
	    	Uf ufFaltante= datos.getUf(siguienteFecha);
	    	boolean existe =setUf.add(ufFaltante);
	    	while(!existe){
	    		fecha=randomDate.nextDate();
	    		siguienteFecha = Date.from(fecha.atStartOfDay(ZoneId.systemDefault()).toInstant());
	    		ufFaltante= datos.getUf(siguienteFecha);
	    		existe =setUf.add(ufFaltante);
	    		 
	    	}	

	    	 
	    }
	    log.debug("tamaño lista completada: " +setUf.size());
	    log.info("Metodo: getValoresfaltantes - Ufs completados ");
	}

}
