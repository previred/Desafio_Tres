package com.previred.ComplementoValoresUF.model;

import java.util.List;

import com.previred.desafio.tres.uf.vo.Uf;

public class ListaUf {

	private String inicio;
	private String fin;
	private List<Uf> UFs;
	
	public ListaUf() {}

	public ListaUf(String inicio, String fin, List<Uf> uFs) {
		super();
		this.inicio = inicio;
		this.fin = fin;
		UFs = uFs;
	}

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

	public List<Uf> getUFs() {
		return UFs;
	}

	public void setUFs(List<Uf> uFs) {
		UFs = uFs;
	}

}
