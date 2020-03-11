package desafio3.entity;

import java.util.Date;
import java.util.List;

public class Rango {
	
	private Date inicio;
	
	private Date fin;
	
	private List<UnidadFomento> ufs;
	

	/**
	 * @return the inicio
	 */
	public Date getInicio() {
		return inicio;
	}

	/**
	 * @param inicio the inicio to set
	 */
	public void setInicio(Date inicio) {
		this.inicio = inicio;
	}

	/**
	 * @return the fin
	 */
	public Date getFin() {
		return fin;
	}

	/**
	 * @param fin the fin to set
	 */
	public void setFin(Date fin) {
		this.fin = fin;
	}

	/**
	 * @return the ufs
	 */
	public List<UnidadFomento> getUfs() {
		return ufs;
	}

	/**
	 * @param ufs the ufs to set
	 */
	public void setUfs(List<UnidadFomento> ufs) {
		this.ufs = ufs;
	}

	

	
	
}