package com.previred.desafio.tres.inter.exportar;

import java.util.List;

import com.previred.desafio.tres.uf.vo.Uf;
import com.previred.desafio.tres.uf.vo.Ufs;

public interface IExportarArchivo {

	/**
	 * Exporta los valores a un archivo de salida
	 * 
	 * @param ufList
	 *            lista de Uf
	 * @param ufs
	 *            Uf
	 * @throws Exception
	 */
	public void exportar(List<Uf> ufList, Ufs ufs) throws Exception;

	/**
	 * Retorna el nombre del archivo
	 * 
	 * @return
	 */
	public String getFileName();

}
