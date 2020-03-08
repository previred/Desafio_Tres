package desafio.tres.dto;

import java.util.List;

public class RespuestaDTO {
	
	String inicio;
	String fin;
	List<RespUF> UFs;
	
	
	
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
	public List<RespUF> getUFs() {
		return UFs;
	}
	public void setUFs(List<RespUF> uFs) {
		UFs = uFs;
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("RespuestaDTO [inicio=");
		builder.append(inicio);
		builder.append(", fin=");
		builder.append(fin);
		builder.append(", UFs=");
		builder.append(UFs);
		builder.append("]");
		return builder.toString();
	}
	
	

}


