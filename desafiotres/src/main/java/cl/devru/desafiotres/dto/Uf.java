package cl.devru.desafiotres.dto;

import java.io.Serializable;
import java.util.Comparator;

public class Uf implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6902931368104145956L;

	private String fecha;
	private String dato;
	
	public Uf() {
		super();
	}

	/**
	 * @return the fecha
	 */
	public String getFecha() {
		return fecha;
	}

	/**
	 * @param fecha the fecha to set
	 */
	public void setFecha(String fecha) {
		this.fecha = fecha;
	}

	/**
	 * @return the dato
	 */
	public String getDato() {
		return dato;
	}

	/**
	 * @param dato the dato to set
	 */
	public void setDato(String dato) {
		this.dato = dato;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("{\"fecha\":\"");
		builder.append(fecha);
		builder.append("\", \"dato\":\"");
		builder.append(dato);
		builder.append("\"}");
		return builder.toString();
	}
	
    static class SortByFecha implements Comparator<Uf> {
        @Override
        public int compare(Uf a, Uf b) {
            return b.getFecha().compareTo(a.getFecha());
        }
    }	
	
}
