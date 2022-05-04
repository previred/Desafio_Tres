package cl.previred;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.previred.desafio.tres.uf.DatosUf;
import com.previred.desafio.tres.uf.Valores;
import com.previred.desafio.tres.uf.vo.Uf;
import com.previred.desafio.tres.uf.vo.Ufs;

import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;

public class PruebaApplication {

    public static void main(String... args){
        Valores valores = new Valores();
        Ufs ufs = valores.getRango();
        Set<Uf> ufSet = ufs.getUfs();
        // Se agregan los valores faltantes de ufs
        ufSet.addAll(DatosUf.getInstance().getUfs(ufs.getInicio(),ufs.getFin()));
        ufs.setUfs(ufSet);
        imprimirJsonInArchive(convertirValoresToJson(ufs),"valores.json");

    }

    public static JsonObject convertirValoresToJson(Ufs ufs){
        JsonObject jsonUfs = new JsonObject();
        jsonUfs.addProperty("inicio",convertirFormatoFecha(ufs.getInicio()));
        jsonUfs.addProperty("fin", convertirFormatoFecha(ufs.getFin()));

        JsonArray jsonArray = new JsonArray();

        for ( Uf uf : ufs.getUfs() ) {
            JsonObject jsonObject = new JsonObject();
            jsonObject.addProperty("fecha",convertirFormatoFecha(uf.getFecha()));
            jsonObject.addProperty("dato",convertirFormatoValor(uf.getValor()));
            jsonArray.add(jsonObject);
        }

        jsonArray = ordenarByFecha(jsonArray);

        jsonUfs.add("UFs",jsonArray);

        return jsonUfs;

    }

    public static JsonArray ordenarByFecha(JsonArray jsonArrayUfs){

        JsonArray sortedJsonArray = new JsonArray();

        List<JsonObject> jsonValues = new ArrayList<>();
        for (int i = 0; i < jsonArrayUfs.size(); i++) {
            jsonValues.add((JsonObject) jsonArrayUfs.get(i));
        }
        Collections.sort( jsonValues, new Comparator<JsonObject>() {
            //You can change "Name" with "ID" if you want to sort by ID
            private static final String KEY_NAME = "fecha";

            @Override
            public int compare(JsonObject a, JsonObject b) {
                String valA = "";
                String valB = "";

                try {
                    valA = a.get(KEY_NAME).getAsString();
                    valB = b.get(KEY_NAME).getAsString();
                }
                catch (Exception e) {
                    e.printStackTrace();
                }

                return -valA.compareTo(valB);
            }
        });

        for (int i = 0; i < jsonValues.size(); i++) {
            sortedJsonArray.add(jsonValues.get(i));
        }

        return sortedJsonArray;
    }

    public static String convertirFormatoFecha(Date fecha){
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        return formatter.format(fecha);
    }

    public static String convertirFormatoValor(Double valor){
        String replacePunto = valor.toString().replace(".",",");
        String[] split = replacePunto.split(",");
        String number = split[0];
        int i = number.length();
        if(i > 3){
            String result = "";
            while (i > 3) {
                result = "." + number.substring(i-3,i) + result;
                i = i-3;
            }
            return number.substring(0,i)+result+","+split[1];
        }
        return replacePunto;
    }

    public static void imprimirJsonInArchive (JsonObject jsonObject, String filename){
        try(FileWriter fileWriter = new FileWriter(filename)) {
            fileWriter.write(jsonObject.toString());
            fileWriter.flush();
        } catch (IOException e){
            e.printStackTrace();
        }
    }
}
