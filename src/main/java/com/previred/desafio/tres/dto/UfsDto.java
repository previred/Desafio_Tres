package com.previred.desafio.tres.dto;

import java.io.Serializable;
import java.util.List;

public class UfsDto implements Serializable  {

	private static final long serialVersionUID = 668602131879757330L;
	
	private String inicio;
	private String fin;
	private List<UfDto> UFs;
	
	public String getInicio() {
		return inicio;
	}
	public void setInicio(String inicio) {
		this.inicio = inicio;
	}
	public String getFin() {
		return fin;
	}
	public void setFin(String fin) {
		this.fin = fin;
	}
	
	
	public List<UfDto> getUFs() {
		return UFs;
	}
	public void setUFs(List<UfDto> uFs) {
		UFs = uFs;
	}
	@Override
	public String toString() {
		return "UfsDto [inicio=" + inicio + ", fin=" + fin + ", UFs=" + UFs + "]";
	}



}
