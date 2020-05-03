package dto;

import java.io.Serializable;

public class UfDTO implements Serializable{

	private static final long serialVersionUID = 1L;

	private String fecha;
	private Double valor;
	
	public Double getValor() {
		return valor;
	}
	public void setValor(Double valor) {
		this.valor = valor;
	}
	public String getFecha() {
		return fecha;
	}
	public void setFecha(String fecha) {
		this.fecha = fecha;
	}

}
