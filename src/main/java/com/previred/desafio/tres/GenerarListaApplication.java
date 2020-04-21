package com.previred.desafio.tres;

import java.util.logging.Logger;

import com.previred.desafio.tres.service.ComplementoValoresService;

public class GenerarListaApplication {

	private static final Logger LOGGER = Logger.getLogger(GenerarListaApplication.class.getName());

	public static void main(String[] args) {
		ComplementoValoresService service = new ComplementoValoresService();
		String respuesta = service.produceComplementoValores().toString();
		LOGGER.info(respuesta);

	}
}
