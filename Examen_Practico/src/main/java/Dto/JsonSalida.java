package Dto;

import java.util.List;


/** Objeto Dto que genera la estructura de salida como tipo Json **/

public class JsonSalida {

    private String inicio;
    private String fin;
    private List<UfdeSalida> UFs;

    public String getInicio() {
        return this.inicio;
    }

    public void setInicio(String inicio) {
        this.inicio = inicio;
    }

    public String getfin() {
        return fin;
    }

    public void setFin(String fin) {
        this.fin = fin;
    }

    public List<UfdeSalida> getUFs() {
        return UFs;
    }

    public void setUFs(List<UfdeSalida> uFs) {
        this.UFs = uFs;
    }

    public JsonSalida() {
    }

}
