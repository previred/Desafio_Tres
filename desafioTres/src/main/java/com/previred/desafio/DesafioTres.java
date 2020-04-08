package com.previred.desafio;

import com.previred.desafio.tres.uf.*;
import com.previred.desafio.tres.uf.vo.*;
import java.text.ParseException;
import java.util.*;
import org.apache.logging.log4j.*;

/**
 * @author Carolina Fuenzalida
 */
public class DesafioTres {

    static Logger log = LogManager.getRootLogger();

    public static void main(String[] args) {
        procesaDatos();
    }

    private static void procesaDatos() {
        try {
            Valores valores = new Valores();
            Ufs ufs = valores.getRango();

            //Fechas del rango aleatorio
            Date inicio = ufs.getInicio();
            Date fin = ufs.getFin();

            //Set de UFs
            Set<Uf> setUfs = ufs.getUfs();

            //Convertimos el Set en List
            List<Uf> listaUfs = new ArrayList<>(setUfs);

            //Completamos la lista con las fechas y UFs faltantes
            List<Uf> listaCompleta = Utiles.completarFechas(listaUfs, inicio, fin);

            //Ordenamos la listaCompleta por fecha de forma descendente
            List<Uf> listaOrdenadaDesc = Utiles.ordenaPorFechaDesc(listaCompleta);

            //Generamos el archivo XML
            CreaXML.generarXML(inicio, fin, listaOrdenadaDesc);

        } catch (ParseException ex) {
            log.error("ERROR: " + ex.getMessage());
        }
    }
}
