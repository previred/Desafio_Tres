package com.previred.desafio.tres.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.previred.desafio.tres.service.IComplementoValoresUfService;

@RestController
public class ComplementoValoresUfRestController {

	@Autowired
	private IComplementoValoresUfService valoresUfService;

	@GetMapping(value = "/metodoUno")
	public ResponseEntity<?> datosJson() {

		return ResponseEntity.ok(valoresUfService.getDatos(1));
	}

	@GetMapping(value = "/metodoDos")
	public ResponseEntity<?> datosJsonAlternativo() {
		return ResponseEntity.ok(valoresUfService.getDatos(2));
	}

}
