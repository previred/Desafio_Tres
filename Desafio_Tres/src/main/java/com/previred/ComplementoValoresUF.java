package com.previred;

import com.previred.desafio.tres.uf.DatosUf;
import com.previred.desafio.tres.uf.Valores;
import com.previred.desafio.tres.uf.vo.Ufs;

public class ComplementoValoresUF {
	
	public void getRango() {
		System.out.println("ComplementoValoresUF.getRango");
		
		Ufs ufs;
		DatosUf datosUf = DatosUf.getInstance();
		ReporteCsv rptCsv;
		
		Valores valores = new Valores();
		
		ufs = valores.getRango();
		
		System.out.println("Fecha Ini: "+ufs.getInicio());
		System.out.println("Fecha Fin: "+ufs.getFin());
		System.out.println("Cantidad de UFs: "+ufs.getUfs().size());
		
		rptCsv = new ReporteCsv(ufs, datosUf);
		rptCsv.valoresUfToCsv();
		
	}
	

}
