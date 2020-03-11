package desafio3.service;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Set;
import java.util.stream.Collectors;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import com.previred.desafio.tres.uf.DatosUf;
import com.previred.desafio.tres.uf.Valores;
import com.previred.desafio.tres.uf.vo.Uf;
import com.previred.desafio.tres.uf.vo.Ufs;

import desafio3.entity.Rango;
import desafio3.entity.UnidadFomento;

@Service
public class DesafioServiceImpl implements DesafioService{
	
	private Logger logger = LogManager.getLogger(DesafioServiceImpl.class);
	
	@Override
	public Rango getRango() {

		
		Valores valores = new Valores();
		
		logger.info("Inicio de generación de datos");
		//Traigo los valores de la librería
		Ufs ufs = valores.getRango();
		
		Set<Uf> valoresExistentes = new HashSet<Uf>();
		valoresExistentes = ufs.getUfs();
		

		//Convierto a una lista para ordenarlos
		List<Uf> ufsOrdenadas = valoresExistentes.stream().collect(Collectors.toList());
		
		//Ordeno los valores por fecha
		Collections.sort(ufsOrdenadas, (uf1, uf2) -> uf1.getFecha().compareTo(uf2.getFecha()));
		
		Date aux = ufs.getInicio();
		Date fin = ufs.getFin();
		
		long diferenciaDias = 0;
		List<UnidadFomento> listAux = new ArrayList<>();
		List<UnidadFomento> listResultado = new ArrayList<>();
		
		logger.info("Inicio de llenado de datos faltantes");
		//Recorro la lista ordenada para ir complentandola con los valores faltantes. 
		//Vuelco el resultado final en una lista auxiliar.
		for (Uf uf:ufsOrdenadas) {
			
			diferenciaDias = ChronoUnit.DAYS.between(aux.toInstant(),uf.getFecha().toInstant());
			
			UnidadFomento ufAux = convertirUf(DatosUf.getInstance().getUf(aux));
			listResultado.add(ufAux);
			
			//Si estoy en el caso en que la diferencias de días
			if ((diferenciaDias >= 2) && (ufsOrdenadas.indexOf(uf) == (ufsOrdenadas.size()-1)) && (uf.getFecha() != fin) ){
				Date fechaFaltanteDesde = incrementarDia(aux);
				Date fechaFaltanteHasta = fin;
				List<Uf> listaFaltante = DatosUf.getInstance().getUfs(fechaFaltanteDesde, fechaFaltanteHasta);
				listAux = convertirUf(listaFaltante);
				listResultado.addAll(listAux);
			} else if (diferenciaDias == 2) {
				Date fechaFaltante = incrementarDia(aux); 
				Uf ufFaltante = DatosUf.getInstance().getUf(fechaFaltante);
				ufAux = convertirUf(ufFaltante);
				listResultado.add(ufAux);
			} else if (diferenciaDias > 2) {
				Date fechaFaltanteDesde = incrementarDia(aux);
				Date fechaFaltanteHasta = decrementarDia(uf.getFecha());
				List<Uf> listaFaltante = DatosUf.getInstance().getUfs(fechaFaltanteDesde, fechaFaltanteHasta);
				listAux = convertirUf(listaFaltante);
				listResultado.addAll(listAux);
			} 
			
			aux=uf.getFecha();
		}
		
		logger.info("Orden decreciente de los datos generados");
		listResultado.sort(Comparator.comparing(UnidadFomento::getFecha).reversed());
		
		Rango rangoFinal = new Rango();
		
		rangoFinal.setInicio(ufs.getInicio());
		rangoFinal.setFin(ufs.getFin());
		
		rangoFinal.setUfs(listResultado);
		
		return rangoFinal;
	}
	
    public static Date incrementarDia(Date fecha)
    {
        Calendar cal = Calendar.getInstance();
        cal.setTime(fecha);
        cal.add(Calendar.DATE, 1);
        return cal.getTime();
    }

    
    public static Date decrementarDia(Date fecha)
    {
        Calendar cal = Calendar.getInstance();
        cal.setTime(fecha);
        cal.add(Calendar.DATE, -1);
        return cal.getTime();
    }

   public static UnidadFomento convertirUf(Uf uf) {
	    UnidadFomento ufResultado = new UnidadFomento();
	    
	    ufResultado.setFecha(uf.getFecha());
	    ufResultado.setDato(cambiarFormato(DatosUf.getInstance().getUf(uf.getFecha()).getValor()));

	   return ufResultado;
   }
   

   public static List<UnidadFomento> convertirUf(List<Uf> listaUf) {
	    List<UnidadFomento> listaUfResultado = new ArrayList();
	    
	    
	    for(Uf uf:listaUf) {
	    	UnidadFomento ufAux = new UnidadFomento();
	    	ufAux.setFecha(uf.getFecha());
	    	ufAux.setDato(cambiarFormato(DatosUf.getInstance().getUf(uf.getFecha()).getValor()));
	    	listaUfResultado.add(ufAux);
	    }

	   return listaUfResultado;
  }

   
   
   public static String cambiarFormato(Double numero) {
		DecimalFormatSymbols formatSymbols = new DecimalFormatSymbols(Locale.getDefault());
		formatSymbols.setDecimalSeparator(',');
		formatSymbols.setGroupingSeparator('.');
		DecimalFormat df = new DecimalFormat("###,###.##", formatSymbols);
		
		return df.format(numero);
   }
    
}
