package cl.zco.desafio3.util;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import javax.swing.JOptionPane;

import org.apache.commons.beanutils.BeanComparator;
import org.apache.commons.collections.ComparatorUtils;
import org.apache.commons.collections.comparators.NullComparator;
import org.apache.commons.collections.comparators.ReverseComparator;

import com.previred.desafio.tres.uf.vo.Uf;
import com.previred.desafio.tres.uf.vo.Ufs;

import cl.zco.desafio3.dto.FormatoJsonDTO;
import cl.zco.desafio3.dto.FormatoUfJsonDTO;

// TODO: Auto-generated Javadoc
/**
 * Clase que contiene las utilidades del aplicativo.
 */
public class Util {
	
	/**
	 * Metodo que obtiene la fecha y le da el formato recibido.
	 *
	 * @param fecha parametro con la fecha
	 * @param formato the formato
	 * @return String con la fecha de salida
	 */
	public static String obtenerFechaFormato(Date fecha,String formato) {
		 SimpleDateFormat formatoFecha =  new SimpleDateFormat(formato);
		 return formatoFecha.format(fecha);
	}
	
	/**
	 * Metodo que ordena lo que reciba y en base al nombre del atributo. 
	 * @param <T> cualquier elemento
	 * @param list lista del elemento T
	 * @param esAscendente boolean que indica si la lista sera asc o desc
	 * @param bean este es el atributo de la clase por el cual se va a ordenar
	 */
	@SuppressWarnings("unchecked")
	public static <T> void clasificarColeccion(List<T> list, boolean esAscendente, String... bean) {
		Comparator<T> nullComparator = new NullComparator(esAscendente);
		Collection<Comparator<T>> beanComparatorCollection = new ArrayList<>( bean.length);
		for (int i = 0; i < bean.length; i++) {
			beanComparatorCollection.add(new BeanComparator(bean[i], nullComparator));
		}
		Comparator<T> finalComparator = ComparatorUtils.chainedComparator(beanComparatorCollection);
		if (!esAscendente) {
			finalComparator = new ReverseComparator(finalComparator);
		}
		Collections.sort(list, finalComparator);
	}
	
	/**
	 * Convertir a json DTO. 
	 * Metodo que convierte el ufs a formato json dto
	 *
	 * @param ufs the ufs
	 * @param listUfRango the list uf rango
	 * @param maxJson the max json
	 * @return the formato json DTO
	 */
	public static FormatoJsonDTO convertiraJsonDTO(Ufs ufs,List<Uf> listUfRango, int maxJson) {
		FormatoJsonDTO dto  = new FormatoJsonDTO();
		dto.setInicio(obtenerFechaFormato(ufs.getInicio(), Constantes.FORMATO_YYYY_MM_DD));
		dto.setFin(obtenerFechaFormato(ufs.getFin(), Constantes.FORMATO_YYYY_MM_DD));
		if(listUfRango == null){
			dto.setUFs(new ArrayList<>());
		}else {
			dto.setUFs(convertiraJsonUfDTO(listUfRango, maxJson));
		}
		return dto; 
	}
	
	/**
	 * Convertira json uf DTO.
	  *Metodo que convierte al DTO jsonUF en base al UF recibido.
	 * @param listUf the list uf
	 * @param maxJson the max json
	 * @return the list
	 */
	public static List<FormatoUfJsonDTO> convertiraJsonUfDTO( List<Uf> listUf,int maxJson) {
		List<FormatoUfJsonDTO> listUfDTO = new ArrayList<>();
		if(maxJson != 0 && listUf.size()>maxJson) {
			listUf = listUf.subList(0, maxJson);
		}
		listUf.forEach(uf -> 
			listUfDTO.add(new FormatoUfJsonDTO(obtenerFechaFormato(uf.getFecha(), Constantes.FORMATO_YYYY_MM_DD),
					uf.getValor().toString()))
		);
		return listUfDTO;
	}
	
	/**
	 * metodo que valida que los elementos retornados de getRange() esten en la lista.
	 *
	 * @param listUf the list uf
	 * @param listUfRango the list uf rango
	 * @return verdadero si todos los elementos de la lista a se encuetran en la lista b.
	 */
	public static boolean validacionElementosEnRango(List<Uf> listUf, List<Uf> listUfRango) {
		int cantidad = listUf.size();
		Long encontrado = listUfRango.stream().filter(uf -> listUf.contains(uf)).count();
		return cantidad == encontrado.intValue();
	}
	
	/**
	 * Metodo sobrecargado para controlar la excepcion.
	 *
	 * @param msg mensaje a mostrar por pantalla
	 * @throws Throwable the throwable
	 */
	public static void controlExcepcion(String msg) throws Throwable {
		controlExcepcion(msg, JOptionPane.INFORMATION_MESSAGE);
	}
	
	/**
	 * Control excepcion.
	 * Metodo creado para controlar la excepcion en caso de inconveniente
	 * @param msg the msg
	 * @param optionMsg the option msg
	 * @throws Throwable the throwable
	 */
	public static void controlExcepcion(String msg , Integer optionMsg) throws Throwable {
		try {
			JOptionPane.showMessageDialog(null, msg, 
					optionMsg.equals(JOptionPane.INFORMATION_MESSAGE) ? "Información" : "Error", optionMsg );
		} catch (Exception e) {
			throw new Throwable();
		}
	}

}
