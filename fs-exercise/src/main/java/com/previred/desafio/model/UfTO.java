package com.previred.desafio.model;

public class UfTO {

	private String fecha;
	private Double dato;

	/**
	 * @return the fecha
	 */
	public String getFecha() {
		return fecha;
	}

	/**
	 * @param fecha
	 *            the fecha to set
	 */
	public void setFecha(String fecha) {
		this.fecha = fecha;
	}

	/**
	 * @return the dato
	 */
	public Double getDato() {
		return dato;
	}

	/**
	 * @param dato
	 *            the dato to set
	 */
	public void setDato(Double dato) {
		this.dato = dato;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "UfTO [fecha=" + fecha + ", dato=" + dato + "]";
	}

}
