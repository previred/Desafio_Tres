package com.previred.desafio.tres.utils;

import java.util.HashMap;
import java.util.List;

public class ListadoUf {

	private String inicio;
	private String fin;
	private List<HashMap<String, String>> ufs;

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

	public List<HashMap<String, String>> getUfs() {
		return ufs;
	}

	public void setUfs(List<HashMap<String, String>> ufs) {
		this.ufs = ufs;
	}

}
