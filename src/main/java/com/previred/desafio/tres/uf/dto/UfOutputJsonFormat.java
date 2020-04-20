package com.previred.desafio.tres.uf.dto;

public class UfOutputJsonFormat {
    private String fecha;
    private String dato;

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getDato() {
        return dato;
    }

    public void setDato(String dato) {
        this.dato = dato;
    }

    public UfOutputJsonFormat(String fecha, String dato) {
        this.fecha = fecha;
        this.dato = dato;
    }
}
