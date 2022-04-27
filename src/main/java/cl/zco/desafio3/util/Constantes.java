package cl.zco.desafio3.util;

import java.util.HashMap;

// TODO: Auto-generated Javadoc
/**
 * Clase que contiene las constantes.
 * @author Patricio Angel
 */
public class Constantes {
	
	/** The Constant FORMATO_DD_MMM_YYYY. */
	public static final String FORMATO_DD_MMM_YYYY = "dd/MM/yyyy";
	
	/** The Constant FORMATO_YYYY_MM_DD. */
	public static final String FORMATO_YYYY_MM_DD = "yyyy-MM-dd";
	
	/** The Constant RUTA_WKS_JSON. */
	public static final String RUTA_WKS_JSON = "files/valores.json";
	
	/** The Constant RUTA_JAR_JSON. */
	public static final String RUTA_JAR_JSON =  "C:\\files\\valores.json";
	
	/** The Constant SIMBOLO_NUEVA_LINEA. */
	public static final String SIMBOLO_NUEVA_LINEA = "\\n";
	
	/** The Constant ATRIB_FECHA. */
	public static final String ATRIB_FECHA = "fecha";
	
	/** The Constant VACIO. */
	public static final String VACIO = "";
	
	/** The etiquetas. */
	private static HashMap<String,String> etiquetas = new HashMap<>();
	
	/**
	 * Bloque de codigo para inicializar las etiquetas.
	 */
	static {
		etiquetas.put("mensaj.portada.desa","Bienvenido al desafio número 3, el archivo json generado quedará en la ruta /files/valores.json");
		etiquetas.put("mensaj.portada.jar","Bienvenido al desafio número 3, no olvidar tener creada la carpeta en C:\\files\\ para poder ver el json resultante");
		etiquetas.put("mensaj.final.ok.desa","Se ha creado correctamente el archivo en /files/valores.json");
		etiquetas.put("mensaj.final.ok.jar","Se ha creado correctamente el archivo en C:\\files\\valores.json");
		etiquetas.put("mensaj.final.error","El proceso de creación a fallado, intente nuevamente");
		etiquetas.put("mensaj.final.ok.ini","Se ha creado correctamente el archivo en ");
	}
	
	/**
	 * Instantiates a new constantes.
	 */
	public Constantes() {super();}
	
	/**
	 * Metodo que obtiene la etiqueta mapeaa
	 * (alternativa rapida concentrar todas las etiquetas, la idea es luego pasar estos codigo
	 * de etiquetas a i18).
	 *
	 * @param codigo the codigo
	 * @return the string
	 */
	public static String obtenerEtiqueta(String codigo) {
		return etiquetas.get(codigo) == null ? VACIO : etiquetas.get(codigo);
	} 
}
