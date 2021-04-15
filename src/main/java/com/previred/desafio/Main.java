package com.previred.desafio;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.previred.desafio.tres.uf.DatosUf;
import com.previred.desafio.tres.uf.Valores;
import com.previred.desafio.tres.uf.vo.Uf;
import com.previred.desafio.tres.uf.vo.Ufs;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {

    public static void main(String[] args) {

        Valores valores = new Valores();
        Ufs ufs = valores.getRango();

        long cantidadDias = ChronoUnit.DAYS.between(dateToLocalDate(ufs.getInicio()), dateToLocalDate(ufs.getFin())) + 1;

        Set<Uf> setUfProporcionadas = ufs.getUfs();

        List<Uf> listaUfFaltantes = Stream.iterate(dateToLocalDate(ufs.getInicio()), f -> f.plusDays(1))
                .limit(cantidadDias)
                .filter(f -> setUfProporcionadas.stream().noneMatch(u -> dateToLocalDate(u.getFecha()).isEqual(f)))
                .map(f -> DatosUf.getInstance().getUf(localDateToDate(f)))
                .collect(Collectors.toList());

        // Se juntan los valores de ambas listas
        List<Uf> listaFinal = Stream.of(setUfProporcionadas, listaUfFaltantes)
                .flatMap(Collection::stream)
                .sorted(Comparator.comparing(Uf::getFecha).reversed())
                .collect(Collectors.toList());

        System.out.println("Fecha inicio: " + ufs.getInicio());
        System.out.println("Fecha fin: " + ufs.getFin());
        System.out.println("Cantidad de días: " + cantidadDias);
        System.out.println("Cantidad de UF proporcionadas: " + setUfProporcionadas.size());
        System.out.println("Cantidad de UF faltantes: " + listaUfFaltantes.size());
        System.out.println("Cantidad de días = cantidad de UF listaFinal: " + (cantidadDias == listaFinal.size()));

        crearArchivoJson(ufs, listaFinal);

    }

    private static void crearArchivoJson(Ufs ufs, List<Uf> listaFinal) {

        String nombreArchivo = "valores.json";

        /* No es la forma más limpia de generar el JSON solicitado, pero preferí
        limitarme a usar las clases proporcionadas por la libería en lugar de
        crear unas nuevas (que proporcionaría mejor control sobre la serialización).*/

        try (Writer writer = new FileWriter(nombreArchivo)) {
            Gson gson = new GsonBuilder()
                    .setDateFormat("yyyy-MM-dd")
                    .setFieldNamingStrategy(c -> c.getName().equals("valor") ? "dato" : c.getName())
                    .setPrettyPrinting()
                    .create();

            JsonElement jsonElementUfs = gson.toJsonTree(ufs);
            JsonElement jsonElementListaFinal = gson.toJsonTree(listaFinal);
            jsonElementUfs.getAsJsonObject().add("UFs", jsonElementListaFinal);
            jsonElementUfs.getAsJsonObject().remove("ufs");

            gson.toJson(jsonElementUfs, writer);

            System.out.println(System.getProperty("user.dir"));

            System.out.println("Archivo " + nombreArchivo + " creado correctamente");

        } catch (IOException e) {
            System.out.println("Ocurrió un error al generar el archivo JSON");
        }
    }

    private static LocalDate dateToLocalDate(Date fecha) {
        return fecha.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
    }

    private static Date localDateToDate(LocalDate fecha) {
        return Date.from(fecha.atStartOfDay(ZoneId.systemDefault()).toInstant());
    }

}
