package com.ricardoc333.desafio.tres.uf;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;

import java.util.Date;
import java.util.List;

public class Valores {

    private Date inicio;

    private Date fin;

    // Etiqueta para Formato de Impresión
    @JacksonXmlElementWrapper(localName = "UFs")
    private List<Uf> ufs;

    public Valores() {
    }

    public Date getInicio() {
        return this.inicio;
    }

    public void setInicio(Date inicio) {
        this.inicio = inicio;
    }

    public Date getFin() {
        return this.fin;
    }

    public void setFin(Date fin) {
        this.fin = fin;
    }

    public List<Uf> getUfs() {
        return this.ufs;
    }

    public void setUfs(List<Uf> ufs) {
        this.ufs = ufs;
    }
}
