package model;

import java.util.Date;

public class UfSalida {

    private Date fechaUf;
    private String fechaUfStr;
    private double valorUf;

    public Date getFechaUf() {
        return fechaUf;
    }

    public void setFechaUf(Date fechaUf) {
        this.fechaUf = fechaUf;
    }

    public String getFechaUfStr() {
        return fechaUfStr;
    }

    public void setFechaUfStr(String fechaUfStr) {
        this.fechaUfStr = fechaUfStr;
    }

    public double getValorUf() {
        return valorUf;
    }

    public void setValorUf(double valorUf) {
        this.valorUf = valorUf;
    }
}
