package com.previred.desafio.tres;

import java.io.File;
import java.util.List;

import com.previred.desafio.tres.impl.completar.CompletarUfs;
import com.previred.desafio.tres.impl.exportar.csv.CSVFile;
import com.previred.desafio.tres.inter.IGenerarArchivoUf;
import com.previred.desafio.tres.inter.completar.ICompletarUfs;
import com.previred.desafio.tres.inter.exportar.IExportarArchivo;
import com.previred.desafio.tres.uf.Valores;
import com.previred.desafio.tres.uf.vo.Uf;
import com.previred.desafio.tres.uf.vo.Ufs;

public class GenerarArchivoUf implements IGenerarArchivoUf {

	@Override
	public void generarArchivo() throws Exception {
		Ufs ufs = generarObjetoUfs();
		ICompletarUfs completarValoresUfs = new CompletarUfs();
		List<Uf> listaUfImprimir = completarValoresUfs.completarValoresUfs(ufs);
		IExportarArchivo exportarArchivo = new CSVFile();
		exportarArchivo.exportar(listaUfImprimir, ufs);
		System.out.printf("Archivo generado en -> %s%s%s", System.getProperty("user.dir"), File.separator,
				exportarArchivo.getFileName());
	}

	/**
	 * Genera el objeto Ufs
	 * 
	 * @return Objeto Ufs valido
	 */
	private Ufs generarObjetoUfs() {
		Valores valores = new Valores();
		Ufs ufs = valores.getRango();
		validarVariables(ufs);
		return ufs;
	}

	/**
	 * Valida que el objeto y las fechas sean correctas
	 * 
	 * @param ufs
	 */
	private void validarVariables(Ufs ufs) {
		if (ufs == null) {
			throw new IllegalArgumentException("ufs.is.null");
		}
		if (ufs.getInicio().after(ufs.getFin())) {
			throw new IllegalArgumentException("fecha.inicio.mayor.al.fin");
		}
	}

}
