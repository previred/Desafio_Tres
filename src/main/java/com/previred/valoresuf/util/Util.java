/**
 * 
 */
package com.previred.valoresuf.util;

import java.io.PrintWriter;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.previred.valoresuf.model.UfsRespuesta;

/**
 * @author crist
 *
 */
public class Util {

	/**
	 * cambiar formato a la fecha
	 * @param date
	 * @return
	 */
	public static LocalDate formatDate(Date date) {
		return date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
	}

	/**
	 * transformar el objeto en json
	 * @param data
	 * @return
	 */
	public static String extraerObjetoJSON(UfsRespuesta data) {
		
		ObjectMapper mapper = new ObjectMapper();
		String json = "";
		try {
			json = mapper.writeValueAsString(data);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
			System.out.println("Error al parsear objeto a json");
		}
		return json;	
	}

	/**
	 * generar archivo .json
	 * @param data
	 * @return
	 */
	public static boolean generarArchivoJson(String data) {
		try {
			PrintWriter writer = new PrintWriter("src/main/resources/valores.json", "UTF-8");
			writer.println(data);
			writer.close();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
}
