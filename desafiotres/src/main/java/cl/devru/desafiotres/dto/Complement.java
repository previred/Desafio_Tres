package cl.devru.desafiotres.dto;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Complement implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2986771728445638970L;

	private String inicio;
	private String fin;
	
	@JsonProperty("UFs")
	private List<Uf> UFs;
	/**
	 * @return the inicio
	 */
	public String getInicio() {
		return inicio;
	}
	/**
	 * @param inicio the inicio to set
	 */
	public void setInicio(String inicio) {
		this.inicio = inicio;
	}
	/**
	 * @return the fin
	 */
	public String getFin() {
		return fin;
	}
	/**
	 * @param fin the fin to set
	 */
	public void setFin(String fin) {
		this.fin = fin;
	}
	/**
	 * @return the uFs
	 */
	@JsonGetter("UFs")
	public List<Uf> getUFs() {
		return UFs;
	}
	/**
	 * @param uFs the uFs to set
	 */
	public void setUFs(List<Uf> uFs) {
		UFs = uFs;
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("{\"inicio\":\"");
		builder.append(inicio);
		builder.append("\", \"fin\":\"");
		builder.append(fin);
		builder.append("\", \"UFs\":");
		builder.append(UFs);
		builder.append("}");
		return builder.toString();
	}
	
	
}
