package com.cl.demo;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Set;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonIOException;
import com.previred.desafio.tres.uf.DatosUf;
import com.previred.desafio.tres.uf.Valores;
import com.previred.desafio.tres.uf.vo.Uf;
import com.previred.desafio.tres.uf.vo.Ufs;

public class UfIService {

	public static void generateJson() {

		UfResponse ufResponse = new UfResponse();
		Valores val = new Valores();

		Ufs rangos = val.getRango();
		Date inicio = rangos.getInicio();
		Set<Uf> ufs = rangos.getUfs();
		Date fin = rangos.getFin();

		try {

			DatosUf datoUf = DatosUf.getInstance();

			List<UfObject> ufList = new ArrayList<UfObject>();

			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

			for (Uf uf : ufs) {
				UfObject udObject = new UfObject();
				
				//valido que una fecha venga sin valor de uf y le seteo el valor perteneciente al dia
				if (uf.getValor() == null) {
					Uf ufValor = datoUf.getUf(uf.getFecha());
					uf.setValor(ufValor.getValor());
				}

				udObject.setFecha(formatter.format(uf.getFecha()));
				udObject.setDato(String.valueOf(uf.getValor()));

				ufList.add(udObject);

			}

			//ordeno lista de forma descendiete
			Collections.sort(ufList, new Comparator<UfObject>() {
				public int compare(UfObject uf1, UfObject uf2) {
					return uf2.getFecha().compareTo(uf1.getFecha());
				}
			});

			ufResponse.setInicio(formatter.format(inicio));
			ufResponse.setFin(formatter.format(fin));
			ufResponse.setUFs(ufList);

			Gson gson = new GsonBuilder().setPrettyPrinting().create();

			//creo el archivo json
			Writer writer = new FileWriter("C:\\Users\\Public\\Documents\\valores.json");

			gson.toJson(ufResponse, writer);

			writer.close();

		} catch (JsonIOException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		System.out.println("[ufResponse :]" + ufResponse.toString());

	}
	
}
