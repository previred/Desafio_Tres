package desafio.com.desafio3;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.previred.desafio.tres.uf.DatosUf;
import com.previred.desafio.tres.uf.Valores;
import com.previred.desafio.tres.uf.vo.Uf;
import com.previred.desafio.tres.uf.vo.Ufs;

import desafio.com.desafio3.to.UfTo;
import desafio.com.desafio3.to.UfsTo;

public class Main {
	
	public static void main(String[] args) {
		String respuesta = valoresUf();
		System.out.println(respuesta);
	}
		
	private static String valoresUf() {
		UfsTo ufsTo = new UfsTo();
		Valores valores = new Valores();
		Ufs ufs = new Ufs();
		List<Uf> listaAux = new ArrayList<Uf>();
		List<UfTo> listaJson = new ArrayList<UfTo>();
		SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");

		try {
			DatosUf datosUf = DatosUf.getInstance();
			ufs = valores.getRango();
			List<Uf> lista = new ArrayList<>(ufs.getUfs());
			ordenarListaUf(lista);

			for (int i = 0; i < lista.size(); i++) {
				int diferencia = 0;
				if (i < lista.size() - 1) {
					diferencia = (int) ((lista.get(i).getFecha().getTime() - lista.get(i + 1).getFecha().getTime())/86400000) * -1;
				}
				if (diferencia > 0) {
					for (int j = 0; j < diferencia - 1; j++) {
						Calendar calendar = Calendar.getInstance();
						calendar.setTime(lista.get(i).getFecha());
						calendar.add(Calendar.DAY_OF_YEAR, j + 1);
						Date nuevaFecha = calendar.getTime();
						Uf nuevaUf = new Uf();
						nuevaUf.setFecha(nuevaFecha);
						nuevaUf.setValor(datosUf.getUf(nuevaFecha).getValor());

						listaAux.add(nuevaUf);
					}
				}
			}

			for (int i = 0; i < listaAux.size(); i++) {
				lista.add(listaAux.get(i));
			}
			ordenarListaUf(lista);
			for (int i = 0; i < lista.size(); i++) {
				UfTo uf = new UfTo();
				uf.setFecha(formato.format(lista.get(i).getFecha()));
				uf.setValor(lista.get(i).getValor());
				listaJson.add(uf);
			}

			ufsTo.setInicio(formato.format(ufs.getInicio()));
			ufsTo.setFin(formato.format(ufs.getFin()));
			ufsTo.setUfs(listaJson);

			ObjectMapper mapper = new ObjectMapper();
			mapper.writeValue(new File("Archivo Salida\\valores.json"), ufsTo);

		} catch (Exception e) {
			return "Ocurrio un Error: "+e.getMessage();
		}
		return "OK";
	}
	
	private static void ordenarListaUf(List<Uf> lista){
		Collections.sort(lista, new Comparator<Uf>() {
			@Override
			public int compare(Uf p1, Uf p2) {
				return p1.getFecha().compareTo(p2.getFecha());
			}
		});
	}
}