package com.previred.common;

import java.util.Date;

public class SortedUf {

	private Date fecha;
	
	private Double dato;

	public SortedUf(Date fecha, Double dato) {

		super();
		
		this.fecha = fecha;
		this.dato = dato;
		
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public Double getDato() {
		return dato;
	}

	public void setDato(Double dato) {
		this.dato = dato;
	}
	
}
