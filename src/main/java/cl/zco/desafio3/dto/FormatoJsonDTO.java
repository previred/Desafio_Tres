package cl.zco.desafio3.dto;

import java.util.List;

/**
 * Clase FormatoJsonDTO.
 * @author Patricio Angel
 */
public class FormatoJsonDTO {
	
	/** The inicio. */
	private String inicio;
	
	/** The fin. */
	private String fin;
	
	/** The U fs. */
	private List<FormatoUfJsonDTO> UFs;

	/**
	 * Instantiates a new formato json DTO.
	 */
	public FormatoJsonDTO() {
		super();
	}

	/**
	 * Gets the inicio.
	 *
	 * @return the inicio
	 */
	public String getInicio() {
		return inicio;
	}

	/**
	 * Sets the inicio.
	 *
	 * @param inicio the new inicio
	 */
	public void setInicio(String inicio) {
		this.inicio = inicio;
	}

	/**
	 * Gets the fin.
	 *
	 * @return the fin
	 */
	public String getFin() {
		return fin;
	}

	/**
	 * Sets the fin.
	 *
	 * @param fin the new fin
	 */
	public void setFin(String fin) {
		this.fin = fin;
	}

	/**
	 * Gets the u fs.
	 *
	 * @return the u fs
	 */
	public List<FormatoUfJsonDTO> getUFs() {
		return UFs;
	}

	/**
	 * Sets the u fs.
	 *
	 * @param uFs the new u fs
	 */
	public void setUFs(List<FormatoUfJsonDTO> uFs) {
		UFs = uFs;
	}
	
}
