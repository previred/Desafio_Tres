package com.previred.desafio.tres.uf.model;

import java.util.Comparator;

public class UF {
	private String fecha;
	
	private String dato;

	public String getFecha() {
		return fecha;
	}

	public void setFecha(String fecha) {
		this.fecha = fecha;
	}

	public String getDato() {
		return dato;
	}

	public void setDato(String dato) {
		this.dato = dato;
	}
	
    public static Comparator<UF> Fecha = new Comparator<UF>() {

    	public int compare(UF uf1, UF uf2) {
		   String strUF1 = uf1.getFecha().replaceAll("-", "").toUpperCase();
		   String strUF2 = uf2.getFecha().replaceAll("-", "").toUpperCase();
		   return strUF2.compareTo(strUF1);
   }};

}
