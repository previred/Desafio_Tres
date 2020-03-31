package desafio.com.desafio3.to;

import java.util.List;

import com.previred.desafio.tres.uf.vo.Uf;

public class UfsTo {

	private String Inicio;
	private String Fin;
	private List<UfTo> Ufs;
	
	public String getInicio() {
		return Inicio;
	}
	public void setInicio(String inicio) {
		Inicio = inicio;
	}
	public String getFin() {
		return Fin;
	}
	public void setFin(String fin) {
		Fin = fin;
	}
	public List<UfTo> getUfs() {
		return Ufs;
	}
	public void setUfs(List<UfTo> ufs) {
		Ufs = ufs;
	} 
	
}
