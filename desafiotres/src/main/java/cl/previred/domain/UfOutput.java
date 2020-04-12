package cl.previred.domain;

public class UfOutput {
    private String fecha;
    private String dato;

    public UfOutput() {
    }

    public UfOutput(String fecha, String dato) {
        this.fecha = fecha;
        this.dato = dato;
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
