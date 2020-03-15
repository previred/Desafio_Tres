package cl.previred.desafio.model;

import java.io.Serializable;

public class UfObject implements Serializable {

	private static final long serialVersionUID = 1L;

	private String dato;
	private String fecha;

	public String getFecha() {
		return fecha;
	}

	public void setFecha(String fecha) {
		this.fecha = fecha;
	}

	public String getDato() {
		return dato;
	}

	public void setDato(String dato) {
		this.dato = dato;
	}

	@Override
	public String toString() {
		return "UfObject [dato=" + dato + ", fecha=" + fecha + "]";
	}

}
