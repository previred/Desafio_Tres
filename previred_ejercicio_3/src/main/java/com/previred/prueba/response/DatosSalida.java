package com.previred.prueba.response;

import com.previred.prueba.request.DatosEntrada;

import java.util.List;

public class DatosSalida {

	private String inicio;
	private String fin;
	private List<UfResponse> UFs;
	private List<UfResponse> UFsFaltante;


	public DatosSalida (){};


	public DatosSalida (DatosEntrada datosEntrada, List<UfResponse> ufs, List<UfResponse> ufsFaltante){
		this.inicio = datosEntrada.getInicio();
        this.fin = datosEntrada.getFin();
        this.setUFs(ufs);
		this.setUFsFaltante(ufsFaltante);
    };


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


	public List<UfResponse> getUFs() {
		return UFs;
	}

	public void setUFs(List<UfResponse> UFs) {
		this.UFs = UFs;
	}

	public List<UfResponse> getUFsFaltante() {
		return UFsFaltante;
	}

	public void setUFsFaltante(List<UfResponse> UFsFaltante) {
		this.UFsFaltante = UFsFaltante;
	}
}
