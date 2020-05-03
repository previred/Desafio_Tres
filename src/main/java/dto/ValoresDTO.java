package dto;

import java.io.Serializable;
import java.util.List;

public class ValoresDTO implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private String inicio;
	private String fin;
	private List<UfDTO> ufs;
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
	public List<UfDTO> getUfs() {
		return ufs;
	}
	public void setUfs(List<UfDTO> ufs) {
		this.ufs = ufs;
	}
		
}
