package com.oca.Desafio_Tres.input;

import com.oca.Desafio_Tres.input.validation.ValidDate;

import javax.validation.constraints.NotBlank;

public class GetUFsInput {
    @NotBlank(message = "No debe estar vacío")
    @ValidDate(message = "Debe ser fecha yyyy-mm-dd")
    private String inicio;

    @NotBlank(message = "No debe estar vacío")
    @ValidDate(message = "Debe ser fecha yyyy-mm-dd")
    private String fin;

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

    @Override
    public String toString() {
        return "inicio=" + inicio + ", fin=" + fin;
    }
}
