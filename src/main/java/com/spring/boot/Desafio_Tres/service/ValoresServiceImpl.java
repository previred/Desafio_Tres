package com.spring.boot.Desafio_Tres.service;

import com.previred.desafio.tres.uf.DatosUf;
import com.previred.desafio.tres.uf.Valores;
import com.previred.desafio.tres.uf.vo.Uf;
import com.previred.desafio.tres.uf.vo.Ufs;
import com.spring.boot.Desafio_Tres.model.ComparadorFecha;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.StringWriter;
import java.text.SimpleDateFormat;
import java.util.*;

@Component
public class ValoresServiceImpl implements ValoresService{

    @Override
    public String obtenerUf() {
        //Se consume la función getRango y se almacena los valores en variables
        Valores valores = new Valores();
        Ufs rango = valores.getRango();
        Date fechaInicio = rango.getInicio();
        Date fechaFin = rango.getFin();
        Set<Uf> ufs = rango.getUfs();

        System.out.println("fechaInicioRango:" + fechaInicio);
        System.out.println("fechaFinRango: " + fechaFin);
        System.out.println("listado de valores de UF: " + ufs);

        return obtenerValoresFaltantes(fechaInicio, fechaFin);
    }

    /**
     * Procedimiento que obtiene una lista con los valores correspondientes a todas
     * las fechas dentro de un cierto rango obtenido de la función getRango.
     * @param fechaInicio fecha de inicio de rango.
     * @param fechaFin fecha final de rango.
     * @return
     */
    public String obtenerValoresFaltantes(Date fechaInicio, Date fechaFin){

        DatosUf datosUf = DatosUf.getInstance();

        //Se ejecuta la función getUfs para obtener todos los valores de UF.
        List<Uf> listaValoresUfs = datosUf.getUfs(fechaInicio, fechaFin);

        // Se realiza el orden de fechas de manera descendente.
        try {
            Comparator comparator = Collections.reverseOrder(new ComparadorFecha());
            Collections.sort(listaValoresUfs, comparator);

            System.out.println("lista ordenada listaValoresUfs");
            System.out.println(listaValoresUfs);

            //Se genera el archivo Json
            return generarArchivoJson(fechaInicio, fechaFin, listaValoresUfs);
        }catch(Exception e){
            System.out.println("Ocurrió un error: "+ e);
        }
        return null;
    }

    /**
     * Procedimiento que genera un archivo Json de la lista obtenida por
     * la función getUfs.
     * @param fechaInicio fecha de inicio de rango.
     * @param fechaFin fecha final de rango.
     * @param listaUfs
     */
    public String generarArchivoJson(Date fechaInicio, Date fechaFin, List<Uf> listaUfs) {
        String message = "";
        Map obj = new LinkedHashMap();
        SimpleDateFormat dateFormat = new SimpleDateFormat ("yyyy-MM-dd");

        obj.put("inicio", dateFormat.format(fechaInicio));
        obj.put("fin", dateFormat.format(fechaFin));

        JSONArray list = new JSONArray();
        for (Uf uf : listaUfs) {
            JSONObject innerObj = new JSONObject();
            String fecha =  dateFormat.format(uf.getFecha());
            Double dato =  uf.getValor();

            innerObj.put("fecha", fecha);
            innerObj.put("dato", dato);

            list.add(innerObj);
        }

        obj.put("UFs", list);
        try {
            File archivo = new java.io.File("valores.json");
            FileWriter file = new FileWriter(archivo);

            StringWriter out = new StringWriter();
            JSONValue.writeJSONString(obj, out);
            String jsonText = out.toString();
            file.write(jsonText);

            file.flush();
            file.close();

            System.out.println("El archivo Json se generó de manera exitosa");
            System.out.println(obj);

            message = "El archivo Json se generó de manera exitosa";

        } catch (IOException e) {
            System.out.println("Ocurrió un problema al generar el archivo: " + e);
            message = "Ocurrió un problema al generar el archivo: " + e;
        }

        return message;
    }
}
