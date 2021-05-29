package com.acl.challenger;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.previred.desafio.tres.uf.DatosUf;
import com.previred.desafio.tres.uf.Valores;
import com.previred.desafio.tres.uf.vo.Uf;
import com.previred.desafio.tres.uf.vo.Ufs;
import java.io.FileWriter;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;


/**
 * @Author: Kevyn Delgado
 * @ Description: Clase principal desafio 3
 */
public class Principal {
    public static void main(String[] args) {
        JsonObject jsonObject = new JsonObject();
        JsonArray ufsArray = new JsonArray();
        Valores valores = new Valores();
        DatosUf datos = DatosUf.getInstance();
        Ufs ufs = valores.getRango();

        //Obtener fecha Inicio, Fecha Fin y Lista de Ufs
        Date fechaInicio = ufs.getInicio();
        Date fechaFin = ufs.getFin();

        List<Uf> listOfUfs = new ArrayList<>(ufs.getUfs());

        // Concatenar la lista de rango completo y la lista generada por el .jar
        List<Uf> listOfUfsAux = Stream.concat(datos.getUfs(fechaInicio, fechaFin).stream(), listOfUfs.stream()).collect(Collectors.toList());

        //Limpiar y cargar los datos  sin duplicados en una nueva lista
        List<Uf> listOfUfs2 = new ArrayList<>();
        listOfUfs2.addAll(new LinkedHashSet<>(listOfUfsAux));

        //Ordenar la lista auxiliar y asignar valores a la lista original generada por el .jar
        listOfUfs.clear();
        listOfUfs.addAll(new HashSet<>(listOfUfsAux).stream()
                .sorted(Comparator.comparing(Uf::getFecha).reversed()).collect(Collectors.toCollection(LinkedHashSet::new)));

        ufsArray = getJsonArrayUfs(listOfUfs);

        jsonObject.addProperty("inicio", new java.sql.Date(fechaInicio.getTime()).toLocalDate().toString());
        jsonObject.addProperty("fin", new java.sql.Date(fechaFin.getTime()).toLocalDate().toString());
        jsonObject.add("UFs", ufsArray);

        try {
            FileWriter file = new FileWriter("valores.json");
            file.write(jsonObject.toString());
            file.flush();
            file.close();
        } catch (Exception ex) {
            System.out.println("Error al escribir en el archivo de salida: " + ex);
        }
    }

    public static JsonArray getJsonArrayUfs(List listUfs) {
        JsonArray result = new JsonArray();
        Uf uf;

        for (int i = 0; i < listUfs.size(); i++) {
            JsonObject newUf = new JsonObject();
            uf = (Uf) listUfs.get(i);
            newUf.addProperty("fecha", new java.sql.Date(uf.getFecha().getTime()).toLocalDate().toString());
            newUf.addProperty("valor", uf.getValor().toString());
            result.add(newUf);
        }
        return result;
    }
}
