package com.previred.desafio.tres.prueba.core;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

import com.previred.desafio.tres.prueba.util.CreateXMLFile;
import com.previred.desafio.tres.prueba.util.Utility;
import com.previred.desafio.tres.uf.DatosUf;
import com.previred.desafio.tres.uf.Valores;
import com.previred.desafio.tres.uf.vo.Uf;
import com.previred.desafio.tres.uf.vo.Ufs;

/**
 * Clase que obtiene el valor de la UF de un periodo.
 * 
 * @author rsepulveda
 *
 */
public class CalculoUF {

	/**
	 * Obtiene el valor de la UF de un periodo.
	 */
	public static void calculoUF() {
		System.out.println("calculoUF");

		// Se obtienen los datos de clase Valores
		Valores valores = new Valores();
		Ufs ufs = valores.getRango();

		Date inicio = ufs.getInicio();
		System.out.println("Inicio: " + inicio);
		Date fin = ufs.getFin();
		System.out.println("Fin: " + fin);
		Set<Uf> ufSetList = ufs.getUfs();
		System.out.println("Cantidad registros lista Valores: " + ufSetList.size());

		List<Uf> listaUfInicial = new ArrayList<Uf>();
		if (ufSetList != null && !ufSetList.isEmpty()) {
			// Se transforma la lista obtenida de Set a List
			listaUfInicial = Utility.convertToList(ufSetList);

			// Se ordena la lista en forma descendente
			listaUfInicial.sort((o1, o2) -> o2.getFecha().compareTo(o1.getFecha()));
		}

		// Se obtienen los datos de la clase DatosUf
		DatosUf datosUf = DatosUf.getInstance();
		List<Uf> listaDatosUf = datosUf.getUfs(inicio, fin);
		System.out.println("Cantidad registros lista DatosUf: " + listaDatosUf.size());

		// Se ordena la lista en forma descendente
		listaDatosUf.sort((o1, o2) -> o2.getFecha().compareTo(o1.getFecha()));

		List<Uf> listaUfFinal = listaUfInicial;

		if (listaDatosUf != null && !listaDatosUf.isEmpty()) {
			for (Uf uf1 : listaDatosUf) {
				boolean isFounded = false;
				// Se comparan los datos de ambas listas para incluir los registros faltantes
				if (listaUfInicial != null && !listaUfInicial.isEmpty()) {
					for (Uf uf2 : listaUfInicial) {
						if (uf1.getFecha().equals(uf2.getFecha())) {
							isFounded = true;
							break;
						}
					}

					if (!isFounded) {
						listaUfFinal.add(uf1);
					}
				} else {
					listaUfFinal = listaDatosUf;
					break;
				}
			}
		}

		System.out.println("Cantidad registros listaUfFinal : " + listaUfFinal.size());

		// Se ordena la lista en forma descendente
		listaUfFinal.sort((o1, o2) -> o2.getFecha().compareTo(o1.getFecha()));

		List<Uf> listToXMLFile = new ArrayList<Uf>();
		if (listaUfFinal != null && !listaUfFinal.isEmpty() && listaUfFinal.size() >= 100) {
			for (int i = 0; i < listaUfFinal.size() && i < 100; i++) {
				Uf uf = listaUfFinal.get(i);
				listToXMLFile.add(uf);
			}
		} else {
			System.err.println(
					"No se puede procesar la lista, porque puede estar vacia o ser nula o bien, no cumplir con largo maximo de 100 registros.");
		}

		System.out.println("Cantidad registros listToXMLFile : " + listToXMLFile.size());

		// Se procede a generar archivo XML de salida
		CreateXMLFile xmlFile = new CreateXMLFile();
		xmlFile.createXML(inicio, fin, listToXMLFile);
	}

}
