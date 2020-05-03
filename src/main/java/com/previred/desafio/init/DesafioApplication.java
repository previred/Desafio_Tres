package com.previred.desafio.init;

import com.previred.desafio.persistence.dao.impl.ValoresDAOImpl;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DesafioApplication {
	private final static Logger LOGGER = Logger.getLogger("bitacora.subnivel.Control");
	 
	public static void main(String[] args) {
		LOGGER.log(Level.INFO, "Salida Desafio Previred - Generar archivo de valores UFs");
		ValoresDAOImpl objInit = new ValoresDAOImpl();
		
		// Se genera archivo valores.json usando método getUf() de la clase DatosUf
		objInit.generarJsonValoresGetUf();
		
		// Se genera archivo valoresUf.json usando método getUfs() de la clase DatosUf
		objInit.generarJsonValoresGetUfs();
	}
}
