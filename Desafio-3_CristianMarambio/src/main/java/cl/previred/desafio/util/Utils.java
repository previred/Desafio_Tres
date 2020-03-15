package cl.previred.desafio.util;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.log4j.Logger;

import com.previred.desafio.tres.uf.vo.Uf;
import com.previred.desafio.tres.uf.vo.Ufs;

public class Utils {

	private static final Logger logger = Logger.getLogger(Utils.class);
	
	//ordenar fechas
	public List<Uf> orderByDate(Ufs listaUfs) {
		return listaUfs.getUfs().stream().sorted(Comparator.comparing(Uf::getFecha)).collect(Collectors.toList());
	}	
	
//	filtrar fechas que no existen en lista con lagunas
	public List<Uf> getFilterDates(List<Uf> listaIncompleta, List<Uf> listaCompleta){
		List<Uf> fechasFiltradas = new ArrayList<Uf>();
		for(Uf uf : listaCompleta) {
			if(!listaIncompleta.contains(uf)) {
				fechasFiltradas.add(uf);
				logger.info(uf.getFecha());
			}
		}
		return fechasFiltradas;
	}
	
}
