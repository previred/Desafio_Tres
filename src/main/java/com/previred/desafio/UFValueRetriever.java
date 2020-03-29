package com.previred.desafio;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.previred.desafio.tres.uf.DatosUf;
import com.previred.desafio.tres.uf.Valores;
import com.previred.desafio.tres.uf.vo.Uf;
import com.previred.desafio.tres.uf.vo.Ufs;
import com.previred.desafio.util.DateConverter;

public class UFValueRetriever {
	
	private DatosUf datosUf = DatosUf.getInstance();
	
	public UFValueResult getUF(){
		Valores valores = new Valores();
	
		Ufs ufs = valores.getRango();
		
		List<Uf> valoresUf = retrieveAllUfValues(ufs);
		
		Comparator<Uf> ufcomparator = (Uf uf1, Uf uf2) -> uf1.getFecha().compareTo(uf2.getFecha()) ;
		valoresUf.sort(ufcomparator.reversed());
		
		UFValueResult result = new UFValueResult(ufs.getInicio(), ufs.getFin(), valoresUf);
		return result;
	}

	private List<Uf> retrieveAllUfValues(Ufs ufs) {
		LocalDate fechaInicial = DateConverter.convertDateToLocalDate(ufs.getInicio());
		LocalDate fechaFinal = DateConverter.convertDateToLocalDate(ufs.getFin());
		
		Set<Uf> setOfUfs = ufs.getUfs();
		
		System.out.println("Fecha Inicial: " + fechaInicial);
		System.out.println("Fecha Final: " + fechaFinal);
		
		Map<LocalDate, Uf> dateUfMap = convertSetToMap(setOfUfs);
		
		List<Uf> valoresUf = new ArrayList<Uf>();
		for (LocalDate date = fechaInicial; date.isBefore(fechaFinal) || date.isEqual(fechaFinal); date = date.plusDays(1))
		{
		    if(dateUfMap.containsKey(date)) {
		    	//obengo valor calculaado
		    	valoresUf.add(dateUfMap.get(date));
		    	
		    } else {
		    	//calculo valor UF.
		    	valoresUf.add(getUfFrom(date));
		    }
		    
		}
		return valoresUf;
	}

	private Uf getUfFrom(LocalDate localDate) {
		Date date = DateConverter.convertLocalDateToDate(localDate);
		return datosUf.getUf(date);
	}

	private Map<LocalDate, Uf> convertSetToMap(Collection<Uf> setOfUfs) {

		Map<LocalDate,Uf> map = new HashMap<LocalDate, Uf>();
		
		for (Uf uf : setOfUfs) {
			map.put( DateConverter.convertDateToLocalDate(uf.getFecha()) , uf);
		}
		
		return map;
	}

}
