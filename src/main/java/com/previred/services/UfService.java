package com.previred.services;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.util.DefaultPrettyPrinter;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.previred.desafio.tres.uf.DatosUf;
import com.previred.desafio.tres.uf.Valores;
import com.previred.desafio.tres.uf.vo.Uf;
import com.previred.desafio.tres.uf.vo.Ufs;

@Component
public class UfService {
	
	private JsonNodeFactory nodeFactory = JsonNodeFactory.instance;
	private DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	

	public ObjectNode getUf() {
		Valores valores = new Valores();
		
		/*
		 * Retorna listado de valores de Uf para un rango de fecha al azar que entrega la libreria Previred
		 */
		Ufs rangouf = valores.getRango();
		List<Uf> listaUf = new ArrayList<>(rangouf.getUfs());
		
		/*
		 * Se comprueba si el primer y ultimo valor del listado, corresponden a la fecha de inicio y final del rango de fechas,
		 * en caso de que no lo sean, se llama al metodo DatosUf.getUf para recuperar el valor de uf para el primer dia y ultimo dia 
		 */
		
		if(!rangouf.getInicio().equals(listaUf.get(0).getFecha())) {
			Uf ufinicio = DatosUf.getInstance().getUf(rangouf.getInicio());
			listaUf.add(ufinicio);
		}
		if(!rangouf.getFin().equals(listaUf.get(listaUf.size() - 1).getFecha())) {
			Uf uffin = DatosUf.getInstance().getUf(rangouf.getFin());
			listaUf.add(uffin);
		}
		
		/*
		 * Ordena el listado de ufs obtenido
		 */
		List<Uf> ufOrdenado = ordenaListado(listaUf);
		
		/*
		 * Busca y completa el listado completo de Ufs para el rango de fechas entregado por el metodo getRango  
		 */
		List<Uf> listadoCompleto = buscaValoresUFs(ufOrdenado);
		
		/*
		 * Se arma el json de salida con el listado completo de Ufs
		 */
		ObjectNode response = nodeFactory.objectNode();
		response.put("inicio", dateFormat.format(rangouf.getInicio()));
		response.put("fin", dateFormat.format(rangouf.getFin()));
		ArrayNode ufs = nodeFactory.arrayNode();
		
		for (Uf listado: listadoCompleto) {
			ObjectNode valorUf = nodeFactory.objectNode();
			valorUf.put("fecha", dateFormat.format(listado.getFecha()));
			valorUf.put("dato", UtilService.formatNumber(listado.getValor()));
			ufs.add(valorUf);
		}
		
		response.set("UFs", ufs);
		
		/*
		 * Se genera el archivo json de salida
		 */
		ObjectMapper mapper = new ObjectMapper();
		ObjectWriter writer = mapper.writer(new DefaultPrettyPrinter());
		try {
			writer.writeValue(new File("export/valores.json"), response);
		} catch (IOException e) {
			ObjectNode responseError = nodeFactory.objectNode();
			responseError.put("Error", "Ha ocurrido un problema en la generacion del archivo json");
			return responseError;
		}
		return response;
	}
	
	/*
	 * Ordena el listado de Ufs entregado al azar por getRango
	 */

	public List<Uf> ordenaListado (List<Uf> ufOrdenado){
		Collections.sort(ufOrdenado, (o1, o2) -> o1.getFecha().compareTo(o2.getFecha()));
		return ufOrdenado;
	}
	
	/*
	 * Metodo que busca los valores de Ufs faltantes para el rango entregado por getRango
	 */
	public List<Uf> buscaValoresUFs(List<Uf> listadoUf){
		
		List<Uf> listUF = new ArrayList<>();
		
		for(int i=0; i<listadoUf.size()-1; i++) {
			LocalDate ldate = UtilService.toLocalDate(listadoUf.get(i).getFecha());
			LocalDate nextlDate = UtilService.toLocalDate(listadoUf.get(i + 1).getFecha());
			
			/*
			 * Calcula la diferencia de dias entre los valores entregados por getRango
			 */
			int dif = Period.between(ldate, nextlDate).getDays() - 1;
			
			/*
			 * Si la diferencia es 0, se agrega el valor al listado
			 * Si la diferencia es 1, se busca el valor para la uf del dia faltante con el metodo DatosUf.getUf
			 * Si la diferencia es mayor a 1, se buscan todos los valores de la uf faltantes con el metodo DatosUf.getUfs
			 */
			if(dif == 0) {
				listUF.add(listadoUf.get(i));
			}else if(dif == 1) {
				listUF.add(listadoUf.get(i));
				Uf diaFaltante = DatosUf.getInstance().getUf(UtilService.sumarDia(listadoUf.get(i).getFecha()));
				listUF.add(diaFaltante);
			}else if(dif > 1) {
				List<Uf> rangoFaltante = DatosUf.getInstance().getUfs(
					listadoUf.get(i).getFecha(), 
					UtilService.sumarDia(listadoUf.get(i + 1).getFecha(), -1)
					);
				listUF.addAll(rangoFaltante);
			}			
		}
		listUF.add(listadoUf.get(listadoUf.size() - 1));
		return listUF;
	}
	
}
