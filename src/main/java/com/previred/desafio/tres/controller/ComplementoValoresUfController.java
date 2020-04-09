package com.previred.desafio.tres.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.previred.desafio.tres.service.IComplementoValoresUfService;

@Controller
public class ComplementoValoresUfController {

	@Autowired
	private IComplementoValoresUfService valoresUfService;

	@GetMapping(value = "/index")
	public String index() throws IOException {
		return "index";
	}

	@GetMapping(value = "/descargaCsv")
	public void exportCsv(HttpServletResponse response) throws IOException {
		valoresUfService.exportCsv(response);
	}

}
