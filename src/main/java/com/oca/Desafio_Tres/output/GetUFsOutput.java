package com.oca.Desafio_Tres.output;

import javax.validation.constraints.NotBlank;
import java.util.Date;

public class GetUFsOutput {
    @NotBlank
    private Date inicio;

    @NotBlank
    private Date fin;

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

    @Override
    public String toString() {
        return "inicio=" + inicio + ", fin=" + fin;
    }
}
