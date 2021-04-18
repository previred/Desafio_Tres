/**
 * 
 */
package com.previred.valoresuf.model;

import java.time.LocalDate;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;

/**
 * @author crist
 *
 */
public class UfsRespuesta {

	@JsonFormat(shape = JsonFormat.Shape.STRING)
	@JsonDeserialize(using = LocalDateDeserializer.class)
	@JsonSerialize(using = LocalDateSerializer.class)
	private LocalDate inicio;
	@JsonFormat(shape = JsonFormat.Shape.STRING)
	@JsonDeserialize(using = LocalDateDeserializer.class)
	@JsonSerialize(using = LocalDateSerializer.class)
	private LocalDate fin;
	private List<UfsList> UFs;

	/**
	 * 
	 */
	public UfsRespuesta() {
		super();
	}

	/**
	 * @param inicio
	 * @param fin
	 * @param uFs
	 */
	public UfsRespuesta(LocalDate inicio, LocalDate fin, List<UfsList> uFs) {
		super();
		this.inicio = inicio;
		this.fin = fin;
		UFs = uFs;
	}

	/**
	 * @return the inicio
	 */
	public LocalDate getInicio() {
		return inicio;
	}

	/**
	 * @param inicio the inicio to set
	 */
	public void setInicio(LocalDate inicio) {
		this.inicio = inicio;
	}

	/**
	 * @return the fin
	 */
	public LocalDate getFin() {
		return fin;
	}

	/**
	 * @param fin the fin to set
	 */
	public void setFin(LocalDate fin) {
		this.fin = fin;
	}

	/**
	 * @return the uFs
	 */
	public List<UfsList> getUFs() {
		return UFs;
	}

	/**
	 * @param uFs the uFs to set
	 */
	public void setUFs(List<UfsList> uFs) {
		UFs = uFs;
	}
	
}
