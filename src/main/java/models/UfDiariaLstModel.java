package models;

import java.util.Date;
import java.util.List;


public class UfDiariaLstModel {
	
	private Date fechaInicio;
	private Date fechaFin;
	private String fechaInicioFmt;
	private String fechaFinFmt;
	private List<UfDiariaModel> lstUfDiaria;

	
	
	public String getFechaInicioFmt() {
		return fechaInicioFmt;
	}

	public void setFechaInicioFmt(String fechaInicioFmt) {
		this.fechaInicioFmt = fechaInicioFmt;
	}

	public String getFechaFinFmt() {
		return fechaFinFmt;
	}

	public void setFechaFinFmt(String fechaFinFmt) {
		this.fechaFinFmt = fechaFinFmt;
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

	public List<UfDiariaModel> getLstUfDiaria() {
		return lstUfDiaria;
	}

	public void setLstUfDiaria(List<UfDiariaModel> lstUfDiaria) {
		this.lstUfDiaria = lstUfDiaria;
	}
	

	
	
	
	
	
}
