package com.desafio3.valoresuf.model;

public class UF {
    private String fecha;
    private String dato;


    public UF(String fecha, String valor) {
        this.fecha = fecha;
        this.dato = valor;
    }

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
}
