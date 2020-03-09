package c.previred.funciones;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.previred.desafio.tres.uf.DatosUf;
import com.previred.desafio.tres.uf.vo.Uf;
import com.previred.desafio.tres.uf.vo.Ufs;

import cl.previred.bean.UfInfoDiaria;
import cl.previred.bean.UfListaRango;
import cl.previred.utilidades.Utils;
import one.util.streamex.StreamEx;

/**
*Clase que contiene la logica del desafio
* @author  Carlos Barra
* @version 1.0
* @since   08-03-2020
*/

public class UfDesafioFunciones {
	
	private static final Logger logger = LogManager.getLogger(UfDesafioFunciones.class);
	private static DecimalFormat df = new DecimalFormat(Utils.DECIMAL_FORMAT);
	
	public UfListaRango completeListDateUf(List<Uf> orderList) {
		//Funcion que rellena las fechas faltantes dentro del rango
		logger.info("Se ejecuta completeListDateUf");
		UfListaRango outObject = new UfListaRango();		
		ArrayList<UfInfoDiaria> UfsArray = new ArrayList<UfInfoDiaria>();
		StreamEx.of(orderList.stream()).forPairs((current, next) -> {
			int dias=diffDate(current.getFecha(), next.getFecha()) ;
			UfInfoDiaria auxUf = new UfInfoDiaria();
			if(dias  > 1 ) {
				auxUf.setDato(df.format((current.getValor())));
				auxUf.setFecha(current.getFecha());
				UfsArray.add(auxUf);
				for (int j = 0; j < dias; j++) {
					UfInfoDiaria auxUfdateDif = new UfInfoDiaria();
					Calendar c = Calendar.getInstance();
					c.setTime(current.getFecha());
					c.add(Calendar.DATE, j + 1);
					Date currentDatePlusOne = c.getTime();					
					try {
						Uf valUf = DatosUf.getInstance().getUf(currentDatePlusOne);
						auxUfdateDif.setDato(df.format((valUf.getValor())));
						auxUfdateDif.setFecha(valUf.getFecha());
						UfsArray.add(auxUfdateDif);

					} catch (Exception e) {
						logger.error("Error en getValor() , no se pudo obtener el valor para la fecha " + currentDatePlusOne );
						//cuando no existe un valor de UF para el dia se genera un objeto auxiliar
						auxUfdateDif.setDato(df.format((999)));
						auxUfdateDif.setFecha(currentDatePlusOne);
						UfsArray.add(auxUfdateDif);
					}

				}
			}else {
				auxUf.setDato(df.format((current.getValor())));
				auxUf.setFecha(current.getFecha());
				UfsArray.add(auxUf);
			}
		});
		outObject.setUFs(UfsArray);
		return outObject;
	}
	
	public static int diffDate(Date first , Date last) {
		return (int) TimeUnit.DAYS.convert(last.getTime() - first.getTime(), TimeUnit.MILLISECONDS);
	}
	
	public void createFileJson(UfListaRango orderListFin) {
		//Funcion que crea el archivo json
		logger.info("Se ejecuta createFileJson");
		GsonBuilder builder = new GsonBuilder();
		Gson gson = builder.setDateFormat(
				Utils.FORMAT_DATE
				).setPrettyPrinting().create();

		String outFile  = gson.toJson(orderListFin);
		try {
			logger.info("Ruta donde se deja el archivo json " + Utils.PATH_FILE);
			Files.write(Paths.get(Utils.PATH_FILE), outFile.getBytes());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			logger.error("Error en le creacion del archivo", e);
			e.printStackTrace();
		}

	}
	public List<Uf> sortListUfs(Ufs rango) {
		//Funcion que ordena por fecha la lista
		logger.info("Se ejecuta sortListUfs");
		return rango.getUfs().stream().sorted(
				Comparator.comparing(Uf::getFecha))
				.collect(Collectors.toList());
	}
	
}
