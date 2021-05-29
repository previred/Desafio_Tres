import java.time.LocalDate;
import java.util.Date;
import java.util.List;

public class ResponseJSON {
    private Date inicio, fin;
    private List<ResponseUf> UFs;

    public ResponseJSON(Date inicio, Date fin, List<ResponseUf> UFs) {
        this.inicio = inicio;
        this.fin = fin;
        this.UFs = UFs;
    }

    public Date getInicio() {
        return inicio;
    }

    public void setInicio(Date inicio) {
        this.inicio = inicio;
    }

    public Date getFin() {
        return fin;
    }

    public void setFin(Date fin) {
        this.fin = fin;
    }

    public List<ResponseUf> getUFs() {
        return UFs;
    }

    public void setUFs(List<ResponseUf> UFs) {
        this.UFs = UFs;
    }
}
