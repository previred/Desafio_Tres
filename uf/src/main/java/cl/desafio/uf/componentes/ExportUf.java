package cl.desafio.uf.componentes;

import cl.desafio.uf.entidades.UFValor;
import cl.desafio.uf.entidades.UFsRango;
import cl.desafio.uf.utilidades.Constantes;
import cl.desafio.uf.utilidades.Util;
import com.google.gson.*;
import org.springframework.stereotype.Component;

import java.io.FileWriter;
import java.io.IOException;


/**
 * Created by APIUXNB531 on 6/3/2020.
 */
@Component
public class ExportUf {

    /**
     *
     * @param rangoUf
     * @return
     */
    public String formatoJson(final UFsRango rangoUf) {
        StringBuilder  ufsJson = new StringBuilder();
        ufsJson.append("{\"inicio\":\"");
        ufsJson.append(Util.fechaString(rangoUf.getInicio(), Constantes.FORMAT_DATE));
        ufsJson.append("\",\"fin\":\"");
        ufsJson.append(Util.fechaString(rangoUf.getFin(),Constantes.FORMAT_DATE));
        ufsJson.append("\",\"UFs\":[");
        for (UFValor ufValor : rangoUf.getListaUFs()){
            ufsJson.append("{\"fecha\":\"");
            ufsJson.append(Util.fechaString(ufValor.getFecha(),Constantes.FORMAT_DATE));
            ufsJson.append("\",\"valor\":\"");
            ufsJson.append(Util.doubleString(ufValor.getValor(),Constantes.FORMAT_NUMBER));
            ufsJson.append("\"},");
        }
        ufsJson.append("]}");
       return  ufsJson.toString();
    }

    /**
     *
     * @param jsonFormat
     */
    public void exportarJson(final String jsonFormat){
        JsonObject jsonObject = new JsonParser().parse(jsonFormat).getAsJsonObject();
        GsonBuilder builder = new GsonBuilder();
        builder.setPrettyPrinting();
        Gson gson = builder.create();
        try (FileWriter writer = new FileWriter("valores.json")){
            gson.toJson(jsonObject, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
