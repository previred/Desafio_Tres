package com.desafio.tres.valoresuf.contract.to;

import java.util.ArrayList;
import java.util.List;

public class UfsTO {
    private String inicio;
    private String fin;
    private List<UfTO> ufs = new ArrayList<>();


    public String getInicio() {
        return this.inicio;
    }

    public void setInicio(String inicio) {
        this.inicio = inicio;
    }

    public String getFin() {
        return this.fin;
    }

    public void setFin(String fin) {
        this.fin = fin;
    }

    public List<UfTO> getUfs() {
        return this.ufs;
    }

    public void setUfs(List<UfTO> ufs) {
        this.ufs = ufs;
    }

    public UfsTO withInicio(String inicio){
        setInicio(inicio);
        return this;
    }
    public UfsTO withFin(String fin){
        setFin(fin);
        return this;
    }
    public UfsTO withUfs(List<UfTO> ufs){
        setUfs(ufs);
        return this;
    }

}
