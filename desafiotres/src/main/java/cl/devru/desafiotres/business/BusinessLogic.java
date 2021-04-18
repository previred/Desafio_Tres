package cl.devru.desafiotres.business;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.previred.desafio.tres.uf.DatosUf;
import com.previred.desafio.tres.uf.Valores;
import com.previred.desafio.tres.uf.vo.Ufs;

import cl.devru.desafiotres.dto.Complement;
import cl.devru.desafiotres.dto.Uf;
import cl.devru.desafiotres.utils.DateUtils;

public class BusinessLogic {

	private Ufs data;
	private ObjectMapper mapper;
	private DatosUf datosUf;
	private List<Uf> initData;
	private List<Uf> missingData;
	private Complement complement;
	
	public BusinessLogic() {
		super();
	}

	public Boolean initData() {
		this.data = new Valores().getRango();
		this.mapper = new ObjectMapper();
		this.datosUf =  DatosUf.getInstance();
		this.complement = new Complement();
		this.initData = new ArrayList<Uf>();
		this.missingData = new ArrayList<Uf>();
		return true;
	}
	
	public Boolean formatedData() {
		if(data != null) {
			if(this.complement == null) {
				complement = new Complement();
			}
			complement.setFin(DateUtils.toStringFormat(data.getFin(), DateUtils.SHORT_FORMAT));
			complement.setInicio(DateUtils.toStringFormat(data.getInicio(), DateUtils.SHORT_FORMAT));

			if(data.getUfs() != null && Boolean.FALSE.equals(data.getUfs().isEmpty())) {
				data.getUfs().forEach((uf) -> {
					Uf newUF = new Uf();
					newUF.setDato(String.valueOf(uf.getValor()));
					newUF.setFecha(DateUtils.toStringFormat(uf.getFecha(), DateUtils.SHORT_FORMAT));
					initData.add(newUF);
				});
				initData.sort(Comparator.comparing(Uf::getFecha).reversed());
				complement.setUFs(initData);
				System.out.println("Dias iniciales obtenidos entre Fecha Inicio y Fecha Fin: "+complement.getUFs().size());
				return true;
			}
			return false;
		}else {
			System.out.println("data no iniciada, se retorna false");
			return false;
		}

	}
	
	public Boolean completeData() {
		
		if(mapper != null) {

			Boolean fin = false;
			int nextDays = 0;
			System.out.println("Completando Fechas faltantes... ");
			/**** Se realiza busqueda de fechas dia a dia desde la fecha de Inicio hasta fecha fin *****/
			while(Boolean.FALSE.equals(fin)){
				/**** Se calcula el siguiente Dia *****/
				Date nextDate = DateUtils.calculateDate(data.getInicio(), nextDays);
				String nextDay = DateUtils.toStringFormat(nextDate, DateUtils.SHORT_FORMAT);
				
				/**** Se busca fecha en arreglo valores UFs *****/
				Uf uf = complement.getUFs().stream()
						  .filter(unidadFomento -> nextDate.equals(DateUtils.toDate(unidadFomento.getFecha(), DateUtils.SHORT_FORMAT) ))
						  .findAny()
						  .orElse(null);
				
				/**** Si no existe la fecha se agrega *****/
				if(uf == null) {
					uf = new Uf();
					uf.setFecha(nextDay);
					
					/**** Se hace uso del método getUf desde el singleton para obtener el valor UF de la fecha faltante *****/
					uf.setDato(String.valueOf(datosUf.getUf(nextDate).getValor()));
					complement.getUFs().add(uf);
					this.missingData.add(uf);
				}
				
				/**** Se valida si es la fecha fin *****/
				if(nextDate.equals(data.getFin())) {
					fin = true;
				}
				nextDays += 1;
				uf = null;
			}
			
			/**** Se ordenan fechas de forma descendente. *****/
			complement.getUFs().sort(Comparator.comparing(Uf::getFecha).reversed());
			
			System.out.println("Dias completados OK...");
			/**** Se crea JSON y se guarda en archivo valores.json *****/
	        try (FileWriter writer = new FileWriter("valores.json")) {
				mapper.writeValue(writer, complement);
				System.out.println("Dias trancurridos entre Fecha Inicio y Fecha Fin: "+(nextDays-2));
				System.out.println("Dias faltantes    entre Fecha Inicio y Fecha Fin: "+this.missingData.size());
				System.out.println("Archivo valores.json creado OK ");
				return true;
	        } catch (IOException e) {
	        	System.err.println("IOException: Error al crear archvio JSON: " + e);
	        	return false;
	        }

		}else {
			System.err.println("data no iniciada, no se crear archivo");
			return false;
		}		
	}
	
	public String getCompleteDataJson() {
		String json = "";
		if(mapper != null) {
			try {
				json = mapper.writeValueAsString(this.complement);
			} catch (JsonProcessingException e) {
				System.err.println("JsonProcessingException: " + e);
			}
		}else {
			System.out.println("data no iniciada, se retorna vacio");
		}
		return json;
	}
	
}
