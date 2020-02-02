package com.previred.desafio.tres;

import org.apache.log4j.Logger;

import com.previred.desafio.tres.util.ControlarException;

public class Main {

	private final static Logger LOGGER = Logger.getLogger(Main.class.getName());

	public static void main(String[] args) {
		ControlarException controlarException = new ControlarException();
		GenerarArchivoUf archivoUf = new GenerarArchivoUf();
		try {
			archivoUf.generarArchivo();
		} catch (Exception e) {
			LOGGER.error(e);
			System.out.println(controlarException.getMessage(e.getMessage()));
		}
	}
}
