package desafio.dao;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.previred.desafio.tres.uf.DatosUf;
import com.previred.desafio.tres.uf.Valores;
import com.previred.desafio.tres.uf.vo.Uf;
import com.previred.desafio.tres.uf.vo.Ufs;
import desafio.enumerators.ConstantesStr;
import desafio.interfaces.IDesafio;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.logging.Logger;
import java.util.stream.Collectors;

public class Desafio implements IDesafio {

    private Valores valores;
    private final Logger logger = Logger.getLogger(Desafio.class.getName());

    public Desafio() {
        this.valores = new Valores();
    }

    public Logger getLogger() {
        return logger;
    }

    public Ufs getRango() {
        return this.valores.getRango();
    }

    //    FUNCION QUE SE IMPLEMENTA PARA COMPLETAR HASTA EL RANGO EXIGIDO LA LISTA DE UFs
    public void completeListUfs(Ufs ufs) {
        this.logger.info("OBTENIENDO LA LISTA DE UFs EXISTENTE EL REPOSITORIO CON TODOS LOS VALORES");
        List<Uf> repositoryList = DatosUf.getInstance().getUfs(ufs.getInicio(), ufs.getFin());
        this.logger.info("OBTENIENDO LA LISTA DE UFs REAL QUE SE RECIBE");
        List<Uf> actualList = ufs.getUfs().stream().collect(Collectors.toList());
        this.logger.info("OBTENIENDO LA DIFERENCIA ENTRE AMBAS LISTAS");
        repositoryList.removeAll(actualList);
        for (int i = 0; i < repositoryList.size(); i++) {
            ufs.getUfs().add(repositoryList.get(i));
        }
    }

    public List<Uf> listUfs(Ufs ufs, String order) {
        this.logger.info("LLAMANDO AL METODO QUE RELLENA LA LISTA");
        this.completeListUfs(ufs);
        this.logger.info("GUARDANDO LOS ELEMENTOS EN UNA LISTA PARA SER ORDENADA LUEGO");
        List<Uf> listUfs = ufs.getUfs().stream().collect(Collectors.toList());
        this.logger.info("ORDENANDO LA LISTA EN DEPENDENCIA DEL PARAMETRO A ORDENAR");
        if (order.equalsIgnoreCase(ConstantesStr.ORDER_VALOR.toString())) {
            Collections.sort(listUfs, (Uf s1, Uf s2) -> {
                return s2.getValor().compareTo(s1.getValor());
            });
        } else {
            Collections.sort(listUfs, (Uf s1, Uf s2) -> {
                return s2.getFecha().compareTo(s1.getFecha());
            });
        }
        return listUfs;
    }

    public String stringFromListUfs(List<Uf> listUfs) {
        String result = "";
        if (listUfs.size() > 0) {
            String dateIni;
            String dateFin;
            String date;
            String valueStr;
            DateFormat format = new SimpleDateFormat(ConstantesStr.FORMAT_FECHA.toString());
            dateFin = format.format(listUfs.get(0).getFecha());
            dateIni = format.format(listUfs.get(listUfs.size() - 1).getFecha());
            result += "{\"inicio\":\"" + dateIni + "\",\"fin\":\"" + dateFin + "\",\"UFs\":[";
            for (Uf uf : listUfs) {
                date = format.format(uf.getFecha());
                valueStr = String.valueOf(uf.getValor());
                result += "{\"fecha\":\"" + date + "\",\"dato\":\"" + valueStr + "\"},";
            }
            result = result.substring(0, result.length() - 1);
            result += "]}";
        }
        return result;
    }

    public String jsonFromListUfs(List<Uf> listUfs) {
        Gson gson_pretty = new GsonBuilder().setPrettyPrinting().create();
        String result = this.stringFromListUfs(listUfs);
        JsonElement jsonElement = new JsonParser().parse(result);
        String feeds = gson_pretty.toJson(jsonElement);
        return feeds;
    }

    public void showListUfs(List<Uf> listUfs) {
        for (Uf uf : listUfs) {
            System.out.println(uf.toString());
        }
    }
}
