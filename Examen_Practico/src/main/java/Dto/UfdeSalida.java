package Dto;


/** Objeto Dto que genera la estructura de salida del campo fecha como tipo String **/

public class UfdeSalida {


    private String fecha;
    private Double dato;

    public UfdeSalida() {

    }

    public Double getDato() {
        return this.dato;
    }

    public void setDato(Double dato) {
        this.dato = dato;
    }

    public String getFecha() {
        return this.fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String toString() {
        return "Uf{valor=" + this.dato + ", fecha=" + this.fecha + '}';
    }


}

