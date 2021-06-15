package com.desafio.tres.valoresuf.contract.to;

public class UfTO {

    private String fecha;
    private Double dato;

    public Double getDato() {
        return dato;
    }

    public void setDato(Double dato) {
        this.dato = dato;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public UfTO withDato(Double dato){
        setDato(dato);
        return this;
    }
    public UfTO withFecha(String fecha){
        setFecha(fecha);
        return this;
    }
}
