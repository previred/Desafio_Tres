package com.ricardoc333.desafio.tres;

//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

//package com.previred.desafio.tres.uf;

import com.ricardoc333.desafio.tres.DatosUf;
import com.ricardoc333.desafio.tres.RandomDate;
import com.ricardoc333.desafio.tres.Ufs;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.Random;

public class Valores {
    private static final int MIN = 90;
    private static final int MAX = 100;

    public Valores() {
    }

    public Ufs getRango() {
        RandomDate inicio = new RandomDate(LocalDate.of(2010, 1, 1), LocalDate.of(2012, 4, 1));
        RandomDate fin = new RandomDate(LocalDate.of(2012, 10, 1), LocalDate.of(2014, 1, 1));
        Ufs ufs = new Ufs();
        LocalDate fechaInicio = inicio.nextDate();
        LocalDate fechaFin = fin.nextDate();
        ufs.setInicio(Date.from(fechaInicio.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant()));
        ufs.setFin(Date.from(fechaFin.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant()));
        RandomDate fechaPeriodos = new RandomDate(fechaInicio, fechaFin);
        Random aleatorio = new Random();
        int cantidadPeriodos = aleatorio.nextInt(11) + 90;

        while(ufs.getUfs().size() <= cantidadPeriodos) {
            Date fechaFiltro = Date.from(fechaPeriodos.nextDate().atStartOfDay().atZone(ZoneId.systemDefault()).toInstant());
            ufs.getUfs().add(DatosUf.getInstance().getUf(fechaFiltro));
        }

        return ufs;
    }
}
