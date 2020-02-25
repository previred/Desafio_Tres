package com.previred.desafio.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.previred.desafio.model.UfsTO;
import com.previred.desafio.service.UfService;

@RestController
public class Controller {

	@Autowired
	private UfService service;
	
	@GetMapping(value="/valores", produces = "application/json")
	public UfsTO getJsonResponse() {
		return service.generateJSON();
	}
	
}
