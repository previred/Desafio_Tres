package cl.zco.desafio3;

import org.springframework.boot.context.properties.ConfigurationProperties;

// TODO: Auto-generated Javadoc
/**
 * Clase de configuracion, en esta tendremos las configuraciones de un archivo
 * propierties y que tengan el prefijo 'desafio3'.
 *
 * @author Patricio Angel
 */
@ConfigurationProperties(prefix = "desafio3")
public class Configuracion {

	/** The cantidadjson. */
	private int cantidadjson;

	/**
	 * Constructor de la clase Configuracion con parametros.
	 *
	 * @param cantidadjson the cantidadjson
	 */
	public Configuracion( int cantidadjson) {
		super();
		this.cantidadjson = cantidadjson;
	}

	/**
	 * Constructor de la clase Configuracion sin parametros.
	 */
	public Configuracion() {
	}
	
	/**
	 * Gets the cantidadjson.
	 *
	 * @return the cantidadjson
	 */
	public int getCantidadjson() {
		return cantidadjson;
	}

	/**
	 * Sets the cantidadjson.
	 *
	 * @param cantidadjson the new cantidadjson
	 */
	public void setCantidadjson(int cantidadjson) {
		this.cantidadjson = cantidadjson;
	}

	/**
	 * To string.
	 *
	 * @return the string
	 */
	@Override
	public String toString() {
		return "Configuracion [cantidadjson=" + cantidadjson + "]";
	}


}
