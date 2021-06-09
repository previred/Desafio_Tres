package com.previred.prueba.request;


import java.util.List;

public class DatosEntrada {

	private String inicio;
	private String fin;
	private List<String> UFs;


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

	public List<String> getUFs() {
		return UFs;
	}

	public void setUFs(List<String> UFs) {
		this.UFs = UFs;
	}
}
