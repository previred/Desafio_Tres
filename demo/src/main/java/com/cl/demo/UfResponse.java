package com.cl.demo;

import java.util.List;

public class UfResponse {


	private String inicio ;
	private String fin;
	private List<UfObject> UFs;
	
	
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
	public List<UfObject> getUFs() {
		return UFs;
	}
	public void setUFs(List<UfObject> uFs) {
		UFs = uFs;
	}
	@Override
	public String toString() {
		return "UfResponse [inicio=" + inicio + ", fin=" + fin + ", UFs=" + UFs + "]";
	}
	
	
	
	
}
