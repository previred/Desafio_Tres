package com.previred.desafio.model;

import java.util.Set;

public class UfsTO {

	private String inicio;
	private String fin;
	private Set<UfTO> UFs;

	/**
	 * @return the inicio
	 */
	public String getInicio() {
		return inicio;
	}

	/**
	 * @param inicio
	 *            the inicio to set
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
	 * @param fin
	 *            the fin to set
	 */
	public void setFin(String fin) {
		this.fin = fin;
	}

	/**
	 * @return the uFs
	 */
	public Set<UfTO> getUFs() {
		return UFs;
	}

	/**
	 * @param uFs
	 *            the uFs to set
	 */
	public void setUFs(Set<UfTO> uFs) {
		UFs = uFs;
	}

}
