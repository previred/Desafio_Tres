package com.github.maraya.ufs;

import java.util.List;

/**
 * 
 * @author marcelo.araya-gomez
 *
 */
public class UfSalida {
	
	private String inicio;
	private String fin;
	private List<Uf2> UFs;
	
	public class Uf2 {
		private String fecha;
		private String dato;
		
		public Uf2() {
		}
		
		public Uf2(String fecha, String dato) {
			this.fecha = fecha;
			this.dato = dato;
		}
		
		public String getFecha() {
			return fecha;
		}
		
		public void setFecha(String fecha) {
			this.fecha = fecha;
		}
		
		public String getDato() {
			return dato;
		}
		
		public void setDato(String dato) {
			this.dato = dato;
		}
	}

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

	public List<Uf2> getUFs() {
		return UFs;
	}

	public void setUFs(List<Uf2> uFs) {
		UFs = uFs;
	}
	
}
