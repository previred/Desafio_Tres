package com.oca.Desafio_Tres.output;

import java.util.Date;
import java.util.List;

public class GetUFsOutput {
    private String inicio;
    private String fin;
    private List<UF> UFs;

    public GetUFsOutput(String inicio, String fin, List<UF> ufs) {
        this.inicio = inicio;
        this.fin = fin;
        this.UFs = ufs;
    }

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

    public List<UF> getUFs() {
        return UFs;
    }

    public void setUFs(List<UF> UFs) {
        this.UFs = UFs;
    }

    @Override
    public String toString() {
        return "inicio=" + inicio + ", fin=" + fin;
    }
}
