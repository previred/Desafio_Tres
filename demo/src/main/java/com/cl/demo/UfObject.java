package com.cl.demo;

public class UfObject {

	private String fecha;
	private String dato;
	

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
		return "UfObject [fecha=" + fecha + ", dato=" + dato + "]";
	}
	
	
}
