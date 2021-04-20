package com.github.maraya.ufs;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;

import com.github.maraya.ufs.UfSalida.Uf2;
import com.google.gson.Gson;
import com.previred.desafio.tres.uf.DatosUf;
import com.previred.desafio.tres.uf.Valores;
import com.previred.desafio.tres.uf.vo.Uf;
import com.previred.desafio.tres.uf.vo.Ufs;

/**
 * 
 * @author marcelo.araya-gomez
 *
 */
public class UfsFaltantes {

	public static void main(String[] args) {
		Valores valores = new Valores();

		Ufs ufs = valores.getRango();
		
		List<Uf> listaUfs = new ArrayList<>();
		
		// ufs se transforman a lista
		listaUfs.addAll(ufs.getUfs());	
		
		// obtenemos el rango segun fecha de inicio y fin
		List<Uf> listaRangoUfs = DatosUf.getInstance().getUfs(ufs.getInicio(), ufs.getFin());
		
		// se unen las dos listas
		listaUfs.addAll(listaRangoUfs);
		
		// se eliminan las duplicadas
		List<Uf> listaUfsNoDuplicada = new ArrayList<>(new HashSet<>(listaUfs));
				
		// se ordena de forma descendente
		Collections.sort(listaUfsNoDuplicada, new Comparator<Uf>(){
			@Override
			public int compare(Uf uf1, Uf uf2) {
				return uf2.getFecha().compareTo(uf1.getFecha());
			}
		});
		
		// se llena el objeto de salida
		ZoneId zoneId = ZoneId.systemDefault();
		UfSalida ufSalida = new UfSalida();
		ufSalida.setInicio(ufs.getInicio().toInstant().atZone(zoneId).toLocalDate().toString());
		ufSalida.setFin(ufs.getFin().toInstant().atZone(zoneId).toLocalDate().toString());
		
		List<Uf2> ufs2 = new ArrayList<>();
		
		DecimalFormat df = new DecimalFormat("#,##0.00", new DecimalFormatSymbols(new Locale("es", "CL")));
		
		for (Uf uf: listaUfsNoDuplicada) {
			String fechaStr = uf.getFecha().toInstant().atZone(zoneId).toLocalDate().toString();
			ufs2.add(new UfSalida().new Uf2(fechaStr, df.format(uf.getValor())));
		}
		
		ufSalida.setUFs(ufs2);
		
		// se crea objeto JSON
		Gson gson = new Gson();
		String json = gson.toJson(ufSalida);
		
		// se imprime salida
		System.out.println(json);
		
	}
}
