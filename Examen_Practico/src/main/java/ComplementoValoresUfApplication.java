
import service.ComplementoValoresUfImpl;
import java.text.ParseException;


public class ComplementoValoresUfApplication {


    /**Metodo principal encargado de ejecutar la funcionalidad**/

    public static void main (String [] args) throws ParseException {

        ComplementoValoresUfImpl complementoValoresUf = new ComplementoValoresUfImpl();

        complementoValoresUf.obtenerRangosFecha();


    }

}

