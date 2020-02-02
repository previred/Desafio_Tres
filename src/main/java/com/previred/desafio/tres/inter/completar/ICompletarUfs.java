package com.previred.desafio.tres.inter.completar;

import java.util.List;

import com.previred.desafio.tres.uf.vo.Uf;
import com.previred.desafio.tres.uf.vo.Ufs;

public interface ICompletarUfs {

	/**
	 * Completa los valores faltantes de la lista de ufs
	 * 
	 * @param ufs
	 *            objeto con los las fechas y hasta 100 valores de uf
	 * @return lista completa de Uf ordenada
	 */
	public List<Uf> completarValoresUfs(Ufs ufs);

}
