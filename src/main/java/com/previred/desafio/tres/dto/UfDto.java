package com.previred.desafio.tres.dto;

import java.io.Serializable;

public class UfDto implements Serializable {
	
	private static final long serialVersionUID = -4256333266736125670L;
	
	private String fecha;
	private String dato;

	
	public String getDato() {
		return dato;
	}
	public void setValor(String dato) {
		this.dato = dato;
	}
	public String getFecha() {
		return fecha;
	}
	public void setFecha(String fecha) {
		this.fecha = fecha;
	}
	@Override
	public String toString() {
		return "UfDto [dato=" + dato + ", fecha=" + fecha + "]";
	}
	
	
	  
}
