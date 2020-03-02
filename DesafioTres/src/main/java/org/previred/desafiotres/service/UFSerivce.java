package org.previred.desafiotres.service;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.previred.desafio.tres.uf.DatosUf;
import com.previred.desafio.tres.uf.Valores;
import com.previred.desafio.tres.uf.vo.Uf;
import com.previred.desafio.tres.uf.vo.Ufs;
import one.util.streamex.StreamEx;
import org.previred.desafiotres.model.UFfinal;
import org.previred.desafiotres.model.Ufdato;
import org.previred.desafiotres.utils.Config;
import org.previred.desafiotres.utils.Utils;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class UFSerivce {

    public String getUfRange() {
        Valores valores = new Valores();
        Ufs rango = valores.getRango();

        UFfinal uFfinal = new UFfinal();
        Gson gson = new GsonBuilder().setDateFormat(
                Config.DATE_FORMAT
        ).setPrettyPrinting().create();

        List<Uf> add = new ArrayList<>();

        uFfinal.setInicio(rango.getInicio());
        uFfinal.setFin(rango.getFin());

        List<Uf> orderList = rango.getUfs().stream().sorted(
                Comparator.comparing(Uf::getFecha))
                .collect(Collectors.toList());

        Stream.of(rango.getFin(), rango.getInicio()).forEach(
                date -> {
                    if (!Utils.containDate(orderList, date)) {
                        orderList.add(DatosUf.getInstance().getUf(date));
                    }
                });


        StreamEx.of(orderList.stream()).forPairs((current, next) -> {
            long dif = Utils.difer(current.getFecha(), next.getFecha());
            if (dif == 2) {
                add.add(
                        DatosUf.getInstance().getUf(
                                Utils.sumDays(current.getFecha(), 1)
                        )
                );
            } else if (dif > 2) {

                add.addAll(
                        DatosUf.getInstance().getUfs(
                                Utils.sumDays(current.getFecha(), 1),
                                Utils.sumDays(
                                        current.getFecha(), (dif - 1)
                                ))
                );
            }
        });

        StreamEx.of(orderList.stream())
                .append(add.stream())
                .sorted(
                        (o1, o2) -> o2.getFecha().compareTo(o1.getFecha())
                ).forEach(
                uf -> uFfinal.getUFs().add(
                        Ufdato.builder().dato(uf.getValor())
                                .fecha(uf.getFecha()).build()
                )
        );

        return gson.toJson(uFfinal);
    }
}
