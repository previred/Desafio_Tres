package com.ricardoc333.desafio.tres.uf;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ricardoc333.desafio.tres.DatosUf;
import com.ricardoc333.desafio.tres.Uf;
import com.ricardoc333.desafio.tres.Ufs;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
//import com.previred.desafio.tres.uf.DatosUf;

public class Main {

    // Orden Descendente para aplicar a lista
    private static int DESCENDENTE = 1;
    // Orden Ascendente para aplicar a lista
    private static int ASCENDENTE = -1;

    // Procedimiento para Ordenar una lista de Ufs según el criterio recibido (Ascendente o Descendente)
    public static void ordenar(List<Uf> ufs, int criterio) {
        boolean sorted = false;
        Uf temp;
        while(!sorted) {
            sorted = true;
            for (int i = 0; i < (ufs.size() - 1); i++) {
                if (ufs.get(i).getFecha().compareTo(ufs.get(i+1).getFecha()) == criterio) {
                    temp = ufs.get(i);
                    ufs.set(i, ufs.get(i+1));
                    ufs.set(i+1, temp);
                    sorted = false;
                }
            }
        }
    }

    // Funcion para sumar (o restar) una cantidad de días a una fecha de un calendario
    // El signo del parámetro entero "dias" determina si se suma o se resta
    public static Date agregarDias(Calendar calendario, Date fechaInicial, int dias){
        calendario.setTime(fechaInicial);
        calendario.add(Calendar.DATE, dias);
        return calendario.getTime();
    }

    // Procedimiento para Completar una lista de Ufs (con fechas)
    public static void completarUfFaltantes(Ufs ufsBase, List<Uf> ufs) {
        Calendar calendario = Calendar.getInstance();
        long diferenciaMiliSegundos;
        long diferenciaDias;
        // Completamos los vacíos entre las fechas recibidas
        for (int i = 0; i < (ufs.size() - 1); i++) {
            diferenciaMiliSegundos = (ufs.get(i + 1).getFecha().getTime() - ufs.get(i).getFecha().getTime());
            diferenciaDias = TimeUnit.DAYS.convert(diferenciaMiliSegundos, TimeUnit.MILLISECONDS);
            if (diferenciaDias == 2) {
                ufs.add(i + 1, DatosUf.getInstance().getUf(agregarDias(calendario, ufs.get(i).getFecha(), 1)));
                i = i + 1;
            }
            else if (diferenciaDias > 2){
                List<Uf> ufsAgregar = DatosUf.getInstance().getUfs(agregarDias(calendario, ufs.get(i).getFecha(), 1), agregarDias(calendario, ufs.get(i + 1).getFecha(), -1));
                ufs.addAll(i + 1, ufsAgregar);
                i = i + ufsAgregar.size();
            }
        }

        // Completamos los vacíos desde la fecha de inicio (inclusive) hasta la primera fecha recibida
        diferenciaMiliSegundos = (ufs.get(0).getFecha().getTime() - ufsBase.getInicio().getTime());
        diferenciaDias = TimeUnit.DAYS.convert(diferenciaMiliSegundos, TimeUnit.MILLISECONDS);
        if (diferenciaDias == 1) {
            ufs.add(0, DatosUf.getInstance().getUf(ufsBase.getInicio()));
        }
        else if (diferenciaDias > 1){
            List<Uf> ufsAgregar = DatosUf.getInstance().getUfs(ufsBase.getInicio(), agregarDias(calendario, ufs.get(0).getFecha(), -1));
            ufs.addAll(0, ufsAgregar);
        }

        // Completamos los vacíos desde la última fecha recibida hasta fecha de fin (inclusive)
        diferenciaMiliSegundos = (ufsBase.getFin().getTime() - ufs.get(ufs.size() - 1).getFecha().getTime());
        diferenciaDias = TimeUnit.DAYS.convert(diferenciaMiliSegundos, TimeUnit.MILLISECONDS);
        if (diferenciaDias == 1) {
            ufs.add(DatosUf.getInstance().getUf(ufsBase.getFin()));
        }
        else if (diferenciaDias > 1){
            List<Uf> ufsAgregar = DatosUf.getInstance().getUfs(agregarDias(calendario, ufs.get(ufs.size() - 1).getFecha(), 1), ufsBase.getFin());
            ufs.addAll(ufsAgregar);
        }
    }

    // Procedimiento para Imprimir una lista de Ufs en formato JSON con las librerías "jackson-databind" y "jackson-dataformat-xml"
    // Se emplea una clase nueva (Valores) por las limitaciones de mantener el orden por la clase Set
    public static void imprimirUfs(Date fechaInicio, Date fechaFin, List<Uf> ufsOrdenadas){
        try{
            Valores ufsImpresas = new Valores();
            ufsImpresas.setInicio(fechaInicio);
            ufsImpresas.setFin(fechaFin);
            List<com.ricardoc333.desafio.tres.uf.Uf> ufs = new ArrayList<com.ricardoc333.desafio.tres.uf.Uf>();
            for (Uf u: ufsOrdenadas) {
                com.ricardoc333.desafio.tres.uf.Uf u2 = new com.ricardoc333.desafio.tres.uf.Uf();
                u2.setFecha(u.getFecha());
                u2.setValor(u.getValor());
                ufs.add(u2);
            }
            ufsImpresas.setUfs(ufs);
            ObjectMapper objectMapper = new ObjectMapper();
            DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
            objectMapper.setDateFormat(df);
            objectMapper.writeValue(new File("valores.json"), ufsImpresas);
        } catch (IOException e) {
            // Loguear el error conseguido
        }
    }

    public static void main(String args[]){
        com.ricardoc333.desafio.tres.Valores valores = new com.ricardoc333.desafio.tres.Valores();
        // Parte 1: Consumir getRango
        Ufs ufs = valores.getRango();
        List<Uf> ufsOrdenadas = ufs.getUfs().stream().collect(Collectors.toList());
        // Parte 4: Ordenamiento
        ordenar(ufsOrdenadas, DESCENDENTE);
        // Parte 2 y 3: Completar valores/fechas faltantes
        completarUfFaltantes(ufs, ufsOrdenadas);
        // Parte 5: Imprimir Datos. Se selecciona el formato 3 (JSON)
        imprimirUfs(ufs.getInicio(), ufs.getFin(), ufsOrdenadas);
    }
}
