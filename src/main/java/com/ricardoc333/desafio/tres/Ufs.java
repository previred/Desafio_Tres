package com.ricardoc333.desafio.tres;

//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

//package com.previred.desafio.tres.uf.vo;

import com.ricardoc333.desafio.tres.Uf;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class Ufs {
    private Date inicio;
    private Date fin;
    private Set<com.ricardoc333.desafio.tres.Uf> ufs = new HashSet();

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

    public Set<com.ricardoc333.desafio.tres.Uf> getUfs() {
        return this.ufs;
    }

    public void setUfs(Set<Uf> ufs) {
        this.ufs = ufs;
    }
}
