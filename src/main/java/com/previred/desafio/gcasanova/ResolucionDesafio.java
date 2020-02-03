package com.previred.desafio.gcasanova;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.google.gson.Gson;
import com.google.gson.JsonIOException;
import com.previred.desafio.model.ListadoUf;
import com.previred.desafio.tres.uf.DatosUf;
import com.previred.desafio.tres.uf.Valores;
import com.previred.desafio.tres.uf.vo.Uf;
import com.previred.desafio.tres.uf.vo.Ufs;
import com.previred.desafio.utils.CollectionUtils;
import com.previred.desafio.utils.DateUtils;

@Component
public class ResolucionDesafio {

	@Autowired
	private DateUtils utilsDate;

	@Autowired
	private CollectionUtils utilsCollection;

	private static final Logger logger = LogManager.getLogger(ResolucionDesafio.class);

	
	/**
	 * Metodo encargado de resolver la problematica planteada en el ejercicio Numero 3
	 * @author gabjjc
	 */
	public void resolution() {

		Date fechaAnterior = null;
		Date fechaActual = null;

		boolean first = true;

		Ufs listaValores = new Valores().getRango();

		// obtenemos la lista de fechas/uf para ordenarla
		ArrayList<Uf> arr = new ArrayList<Uf>(listaValores.getUfs());
		
		// ordenamos la lista para simplificar algoritmo de busqueda y completada de datos.
		utilsCollection.sortCollection(arr);

		// Lista temporal que contendra los objetos a agregar.
		ArrayList<Uf> listaCompleta = new ArrayList<Uf>();

		for (Uf datos : arr) {

			fechaActual = datos.getFecha();

			if (first) {
				first = false;
				if (!fechaActual.equals(listaValores.getInicio())) {
					listaCompleta.addAll(getValoresPeriodo(utilsDate.getMoreDays(listaValores.getInicio(), -1),
							utilsDate.getMoreDays(fechaActual, -1)));
				}
			} else {
				if (!fechaActual.equals(utilsDate.getMoreDays(fechaAnterior, 1))) {
					listaCompleta.addAll(getValoresPeriodo(fechaAnterior, utilsDate.getMoreDays(fechaActual, -1)));
				}
			}
			fechaAnterior = datos.getFecha();
		}

		if (!fechaAnterior.equals(listaValores.getFin())) {
			listaCompleta.addAll(getValoresPeriodo(fechaAnterior, listaValores.getFin()));
		}
		
		//Agregamos los objetos faltantes a la lista principal.
		arr.addAll(listaCompleta);

		//Ordenamos para generar el archivo final.
		utilsCollection.sortCollection(arr);

		//Creamos el objeto final con el que generaremos el archivo
		ListadoUf listadoUf = new ListadoUf(listaValores.getInicio(), listaValores.getFin(), arr);
		
		//Se genera el archivo Json
		try {
			converToJson(listadoUf);
		} catch (Exception e) {
			logger.error(e);
		}
	}

	
	/**
	 * 
	 * Metodo encargado de generar el archivo json. 
	 * Se encapsulan las excepciones 
	 * 
	 * @param listadoUf
	 * @return void
	 * @throws Exception
	 */
	private void converToJson(ListadoUf listadoUf) throws Exception {

		try {
			Gson gson = new Gson();
			gson.toJson(listadoUf);

			gson.toJson(listadoUf, new FileWriter(System.getProperty("user.dir") + "/valores.json"));
		} catch (JsonIOException e) {
			logger.error(e.getMessage());
			throw new Exception("Error al parsear archivo json");
		} catch (IOException e) {
			logger.error(e.getMessage());
			throw new Exception("Error al generar archivo Valores.json");
		}
		logger.info("Archivo generado correctamente");
		
	}

	/**
	 * Metodo encargado de obtener valores de uf y fecha
	 * Toman como base los metodos getUf y GetUfs provistos.
	 * 
	 * @param fechaInicio
	 * @param fechaFin
	 * @return Listado de fechas y valores de uf para las fechas indicadas.
	 */
	private ArrayList<Uf> getValoresPeriodo(Date fechaInicio, Date fechaFin) {

		ArrayList<Uf> listValores = new ArrayList<Uf>();
		DatosUf datosDelDia = DatosUf.getInstance();

		DatosUf.getInstance();

		if (fechaFin.equals(utilsDate.getMoreDays(fechaFin, 2))) {
			listValores.add(datosDelDia.getUf(utilsDate.getMoreDays(fechaFin, 1)));
		} else {
			listValores.addAll(datosDelDia.getUfs(utilsDate.getMoreDays(fechaInicio, 1), fechaFin));
		}

		return listValores;
	}

}
