package com.desafio.tres.valoresuf.service.impl;

import com.desafio.tres.valoresuf.contract.to.UfTO;
import com.desafio.tres.valoresuf.contract.to.UfsTO;
import com.desafio.tres.valoresuf.service.DesafioService;
import com.previred.desafio.tres.uf.DatosUf;
import com.previred.desafio.tres.uf.Valores;
import com.previred.desafio.tres.uf.vo.Uf;
import com.previred.desafio.tres.uf.vo.Ufs;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Comparator;
import java.util.stream.Collectors;

import static com.desafio.tres.valoresuf.util.json.JsonSerdes.jsonfy;
import static java.lang.String.format;

/**
 * Implementacion de la interfaz {@link DesafioService}
 * Implementa el desafio
 *
 * @author Victor
 * @since 0.1.0
 */

@Service("desafioservice")
public class DesafioServiceImpl implements DesafioService {
    private static final Logger logger = LoggerFactory.getLogger(DesafioService.class);

    @Override
    public String desafio() {
        logger.info("Iniciando desafio");
        SimpleDateFormat formateadorFecha = new SimpleDateFormat("yyyy/MM/dd");

        Valores valores = new Valores();
        Ufs rango = valores.getRango();
        logger.info("Consumiendo la funcion getRango");
        DatosUf datosUf = DatosUf.getInstance();

        //obtener las fechas devueltas en el metodo get rango()
        var dateList = rango.getUfs().stream().map(Uf::getFecha).collect(Collectors.toList());
        logger.info("separando fechas del rango");

        //filtrando las fechas del metodo getUfs y luego agregar las fechas faltantes
        rango.getUfs().addAll(datosUf.getUfs(rango.getInicio(), rango.getFin())
                .stream()
                .filter(uf -> !dateList.contains(uf.getFecha()))
                .collect(Collectors.toSet()));
        logger.info("Obteniendo fechas faltantes con valores uf respectivos");

        //ordenar la lista resultante de forma descendente y luego mapear los datos a la salida
        var fullList = rango.getUfs().stream().sorted(Comparator.comparing(Uf::getFecha).reversed()).map(uf -> new UfTO().withFecha(formateadorFecha.format(uf.getFecha())).withDato(uf.getValor()))
                .collect(Collectors.toList());
        logger.info("Ordenando lista y preparandola para la respuesta");

        //crear el objeto de salida y luego convertirlo a json
        String result=jsonfy(new UfsTO().withFin(formateadorFecha.format(rango.getFin())).withInicio(formateadorFecha.format(rango.getInicio())).withUfs(fullList)).replace("ufs","UFs");
        logger.info("Convirtiendo el objeto de los resultados a un String con formato Json");

        //salvando archivo valores.json
        try(var fw=new FileWriter(new File("valores.json"))){
            fw.write(result);
        }catch (IOException e) {
            logger.error(format("Error al tratar de salvar el archivo: %s",e.getMessage()));
        }
        logger.info("Terminando desafio");
        return result;
    }

}
