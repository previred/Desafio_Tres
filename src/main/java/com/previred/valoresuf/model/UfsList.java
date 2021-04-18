/**
 * 
 */
package com.previred.valoresuf.model;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;

/**
 * @author crist
 *
 */
public class UfsList {


	@JsonFormat(shape = JsonFormat.Shape.STRING)
	@JsonDeserialize(using = LocalDateDeserializer.class)
	@JsonSerialize(using = LocalDateSerializer.class)
	private LocalDate fecha;
	private Double dato;
	
	/**
	 * 
	 */
	public UfsList() {
		super();
	}

	/**
	 * @param fecha
	 * @param dato
	 */
	public UfsList(LocalDate fecha, Double dato) {
		super();
		this.fecha = fecha;
		this.dato = dato;
	}

	/**
	 * @return the fecha
	 */
	public LocalDate getFecha() {
		return fecha;
	}

	/**
	 * @param localDate the fecha to set
	 */
	public void setFecha(LocalDate localDate) {
		this.fecha = localDate;
	}

	/**
	 * @return the dato
	 */
	public Double getDato() {
		return dato;
	}

	/**
	 * @param dato the dato to set
	 */
	public void setDato(Double dato) {
		this.dato = dato;
	}
}
