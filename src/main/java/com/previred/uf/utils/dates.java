package com.previred.uf.utils;

import com.previred.desafio.tres.uf.vo.Uf;
import org.joda.time.LocalDate;

import java.text.SimpleDateFormat;
import java.util.*;

public class dates {
    public List<Date> ObtieneFechasFaltantes(Date inicio, Date fin, List<Date> dates){
        //Primero lleno todas las fechas, de todos los días...
        List<Date> lstFechasFaltantes = new ArrayList<>();
        for (LocalDate date = new LocalDate(inicio); date.isBefore(new LocalDate(fin)); date = date.plusDays(1)) {
            lstFechasFaltantes.add(date.toDate());
        }
        for (int j = 0; j < dates.toArray().length; j += 1) {
            if (lstFechasFaltantes.indexOf((Date) dates.toArray()[j]) > 1) {
                //System.out.println("La fecha existe: " + dates.toArray()[j]);
                lstFechasFaltantes.remove(lstFechasFaltantes.indexOf((Date) dates.toArray()[j]));
            }
        }

        Collections.sort(lstFechasFaltantes);
        //System.out.println("solo fechas que faltan: ");
        //for(Date date: lstFechasFaltantes){
        //    System.out.println(date);
        //}

        return lstFechasFaltantes;
    }

    public static String formatDate(Date fecha, String pattern){
        SimpleDateFormat sdf;
        sdf = new SimpleDateFormat(pattern);
        return sdf.format(fecha);
    }
}
