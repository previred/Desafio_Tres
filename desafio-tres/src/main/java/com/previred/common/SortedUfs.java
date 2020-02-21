package com.previred.common;

import java.util.Date;
import java.util.List;

import com.google.gson.annotations.Expose;

public class SortedUfs {

	@Expose
	private Date inicio;
	
	@Expose
	private Date fin;
	
	@Expose
	private List<SortedUf> Ufs;

	public SortedUfs(Date inicio, Date fin, List<SortedUf> ufs) {
		
		super();
		
		this.inicio = inicio;
		this.fin = fin;
		Ufs = ufs;
		
	}

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

	public List<SortedUf> getUfs() {
		return Ufs;
	}

	public void setUfs(List<SortedUf> ufs) {
		Ufs = ufs;
	}
		
}
