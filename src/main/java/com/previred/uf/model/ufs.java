package com.previred.uf.model;

import java.util.List;

public class ufs {
    private String inicio;
    private String fin;

    public ufs() {
    }

    public String getInicio() {
        return inicio;
    }

    public String getFin() {
        return fin;
    }

    public void setInicio(String inicio) {
        this.inicio = inicio;
    }

    public void setFin(String fin) {
        this.fin = fin;
    }

    public void setUfs(List<uf> ufs) {
        this.UFs = ufs;
    }

    public List<uf> getUfs() {
        return UFs;
    }

    private List<uf> UFs;
}
