package com.previred.desafio;

import com.previred.desafio.tres.uf.DatosUf;
import com.previred.desafio.tres.uf.vo.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import org.apache.logging.log4j.*;

/**
 * @author Carolina Fuenzalida
 */
public class Utiles {

    static Logger log = LogManager.getRootLogger();
    static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

    /**
     * Método para ordenar una lista por fecha en forma descendente
     *
     * @param listaUf Lista de tipo Uf
     * @return Lista ordenada
     */
    public static List<Uf> ordenaPorFechaDesc(List<Uf> listaUf) {
        try {
            if(!listaUf.isEmpty()){
             Collections.sort(listaUf, Comparator.comparing(Uf::getFecha).reversed());
            }
        } catch (Exception e) {
            log.error("Error Utiles.ordenaPorFechaDesc: " + e.getMessage());
        }

        return listaUf;
    }

    /**
     * Método para completar una lista con fechas y UFs faltantes en el
     * intervalo indicado
     *
     * @param fechasUfsRango Datos obtenidos del método Valores.getRango
     * @param fechaInicio Fecha inicial del intervalo
     * @param fechaFin Fecha final del intervalo
     * @return Lista de UF con fechas y valores del rango indicado
     */
    static List<Uf> completarFechas(List<Uf> fechasUfsRango, Date fechaInicio, Date fechaFin) throws ParseException {
        List<Uf> fechasUfsFin = new ArrayList<>();
        DatosUf datosUf = DatosUf.getInstance();
        int i = 0; //Índice de la lista
        String fechaAux = ""; //Fecha Auxiliar para ir comparando con la fecha actual

        if(!fechasUfsRango.isEmpty() && !validarFecha(sdf.format(fechaInicio)) && !validarFecha(sdf.format(fechaFin))) {
            log.error("Utiles.completarFechas: Datos incorrectos");
            return fechasUfsFin;
        }
        
        try {
            //Traemos la lista valores del rango a trabajar
            List<Uf> fechasUfLista = datosUf.getUfs(fechaInicio, fechaFin);

            //Ordenamos la lista en forma ascendente, para poder recorrerla
            Collections.sort(fechasUfsRango, (x, y) -> sdf.format(x.getFecha()).compareToIgnoreCase(sdf.format(y.getFecha())));

            //Recorremos la lista que contiene todas fechas y valores (datosUf.getUfs)
            for (Uf ufsFuncion : fechasUfLista) {
                String fechaActFmt = sdf.format(ufsFuncion.getFecha());
                if (fechaActFmt.compareTo(fechaAux) == 0) {
                    log.error("Fecha Repetida: " + fechaActFmt);
                    continue;
                }

                //Con este objeto construimos la lista final
                Uf ufFechaAct = null;

                //De haber coincidencia, tomamos los valores de fechasUfsRango
                if (i < fechasUfsRango.size()) {
                    Uf fechaUfRango = fechasUfsRango.get(i);
                    if (fechaActFmt.compareTo(sdf.format(fechaUfRango.getFecha())) == 0) {
                        ufFechaAct = fechaUfRango;
                        i++;
                    }
                }

                //De NO haber coincidencia, tomamos los valores de fechasUfLista
                if (ufFechaAct == null) {
                    ufFechaAct = ufsFuncion;
                }

                //Alimentamos la lista final
                fechasUfsFin.add(ufFechaAct);
                fechaAux = fechaActFmt;
            }
        } catch (Exception e) {
            log.error("Utiles.completarFechas: " + e.getMessage());
        }

        return fechasUfsFin;
    }

    public static boolean validarFecha(String fecha) {
        try {
            sdf.setLenient(false);
            sdf.parse(fecha);
        } catch (ParseException e) {          
            log.error("Error Utiles.validarFecha: " + e.getMessage());
            return false;
        }
        return true;
    }
}
