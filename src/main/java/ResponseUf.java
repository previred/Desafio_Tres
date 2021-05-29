import java.time.LocalDate;
import java.util.Date;

public class ResponseUf {
    private Date fecha;
    private String dato;

    public ResponseUf(Date fecha, String dato) {
        this.fecha = fecha;
        this.dato = dato;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getDato() {
        return dato;
    }

    public void setDato(String dato) {
        this.dato = dato;
    }
}
