package com.previred.desafio.uf;

import java.util.Date;
import java.util.List;

import com.previred.desafio.tres.uf.vo.Uf;

public class UFValueResult {

	private Date inicio;
	
	private Date fin;
	
	private List<Uf> sortedUfs;
	
	public UFValueResult(Date inicio, Date fin, List<Uf> sortedUfs) {
		this.inicio = inicio;
		this.fin = fin;
		this.sortedUfs = sortedUfs;
	}

	public Date getInicio() {
		return inicio;
	}

	public Date getFin() {
		return fin;
	}

	public List<Uf> getSortedUfs() {
		return sortedUfs;
	}
}
