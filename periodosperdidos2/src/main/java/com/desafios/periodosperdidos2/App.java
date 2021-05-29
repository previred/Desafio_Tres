package com.desafios.periodosperdidos2;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.previred.desafio.tres.uf.DatosUf;
import com.previred.desafio.tres.uf.Valores;
import com.previred.desafio.tres.uf.vo.Uf;
import com.previred.desafio.tres.uf.vo.Ufs;
import org.apache.log4j.Logger;

import java.util.*;
import java.util.stream.Collectors;

public class App {
    static final Logger logger = Logger.getLogger(App.class);

    public static void main(String[] args) {


        Valores valores = new Valores();
        Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();

        logger.info("Consumir la función getRango de la clase com.previred.desafio.tres.uf.Valores");
        Ufs ufs = valores.getRango();
        logger.debug(gson.toJson(ufs));

        Set<Uf> ufList = DatosUf.getInstance().getUfs(ufs.getInicio(), ufs.getFin()).stream()
                .sorted(Comparator.comparing(Uf::getFecha).reversed())
                .peek(fn -> {
                    Optional<Uf> opUf = ufs.getUfs().stream().filter(fuf -> fuf.getFecha().equals(fn.getFecha())).findFirst();
                    opUf.ifPresent(uf -> fn.setValor(uf.getValor()));
                })
                .collect(Collectors.toCollection(LinkedHashSet::new));
        ufs.setUfs(ufList);

        logger.info("Escribir un algoritmo para complementar los valores de UF para las fechas faltantes en la " +
                "lista contenidas en la clase Ufs que retorna getRango");
        logger.info("Para complementar los valores de UF se pueden utilizar los métodos getUf y getUfs de la " +
                "clase com.previred.desafio.tres.uf.DatosUf.");
        logger.debug(gson.toJson(ufs));
    }
}
