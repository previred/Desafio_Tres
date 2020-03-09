package cl.previred.desafio;


import java.text.DecimalFormat;
import java.util.List;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import com.previred.desafio.tres.uf.Valores;
import com.previred.desafio.tres.uf.vo.Uf;
import com.previred.desafio.tres.uf.vo.Ufs;
import c.previred.funciones.UfDesafioFunciones;
import cl.previred.bean.UfListaRango;
import cl.previred.utilidades.Utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


/**
*Clase main que contiene las 
*llamadas a las funciones que realizan
*la logica del desarrollo
* @author  Carlos Barra
* @version 1.0
* @since   08-03-2020
*/

public class DesafioPreviredApplication {

	private static DecimalFormat df = new DecimalFormat(Utils.DECIMAL_FORMAT);
	private static final Logger logger = LogManager.getLogger(DesafioPreviredApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(DesafioPreviredApplication.class, args);

		logger.info("Iniciando aplicacion desafio");
		try {
			UfDesafioFunciones controlador = new UfDesafioFunciones();
			
			//Se ejecuta funcion importada desde la api
			logger.info("Se ejecuta UfListaRango");
			Valores valores = new Valores();
			Ufs rango = valores.getRango();
			UfListaRango orderListFin = new UfListaRango();

		
			//Se ordena la lista de fechas por fecha
			List<Uf> orderList = controlador.sortListUfs(rango);
			
			//Se setean los valores de inicio y fin del rango
			orderListFin = controlador.completeListDateUf(orderList);
			orderListFin.setInicio(rango.getInicio());
			orderListFin.setFin(rango.getFin());

			//se crea el archivo json
			controlador.createFileJson(orderListFin);
			
			
		}catch (Exception e) {
			logger.error("Ocurrio un error ejecutando la aplicacion"  , e);
		} finally {
			logger.info("Terminando aplicacion desafio");
		}
			

	}


} 


