/**
 * 
 */
package com.previred.valoresuf.app;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.previred.desafio.tres.uf.DatosUf;
import com.previred.desafio.tres.uf.Valores;
import com.previred.desafio.tres.uf.vo.Uf;
import com.previred.desafio.tres.uf.vo.Ufs;
import com.previred.valoresuf.model.UfsList;
import com.previred.valoresuf.model.UfsRespuesta;
import com.previred.valoresuf.util.CompararFechas;
import com.previred.valoresuf.util.Util;

/**
 * @author crist
 *
 */
public class App {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		//Obtenemos los datos de la libreria
		Valores v = new Valores();
		Ufs ufs = v.getRango();	
		DatosUf datos = DatosUf.getInstance();
		List<Uf> list = datos.getUfs(ufs.getInicio(), ufs.getFin());

		System.out.println("--------------------");
		System.out.println("--------------------");
		System.out.println("--------------------");
		System.out.println("Fecha Inicio " + Util.formatDate(ufs.getInicio()));
		System.out.println("Fecha Fin " + Util.formatDate(ufs.getFin()));

		//se crea una lista auxiliar de los datos entregados por la clase singleton
		//para comparar los datos faltantes que nos entrega getRango.
		List<UfsList> listaAuxiliar = new ArrayList<>();
		list.forEach(x -> {
			UfsList lista = new UfsList();
			lista.setFecha(Util.formatDate(x.getFecha()));
			lista.setDato(x.getValor());
			listaAuxiliar.add(lista);
		});
		
		//se crea una instacia de la clase que retornaremos como respuesta en json
		UfsRespuesta respuesta = new UfsRespuesta();
		List<UfsList> lista = new ArrayList<>();
		
		respuesta.setInicio(Util.formatDate(ufs.getInicio()));
		respuesta.setFin(Util.formatDate(ufs.getFin()));
		
		//se completa la lista con los datos entregados por el metodo getRango
		ufs.getUfs().forEach(a -> {
			UfsList ufslist = new UfsList();
			ufslist.setFecha(Util.formatDate(a.getFecha()));
			ufslist.setDato(a.getValor());
			lista.add(ufslist);
		});
		
		//se compara la lista auxiliar con la lista de respuesta
		//y se agrega el objeto a la lista si este no se encuentra dentro de la lista de respuesta
		listaAuxiliar.forEach(element ->{
			if(!lista.contains(element)) {
				lista.add(element);
			}
		});
		
		//se ordena la lista por fecha de mayor a menor
		//y se agrega a al objeto de respuesta
		Collections.sort(lista, new CompararFechas());
		respuesta.setUFs(lista);
		
		System.out.println("--------------------");
		System.out.println("--------------------");
		lista.forEach(a -> System.out.println("Fecha " + a.getFecha() + " Valor "+ a.getDato()));
		
		//se genera el traspaso de objeto a json para exportar el archivo .json
		if(Util.generarArchivoJson(Util.extraerObjetoJSON(respuesta))) {
			System.out.println("--------------------");
			System.out.println("--------------------");
			System.out.println("valores.json generado");
			System.out.println("--------------------");
			System.out.println("--------------------");
		}

	}

}
