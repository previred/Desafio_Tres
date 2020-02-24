package models;

import java.util.Date;


public class UfDiariaModel {

	private Date fechaUf;
	private String fechaUfFmt;
	private double valorUf;

	public String getFechaUfFmt() {
		return fechaUfFmt;
	}

	public void setFechaUfFmt(String fechaUfFmt) {
		this.fechaUfFmt = fechaUfFmt;
	}

	public Date getFechaUf() {
		return fechaUf;
	}

	public void setFechaUf(Date fechaUf) {
		this.fechaUf = fechaUf;
	}

	public double getValorUf() {
		return valorUf;
	}

	public void setValorUf(double valorUf) {
		this.valorUf = valorUf;
	}
	
	public String toString() {
		
		return 		"fechaUf: " + this.fechaUfFmt + "\n"
				+   "valorUf: " + this.valorUf;	
	}
	
}
