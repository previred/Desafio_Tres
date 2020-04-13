package model;

import java.util.ArrayList;
import java.util.List;

public class UfsSalida {

    private String inicio;
    private String fin;
    private List<Uf> UFs;

    public String getInicio() {
        return inicio;
    }

    public void setInicio(String inicio) {
        this.inicio = inicio;
    }

    public String getFin() {
        return fin;
    }

    public void setFin(String fin) {
        this.fin = fin;
    }

    public List<Uf> getUFs() {

        if (null == UFs) {
            UFs = new ArrayList<Uf>();
        }
        return UFs;
    }

    public void setUFs(List<Uf> UFs) {
        this.UFs = UFs;
    }
}
