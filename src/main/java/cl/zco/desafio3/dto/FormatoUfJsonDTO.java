package cl.zco.desafio3.dto;

/**
 * Clase FormatoUfJsonDTO.
 * @author Patricio Angel
 */
public class FormatoUfJsonDTO {
	
	/** The fecha. */
	private String fecha;
	
	/** The dato. */
	private String dato;
	
	/**
	 * Instantiates a new formato uf json DTO.
	 *
	 * @param fecha the fecha
	 * @param dato the dato
	 */
	public FormatoUfJsonDTO(String fecha, String dato) {
		this.fecha = fecha;
		this.dato = dato;
	}
	
	/**
	 * Gets the fecha.
	 *
	 * @return the fecha
	 */
	public String getFecha() {
		return fecha;
	}
	
	/**
	 * Sets the fecha.
	 *
	 * @param fecha the new fecha
	 */
	public void setFecha(String fecha) {
		this.fecha = fecha;
	}
	
	/**
	 * Gets the dato.
	 *
	 * @return the dato
	 */
	public String getDato() {
		return dato;
	}
	
	/**
	 * Sets the dato.
	 *
	 * @param dato the new dato
	 */
	public void setDato(String dato) {
		this.dato = dato;
	}
	
}
