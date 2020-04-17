package desafio.previred.mi.solucion.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.previred.desafio.tres.uf.vo.Uf;

@JsonPropertyOrder({ "inicio", "fin", "UFs" })
public class ViewUfs implements Serializable {

	
	@JsonProperty("inicio")
	private Date Inicio;
	
	@JsonProperty("fin")
	private Date Fin;
	
	@JsonProperty("UFs")
	private List<Uf> ufs;
	
	private static final long serialVersionUID = 1L;

	public Date getInicio() {
		return Inicio;
	}

	public void setInicio(Date inicio) {
		Inicio = inicio;
	}

	public Date getFin() {
		return Fin;
	}

	public void setFin(Date fin) {
		Fin = fin;
	}

	public List<Uf> getUfs() {
		return ufs;
	}

	public void setUfs(List<Uf> ufs) {
		this.ufs = ufs;
	}
}
