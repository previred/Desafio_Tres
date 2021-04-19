package com.previred.desafio.tres.complementouf.vo;

import java.io.Serializable;
import java.util.Date;

public class UF implements Serializable{
	private static final long serialVersionUID = 1L;
	private Date fecha;
	private Double dato;
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
