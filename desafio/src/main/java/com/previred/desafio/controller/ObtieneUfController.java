package com.previred.desafio.controller;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Set;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;
import com.previred.desafio.model.Cabecera;
import com.previred.desafio.model.Cuerpo;
import com.previred.desafio.tres.uf.Valores;
import com.previred.desafio.tres.uf.vo.Uf;

@RestController
public class ObtieneUfController {
	
	Valores valores = new Valores(); 
	
	@RequestMapping(value="/obtieneUf", method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
	public String obtieneValoresUF() throws ParseException{
		
		Gson gson = new Gson();
		Set<Uf> valIn = valores.getRango().getUfs();
		String[] valOut = (valIn.toString().replace("},", ";").replace("Uf{", "").replace("fecha=", "").replace("valor=", "")).split(";");
		
		ArrayList<Cuerpo> Cuerpos = new ArrayList<Cuerpo>();
		
		for (int i = 0; i < valOut.length; i++) {
			
			String[] valDetalle = valOut[i].split(",");
			Cuerpo cuerpo = new Cuerpo(valDetalle[0], valDetalle[1]);
			Cuerpos.add(cuerpo);
		}
		
		Cabecera cabecera = new Cabecera(valores.getRango().getInicio(),valores.getRango().getFin(), Cuerpos);
		
		String json = gson.toJson(cabecera);
		
		return json;
	}

}
