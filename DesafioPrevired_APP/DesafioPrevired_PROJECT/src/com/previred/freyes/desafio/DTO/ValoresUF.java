package com.previred.freyes.desafio.DTO;

import java.util.Date;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;


@XmlRootElement(name = "valores")
@XmlAccessorType(XmlAccessType.FIELD)
public class ValoresUF {
    private String inicio;
    private String fin;
    @XmlElement(name="UF")
    List<UeFe> uf;

    public void setInicio(String inicio) {
        this.inicio = inicio;
    }

    public String getInicio() {
        return inicio;
    }

    public void setFin(String fin) {
        this.fin = fin;
    }

    public String getFin() {
        return fin;
    }

    public void setUf(List<UeFe> uf) {
        this.uf = uf;
    }

    public List<UeFe> getUf() {
        return uf;
    }

}
