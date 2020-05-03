package com.previred.desafio.model.dto;

import java.util.ArrayList;

public class ValoresJsonDTO {
	public ValoresJsonDTO() {
		// TODO Auto-generated constructor stub
	}
	
	private String inicio;
    private String fin;
    private ArrayList<UFsJsonDTO> UFs;
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
	public ArrayList<UFsJsonDTO> getUfs() {
		return UFs;
	}
	public void setUfs(ArrayList<UFsJsonDTO> UFs) {
		this.UFs = UFs;
	}
}
