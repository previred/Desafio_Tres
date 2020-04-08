package com.previred.uf.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.previred.desafio.tres.uf.vo.Uf;
import com.previred.desafio.tres.uf.vo.Ufs;
import com.previred.uf.delegate.impl.DatosUfDelegateImpl;
import com.previred.uf.delegate.impl.ValoresDelegateImpl;
import com.previred.uf.model.uf;
import com.previred.uf.model.ufs;
import com.previred.uf.utils.dates;
import com.previred.uf.utils.number;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.*;

import static com.previred.uf.utils.json.objetToJson;

public class valores {

    public String generaJSON() throws Exception {
        //Primero consumimos getRango
        ValoresDelegateImpl valores = new ValoresDelegateImpl();
        DatosUfDelegateImpl datosUfService = new DatosUfDelegateImpl();
        dates dat = new dates();
        Ufs ufs = valores.getRango();


        List<Uf> ufList = new ArrayList<>(ufs.getUfs());
        List<Date> lstDates = new ArrayList<>();
        for(int j=0;j<ufList.toArray().length;j++)
        {
            lstDates.add(ufList.get(j).getFecha());
        }

        //Obtengo las fechas faltantes que se entregaron...
        List<Date> lstFechasFaltantes = dat.ObtieneFechasFaltantes(ufs.getInicio(), ufs.getFin(), lstDates);

        //Ahora que tenemos las fechas faltantes, las complementamos en la lista original
        List<Uf> lstUf = new ArrayList<>();
        for (int i = 0; i < lstFechasFaltantes.toArray().length; i += 1){
            //Obtenemos el valor a la fecha faltante
            Uf uf = datosUfService.getUf(lstFechasFaltantes.get(i));
            //System.out.println("Uf obtenido: " + uf);
            ufList.add(uf);
        }
        //Se ordena de forma descendente
        ufList.sort(Comparator.comparing(Uf::getFecha).reversed());

        //Formateamos los números al deseado y lo dejamos en la lista uf de salida
        List<uf> _listUf;
        _listUf = number.formatArrayNumber(ufList, Locale.CANADA);

        //Ahora que tengo los datos, genero la salida
        ufs objSalida = new ufs();
        objSalida.setInicio(dates.formatDate(ufs.getInicio(),"yyyy-MM-dd"));
        objSalida.setFin(dates.formatDate(ufs.getFin(),"yyyy-MM-dd"));
        objSalida.setUfs(_listUf);

        //Lo parseo a JSON
        return objetToJson(objSalida);
    }
}
