package com.previred.challenge.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

@JacksonXmlRootElement(localName = "Valores") 
public class Valores {

	private Date inicio;

	private Date fin;

	@JacksonXmlProperty(localName = "uf")
    @JacksonXmlElementWrapper(useWrapping = true, localName = "UFs")
	private List<Uf> UFs = new ArrayList<>();

	public Date getInicio() {
		return inicio;
	}

	public void setInicio(Date inicio) {
		this.inicio = inicio;
	}

	public Date getFin() {
		return fin;
	}

	public void setFin(Date fin) {
		this.fin = fin;
	}

	public List<Uf> getUFs() {
		return UFs;
	}

	public void setUFs(List<Uf> uFs) {
		UFs = uFs;
	}

}
