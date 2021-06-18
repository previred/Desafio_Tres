package com.desafio3.valoresuf.datosuf;

import com.previred.desafio.tres.uf.DatosUf;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Date;

public class DatosUfTests {

    @Test
    public void validateUfByFecha () {

        //3rd party JAR, Third-party JAR, instantiate DatosUf class
        DatosUf datosUf = DatosUf.getInstance();

        Date fecha = new Date(113, 9, 14);
        Assertions.assertEquals(23123.47, datosUf.getUf(fecha).getValor());
    }
}
