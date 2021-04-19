package com.previred.desafio.tres.complementouf;

import java.io.IOException;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import com.previred.desafio.tres.complementouf.vo.ComplementoUF;
import com.previred.desafio.tres.complementouf.vo.UF;
import com.previred.desafio.tres.uf.DatosUf;
import com.previred.desafio.tres.uf.Valores;
import com.previred.desafio.tres.uf.vo.Uf;

public class ComplementoValoresUf {


	
	
	public ComplementoUF determinarComplemento() throws IOException {
		
		Valores v = new Valores();
		
		ComplementoUF complemento = new ComplementoUF();
		complemento.setInicio(v.getRango().getInicio());
		complemento.setFin(v.getRango().getFin());
		
		Set<UF> listUFFaltantes = new HashSet<UF>();
		
		Map<Date, Uf> ufsMap = v.getRango().getUfs().stream().collect(Collectors.toMap(x->x.getFecha(), x->x));
		
		Calendar cal = Calendar.getInstance();
		
		Date piv = v.getRango().getInicio();
		DatosUf datosUf = DatosUf.getInstance();
		while(piv.compareTo(v.getRango().getFin()) <= 0) {
			Uf uf = datosUf.getUf(piv);
			
			if (!ufsMap.containsKey(uf.getFecha())) {
				UF ufFaltante = new UF();
				ufFaltante.setFecha(piv);
				ufFaltante.setDato(uf.getValor());
				listUFFaltantes.add(ufFaltante);
			}
			
			
			cal.setTime(piv);
			cal.add(Calendar.MONTH, 1);
			piv = cal.getTime();
		}
		
		complemento.setUFs(listUFFaltantes.stream().sorted((x,y)->y.getFecha().compareTo(x.getFecha())).collect(Collectors.toList()));
		
		return complemento;
		
		
		
		
	}

}
