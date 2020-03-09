package cl.desafio.uf.utilidades;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;

import static java.util.Calendar.DAY_OF_YEAR;

/**
 * Created by APIUXNB531 on 7/3/2020.
 */
public class Util {

    public static int diferenciaDias(final Date fechaMayor, final Date fechaMejor) {
        Period period = Period.between(
                fechaMejor.toInstant().atZone(ZoneId.systemDefault()).toLocalDate(),
                fechaMayor.toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
        return period.getDays();
    }

    public static Date cambiarFecha(final Date fecha, final int valor){
        if (valor==0) return fecha;
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(fecha);
        calendar.add(DAY_OF_YEAR, valor);
        return calendar.getTime();
    }

    public static String fechaString(final Date fecha, final String formato){
        return new SimpleDateFormat(formato).format(fecha);
    }

    public static String doubleString(final Double valor, final String formato) {
        return new DecimalFormat(formato).format(valor);
    }

}
