package cl.previred.bean;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
/**
*Clase que contiene estructura de la informacion de la lista de UFs y su rango 
* @author  Carlos Barra
* @version 1.0
* @since   08-03-2020
*/

public class UfListaRango {

	private Date inicio;
	private  Date fin;
	private  ArrayList<UfInfoDiaria> UFs;
	
	
	public Date getInicio() {
		return inicio;
	}
	public void setInicio(Date inicio) {
		this.inicio = inicio;
	}
	public Date getFin() {
		return fin;
	}
	public void setFin(Date fin) {
		this.fin = fin;
	}
	public List<UfInfoDiaria> getUFs() {
		return UFs;
	}
	public void setUFs(ArrayList<UfInfoDiaria> uFs) {
		UFs = uFs;
	}
	
	/*
	 * @Override public String toString() { return "UfListaRango [inicio=" + inicio
	 * + ", fin=" + fin + ", UFs=" + UFs + "]"; }
	 */
	
}



