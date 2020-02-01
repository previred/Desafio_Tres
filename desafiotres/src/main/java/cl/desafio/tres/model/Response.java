package cl.desafio.tres.model;

import java.util.Date;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Response {
	private Date inicio;
	private Date fin;
	@JsonProperty("UFs") 
	private Set<UF> ufs;
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
	public Set<UF> getUfs() {
		return ufs;
	}
	public void setUfs(Set<UF> ufs) {
		this.ufs = ufs;
	}
	
	
}
