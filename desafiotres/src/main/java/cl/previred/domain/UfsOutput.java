package cl.previred.domain;

import java.util.List;

public class UfsOutput {
    private String inicio;
    private String fin;
    private List<UfOutput> UFs;

    public UfsOutput() {
    }



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

    public List<UfOutput> getUFs() {
        return UFs;
    }

    public void setUFs(List<UfOutput> UFs) {
        this.UFs = UFs;
    }

}
