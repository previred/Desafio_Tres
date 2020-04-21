package com.previred.desafio.tres.dto;

import java.util.Date;
import java.util.List;

import com.google.gson.annotations.SerializedName;

/**
 * Objeto que representa la salida de Ufs solicitada
 * @author Luis San Martin
 *
 */
public class UfsDto {
	private Date inicio;
	private Date fin;

	@SerializedName("Ufs")
	private List<UfDto> ufs;

	public Date getInicio() {
		return inicio;
	}

	public void setInicio(Date inicio) {
		this.inicio = inicio;
	}

	public Date getFin() {
		return fin;
	}

	public void setFin(Date fin) {
		this.fin = fin;
	}

	public List<UfDto> getUfs() {
		return ufs;
	}

	public void setUfs(List<UfDto> ufs) {
		this.ufs = ufs;
	}

}
