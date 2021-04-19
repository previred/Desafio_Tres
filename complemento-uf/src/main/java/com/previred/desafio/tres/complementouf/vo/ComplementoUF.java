package com.previred.desafio.tres.complementouf.vo;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Set;

public class ComplementoUF implements Serializable{

	private static final long serialVersionUID = 1L;
	private Date inicio;
	private Date fin;
	private List<UF> UFs;
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
	public List<UF> getUFs() {
		return UFs;
	}
	public void setUFs(List<UF> uFs) {
		UFs = uFs;
	}
	
}
