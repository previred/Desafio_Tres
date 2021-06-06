package com.previred.prueba.response;


import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class Ufs {
    private Date inicio;
    private Date fin;
    private Set<Uf> ufs = new HashSet();

    public Ufs() {
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

    public Set<Uf> getUfs() {
        return this.ufs;
    }

    public void setUfs(Set<Uf> ufs) {
        this.ufs = ufs;
    }
}

