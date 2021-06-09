package com.previred.prueba.response;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class UfResponse {
    private Double valor;
    private String fecha;


    public UfResponse(){

    }
    public UfResponse(Uf uf){
        this.valor = uf.getValor();
        this.fecha = obtienefechaStringSegunFormato(uf.getFecha(), "yyyy-MM-dd");
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }



    private String obtienefechaStringSegunFormato(Date fecha, String formatoSalida) {
        String fechaString = "";
        if (fecha == null) {
            return fechaString;
        } else {
            try {
                DateFormat formato = new SimpleDateFormat(formatoSalida);
                fechaString = formato.format(fecha);
                //System.out.println("====================>  Fecha de salida :  "+fechaString);
                return fechaString;
            } catch (Exception e) {
                fechaString = "error: "+e;
                return fechaString;
            }
        }
    }
}
