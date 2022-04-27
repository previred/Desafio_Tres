package cl.zco.desafio3.negocio;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.core.NestedExceptionUtils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import cl.zco.desafio3.dto.FormatoJsonDTO;
import cl.zco.desafio3.util.Constantes;

/**
 * Clase que manejará los archivos.
 * @author Patricio Angel
 *
 */
public class ManejadorArchivos {
	
	/** The log. */
	private static Logger log = LogManager.getLogger(ManejadorArchivos.class);

	/**
	 * Instantiates a new manejador archivos.
	 */
	public ManejadorArchivos() {super();}
	
	/**
	 * Crear fichero json.
	 * @param jsonDTO the json DTO
	 * @param destino the destino
	 * @return true, if successful
	 */
	public static boolean crearFicheroJson(FormatoJsonDTO jsonDTO, String destino) {
		return crearFicheroJson(jsonDTO, destino, false);
	}
	
	/**
	 * Crear fichero json.
	 * Metodo que crea el fichero valores.json.
	 * @param jsonDTO the json DTO
	 * @param destino the destino
	 * @param ignorarValidacion the ignorar validacion
	 * @return true, if successful
	 */
	public static boolean crearFicheroJson(FormatoJsonDTO jsonDTO, String destino, boolean ignorarValidacion) {
		try {
			if (!ignorarValidacion && !existeCarpeta(destino)) {
				destino = Constantes.RUTA_JAR_JSON;
			}
		    PrintWriter out = new PrintWriter(destino);
		    Gson gson = new GsonBuilder().setPrettyPrinting().create();
		    String json = gson.toJson(jsonDTO);
		    json = json.replace(Constantes.SIMBOLO_NUEVA_LINEA, System.lineSeparator());
		    out.println(json);
		    out.close();
		    return true;
		} catch (FileNotFoundException e) {
			log.error(NestedExceptionUtils.getRootCause(e));
			return false;
		}
	}
	
	/**
	 * Existe carpeta.
	 * Metodo que valida que exista carpeta segun ruta
	 * @param ruta the ruta
	 * @return true, if successful
	 */
	public static boolean existeCarpeta(String ruta) {
		File file = new File(ruta);
		return file.exists();
	}
}
