package com.previred.desafio.tres.impl.completar.ordenar;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

import com.previred.desafio.tres.uf.vo.Uf;

/**
 * Clase singleton
 * 
 * @author jaime cisternas
 *
 */
public class OrdenarUfs {

	private static OrdenarUfs ordenarUfs;
	private CompararAscendente compararAscendente;

	/**
	 * Metodo visible
	 * 
	 * @return instancia unica
	 */
	public static OrdenarUfs getInstance() {
		if (ordenarUfs == null) {
			ordenarUfs = new OrdenarUfs();
		}
		return ordenarUfs;
	}

	/**
	 * Constructor
	 */
	private OrdenarUfs() {
		super();
		compararAscendente = new CompararAscendente();
	}

	/**
	 * Ordena el set de fechas de forma ascendente
	 * 
	 * @param setUf
	 *            Uf desordenada
	 * @return lista ordenada
	 */
	public List<Uf> ordenarUfPorFecha(Set<Uf> setUf) {
		List<Uf> listUf = null;
		if (setUf != null && !setUf.isEmpty()) {
			listUf = new ArrayList<>(setUf);
			Collections.sort(listUf, compararAscendente);
		}
		return listUf;
	}

	/**
	 * Ordena la lista ingresada como parametros
	 * 
	 * @param listUf
	 */
	public void ordenarUfPorFecha(List<Uf> listUf) {
		if (listUf != null && !listUf.isEmpty()) {
			Collections.sort(listUf, compararAscendente);
		}
	}

}
