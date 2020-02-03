package com.previred.desafio.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.previred.desafio.tres.uf.vo.Uf;

public class ListadoUf {

	Date fechaInicio;
	Date fechaFin;
	List<Uf> Uf;

	public ListadoUf(Date inicio, Date fin, ArrayList<Uf> arr) {
		this.fechaInicio = inicio;
		this.fechaFin = fin;
		this.Uf = arr;
	}

	public Date getFechaInicio() {
		return fechaInicio;
	}

	public void setFechaInicio(Date fechaInicio) {
		this.fechaInicio = fechaInicio;
	}

	public Date getFechaFin() {
		return fechaFin;
	}

	public void setFechaFin(Date fechaFin) {
		this.fechaFin = fechaFin;
	}

	public List<Uf> getUf() {
		return Uf;
	}

	public void setUf(List<Uf> uf) {
		Uf = uf;
	}

}
