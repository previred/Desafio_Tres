package com.previred.desafio.tres.service;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import com.previred.desafio.tres.utils.ListadoUf;

public interface IComplementoValoresUfService {

	ListadoUf getDatos(int metodo);

	void exportCsv(HttpServletResponse response) throws IOException;

}
