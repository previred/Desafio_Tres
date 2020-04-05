package com.cromero.previred.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cromero.previred.dto.UFValueDTO;
import com.cromero.previred.services.UFService;

@RestController
@RequestMapping("/uf")
public class UFController {
	@Autowired
	private UFService ufService;

	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<UFValueDTO> getUfs() throws Exception {
		UFValueDTO response = this.ufService.getUfs();
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

}
