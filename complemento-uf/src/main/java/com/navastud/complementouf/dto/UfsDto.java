package com.navastud.complementouf.dto;

import java.time.LocalDate;
import java.util.List;

public class UfsDto {

	private LocalDate inicio;

	private LocalDate fin;

	private List<UfDto> UFs;

	public UfsDto() {
		super();
	}

	public UfsDto(LocalDate inicio, LocalDate fin, List<UfDto> Ufs) {
		super();
		this.inicio = inicio;
		this.fin = fin;
		this.UFs = Ufs;
	}

	public LocalDate getInicio() {
		return inicio;
	}

	public void setInicio(LocalDate inicio) {
		this.inicio = inicio;
	}

	public LocalDate getFin() {
		return fin;
	}

	public void setFin(LocalDate fin) {
		this.fin = fin;
	}

	public List<UfDto> getUFs() {
		return this.UFs;
	}

	public void setUFs(List<UfDto> uFs) {
		this.UFs = uFs;
	}

}
