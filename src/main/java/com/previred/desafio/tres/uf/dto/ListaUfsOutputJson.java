package com.previred.desafio.tres.uf.dto;

import java.util.List;

public class ListaUfsOutputJson {
    private String inicio;
    private String fin;
    private List<UfOutputJsonFormat> UFs;


    public String getInicio() {
        return inicio;
    }

    public void setInicio(String inicio) {
        this.inicio = inicio;
    }

    public String getFin() {
        return fin;
    }

    public void setFin(String fin) {
        this.fin = fin;
    }

    public List<UfOutputJsonFormat> getUFs() {
        return UFs;
    }

    public void setUFs(List<UfOutputJsonFormat> UFs) {
        this.UFs = UFs;
    }

    public ListaUfsOutputJson() {
    }
}
