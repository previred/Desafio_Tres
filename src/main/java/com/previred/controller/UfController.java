package com.previred.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.node.ObjectNode;
import com.previred.services.UfService;

@RestController
public class UfController {

	@Autowired
	private UfService ufService;

    
	@GetMapping(value = "/api/uf", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ObjectNode> getUf() throws IOException {
		
		ObjectNode uf = ufService.getUf();
		
		return ResponseEntity.ok(uf);
		
	}
	

}
