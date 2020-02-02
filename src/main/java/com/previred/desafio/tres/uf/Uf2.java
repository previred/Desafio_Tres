package com.previred.desafio.tres.uf;

/*clase para formatear UF*/
public class Uf2 {

    public Uf2(String fecha, String dato) {
        super();
        this.fecha = fecha;
        this.dato = dato;
    }

    private final String dato;
    private final String fecha;

    @Override
    public String toString() {
        return "Data [fecha=" + fecha + ", dato=" + dato
                + "]";
    }
}
