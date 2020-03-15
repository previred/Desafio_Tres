package cl.previred.desafio.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class UfsList implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private String inicio;
	private String fin;
	private List<UfObject> ufs = new ArrayList<UfObject>();;
	
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
	public List<UfObject> getUfs() {
		return ufs;
	}
	public void setUfs(List<UfObject> ufs) {
		this.ufs = ufs;
	}
	
	@Override
	public String toString() {
		return "Ufs [inicio=" + inicio + ", fin=" + fin + ", ufs=" + ufs + "]";
	}
	
}
