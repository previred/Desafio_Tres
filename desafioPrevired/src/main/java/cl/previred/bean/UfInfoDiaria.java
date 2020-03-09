package cl.previred.bean;


import java.util.Date;

/**
*Clase que contiene estructura de la informacion de la UF
* @author  Carlos Barra
* @version 1.0
* @since   08-03-2020
*/
public class UfInfoDiaria {
	
	private String dato;
	private Date fecha;  
	
	public String getDato() {
		return dato;
	}
	public void setDato(String dato) {
		this.dato = dato;
	}
	public Date getFecha() {
		return fecha;
	}
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	
	
	
}
