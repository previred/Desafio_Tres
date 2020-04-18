package com.previred.challenge.domain;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonSetter;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import com.previred.challenge.util.Util;

@JacksonXmlRootElement(localName = "UF") 
public class Uf extends com.previred.desafio.tres.uf.vo.Uf {

	private String dato;

	@Override
	public Date getFecha() {
		return super.getFecha();
	}

	@JsonGetter("dato")
	public String getDato() {
		return this.dato;
	}

	@JsonSetter("valor")
	public void setDato(Double dato) {
		this.dato = Util.doubleToString(dato);
	}

	@Override
	@JsonIgnore
	public Double getValor() {
		return super.getValor();
	}

}
