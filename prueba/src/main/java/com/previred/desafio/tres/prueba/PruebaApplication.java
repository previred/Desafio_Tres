package com.previred.desafio.tres.prueba;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.previred.desafio.tres.prueba.core.CalculoUF;

@SpringBootApplication
public class PruebaApplication {

	public static void main(String[] args) {
		SpringApplication.run(PruebaApplication.class, args);
		
		// Llama a la logica de negocio para obtener las UF del periodo
		CalculoUF.calculoUF();
	}

}
