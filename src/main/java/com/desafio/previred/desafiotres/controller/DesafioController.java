package com.desafio.previred.desafiotres.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.desafio.previred.desafiotres.services.DesafioServices;
import com.fasterxml.jackson.core.JsonProcessingException;

@RestController
class DesafioController {

	@Autowired
	DesafioServices desafioService;

	@GetMapping("/devolverValores")
	public ResponseEntity<byte[]> getList() throws JsonProcessingException {
		return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=valores.json")
				.contentType(MediaType.APPLICATION_JSON).body(desafioService.generateUfs().getBytes());
	}

}