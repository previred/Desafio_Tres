package com.previred.prueba.response;

import java.util.Date;
import java.util.Objects;

public class Uf {
    private Double valor;
    private Date fecha;

    public Uf() {
    }

    public Double getValor() {
        return this.valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }

    public Date getFecha() {
        return this.fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String toString() {
        return "Uf{valor=" + this.valor + ", fecha=" + this.fecha + '}';
    }

    public int hashCode() {
        int hash = 3;
        hash = 67 * hash + Objects.hashCode(this.fecha);
        return hash;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        } else if (obj == null) {
            return false;
        } else if (this.getClass() != obj.getClass()) {
            return false;
        } else {
            Uf other = (Uf)obj;
            return Objects.equals(this.fecha, other.fecha);
        }
    }
}
