package com.previred.desafio.tres.uf.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.previred.desafio.tres.uf.common.UFConstant;
import com.previred.desafio.tres.uf.service.UFService;

@RestController
@RequestMapping(value = "api/v1", produces = { MediaType.APPLICATION_JSON_VALUE })
@CrossOrigin("*")
public class UFController {
	
	@Autowired
	private UFService ufservice;
	
	@GetMapping("uf/download")
	public ResponseEntity<byte[]> downloadFile() throws Exception {
		String json = ufservice.getUfs();
		byte[] isr = json.getBytes();
		
		String fileName = UFConstant.FILENAME + "." + UFConstant.FILEEXTENSION;
		HttpHeaders respHeaders = new HttpHeaders();
		respHeaders.setContentLength(isr.length);
		respHeaders.setContentType(new MediaType("text", "json"));
		respHeaders.setCacheControl("must-revalidate, post-check=0, pre-check=0");
		respHeaders.set(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + fileName);
		return new ResponseEntity<byte[]>(isr, respHeaders, HttpStatus.OK);
	}
}
