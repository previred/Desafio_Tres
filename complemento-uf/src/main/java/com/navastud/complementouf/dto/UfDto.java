package com.navastud.complementouf.dto;

import java.time.LocalDate;

public class UfDto {

	private LocalDate fecha;
	private String valor;

	public UfDto() {
		super();
	}

	public UfDto(LocalDate fecha, String valor) {
		super();
		this.fecha = fecha;
		this.valor = valor;
	}

	public LocalDate getFecha() {
		return fecha;
	}

	public void setFecha(LocalDate fecha) {
		this.fecha = fecha;
	}

	public String getValor() {
		return valor;
	}

	public void setValor(String valor) {
		this.valor = valor;
	}

}
