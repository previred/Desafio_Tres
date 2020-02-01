package cl.desafio.tres.model;


import java.util.Date;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import cl.desafio.tres.util.CustomDoubleDeserializer;

public class UF {
	private Date fecha;
	@JsonSerialize(converter = CustomDoubleDeserializer.class)
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
	public UF() {}
	public UF(Date fecha, Double dato) {
		super();
		this.fecha = fecha;
		this.dato = dato;
	}

}
