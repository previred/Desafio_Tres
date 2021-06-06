import com.previred.prueba.request.DatosEntrada;
import com.previred.prueba.response.DatosSalida;
import com.previred.prueba.response.Uf;
import com.previred.prueba.response.UfResponse;
import com.previred.prueba.service.DatosUf;
import com.previred.prueba.service.PruebaPreviredUfService;
import com.previred.prueba.service.PruebaPreviredUfServiceImpl;
import net.bytebuddy.implementation.bind.annotation.Super;
import org.apache.log4j.Logger;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

import static org.junit.Assert.*;

public class UfTest {

    private static final Logger log = Logger.getLogger(PruebaPreviredUfServiceImpl.class);

//    @Autowired
//    PruebaPreviredUfServiceImpl PruebaPreviredUfServiceImpl;

    @Test
    public void pruebaDetalleUf(){
        DatosSalida datosSalida = new DatosSalida();
        DatosEntrada datosEntrada = new DatosEntrada();
        try{
            datosEntrada.setInicio("2014-04-01");
            datosEntrada.setFin("2014-05-01");
            datosSalida = obtieneDetalleFechasUf(datosEntrada);
            assertNotNull(datosSalida);
        }catch (Exception e){
            datosSalida = null;
            log.error(e.getMessage());
            assertNotNull(datosSalida);
        }

    }

    public DatosSalida obtieneDetalleFechasUf(DatosEntrada datosEntrada) throws Exception {
        try{
            Date inicio = formatFechaStringADate(datosEntrada.getInicio(), "yyyy-MM-dd");
            Date fin = formatFechaStringADate(datosEntrada.getFin(), "yyyy-MM-dd");
            List<Uf> datosUf = new ArrayList<>();
            datosUf = DatosUf.getInstance().getUfs(inicio,fin);



            //        Agrego la lista de fechas a un map (este paso es par facilitar mas abajo la busqueda directo por el id)
            Map fechasProvenientesServicio = new HashMap();
            for(Uf fechas : datosUf){
                fechasProvenientesServicio.put(obtienefechaStringSegunFormato(fechas.getFecha(), "yyyy-MM-dd"), obtienefechaStringSegunFormato(fechas.getFecha(), "yyyy-MM-dd"));
            }

            List<String> fechasEncontradasReales = new ArrayList();
            int contadorMaximo = 0;
            //       Recorro las fechas segun los parametros de entrada de inicio y fin
            while (!fin.before(inicio)) {
                //          Formateo la fecha para guardar registro en mismo formato segun el ejemplo dado en la respuesta del servicio
                String fechaFormt = obtienefechaStringSegunFormato(inicio, "yyyy-MM-dd");

                Calendar cal = Calendar.getInstance();
                cal.setTime(inicio);
                //            Sumo 1 dia para pasar al siguiente
                cal.add(Calendar.DAY_OF_WEEK, 1);

                //            se agregada a la lista
                fechasEncontradasReales.add(fechaFormt);
                //          Agrego la nueva fecha a consultar
                inicio = cal.getTime();
                contadorMaximo = contadorMaximo + 1;
                if(contadorMaximo == 101){
                    break;
                }

            }

            //            Ahora que cuento con todos los datos, preparo el objeto de salida
            List<UfResponse> fechasServicio = new ArrayList<>();
            List<UfResponse> fechasFaltantes = new ArrayList<>();
            UfResponse ufResponse = new UfResponse();

            Uf uf = new Uf();
            //        Recorro las fechas reales encontradas bajo los criterios de entrada de inicio y fin ya verificados
            for (String fechaEncontrada: fechasEncontradasReales){
//            Verifico a traves de la fechaReal si existe en el listado de fechas entregado por el servicio, si no existe, la agrego a las faltantes
                uf = new Uf();
                try{
                    uf = DatosUf.getInstance().getUf(formatFechaStringADate(fechaEncontrada, "yyyy-MM-dd"));
                }catch (Exception e){
                    uf.setFecha(formatFechaStringADate(fechaEncontrada, "yyyy-MM-dd"));
                    uf.setValor(0.0);
                }
                if(!fechasProvenientesServicio.containsKey(fechaEncontrada)){
                    ufResponse = new UfResponse(uf);
                    fechasFaltantes.add(ufResponse);
                } else {
                    ufResponse = new UfResponse(uf);
                    fechasServicio.add(ufResponse);
                }
            }

//            Las ingreso a traves de un contructor al objeto de respuesta
            DatosSalida datosSalida = new DatosSalida(datosEntrada, fechasServicio, fechasFaltantes);
            return datosSalida;

        }catch (Exception e){
            log.error(e.toString());
            throw e;

        }
    }


    private Date formatFechaStringADate(String fechaString, String formatoEntrada)throws Exception {

        if (fechaString == null || fechaString == " ") {
            throw new Exception("Fecha null");
        } else {
            try {
                //        Pasar fecha de String a Date
                Date fechaDate = new Date();
                SimpleDateFormat formatoFecha = new SimpleDateFormat(formatoEntrada);
                fechaDate = formatoFecha.parse(fechaString);
                return fechaDate;
                ///// fin /////
            } catch (Exception e) {
                log.error(e.getMessage());
                throw e;
            }
        }
    }

    private String obtienefechaStringSegunFormato(Date fecha, String formatoSalida) {
        String fechaString = "";
        if (fecha == null) {
            return fechaString;
        } else {
            try {
                DateFormat formato = new SimpleDateFormat(formatoSalida);
                fechaString = formato.format(fecha);
                //System.out.println("====================>  Fecha de salida :  "+fechaString);
                return fechaString;
            } catch (Exception e) {
                fechaString = "error: "+e;
                return fechaString;
            }
        }
    }

}
