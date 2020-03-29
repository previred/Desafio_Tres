package com.previred.desafio.uf;

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
		
		List<Uf> allUfValues = retrieveAllUfValues(ufs);
		
		Comparator<Uf> ufcomparator = (Uf uf1, Uf uf2) -> uf1.getFecha().compareTo(uf2.getFecha()) ;
		allUfValues.sort(ufcomparator.reversed());
		
		UFValueResult result = new UFValueResult(ufs.getInicio(), ufs.getFin(), allUfValues);
		return result;
	}

	private List<Uf> retrieveAllUfValues(Ufs ufs) {
		LocalDate initialDate = DateConverter.convertDateToLocalDate(ufs.getInicio());
		LocalDate endDate = DateConverter.convertDateToLocalDate(ufs.getFin());
		
		Set<Uf> setOfUfs = ufs.getUfs();
		
		System.out.println("Fecha Inicial: " + initialDate);
		System.out.println("Fecha Final: " + endDate);
		
		Map<LocalDate, Uf> dateUfMap = convertSetToMap(setOfUfs);
		
		List<Uf> valoresUf = new ArrayList<Uf>();
		for (LocalDate date = initialDate; date.isBefore(endDate) || date.isEqual(endDate); date = date.plusDays(1))
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
