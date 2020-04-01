package org.previred;

import com.google.gson.Gson;

import com.google.gson.JsonArray;
import com.previred.desafio.tres.uf.DatosUf;
import com.previred.desafio.tres.uf.Valores;
import com.previred.desafio.tres.uf.vo.Uf;
import com.previred.desafio.tres.uf.vo.Ufs;
import java.io.BufferedWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Hello world!
 *
 */
public class App
{

    public static void main(String[] args )
    {

        //OBTENEMOS LOS VALORES DE LA LIBRERÍA
        //com.previred.desafio.tres.uf.Valores
        Valores valores = new Valores();
        //OBTENEMOS LAS UFS DE LOS VALORES
        //SE CONSUME LA FUNCIÓN GET RANGO
        Ufs ufs = valores.getRango();
        //OBTENEMOS FECHAS DE INICIO Y FIN DE LOS VALORES REGISTRADOS
        Date fechaInicio = ufs.getInicio();
        Date fechaFin = ufs.getFin();
        //OBTENEMOS EL SET DE UF
        Set<Uf> listaUfs = ufs.getUfs();

        //OBTENEMOS LA LISTA DE DATOS DE LAS UFS DE LAS FECHAS OBTENIDAS
        List<Uf> datosUf = DatosUf.getInstance().getUfs(fechaInicio, fechaFin);





        try {

            BufferedWriter writer = Files.newBufferedWriter(Paths.get("valores.json"));
            // OBTENEMOS EL FORMATO DE LAS FECHAS
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            //CREAMOS EL OBJETO A AGREGAR AL JSON
            Map<String, Object> UFS = new HashMap<>();
            //CREAMOS UNA LISTA DE OBJETOS JSON PARA AGREGAR LOS DATOS UF
            ArrayList ufsArray = new ArrayList();
            //RENOVAMOS LA LISTA DE UFS
            listaUfs = new HashSet<Uf>();
            for(int i=0;i<datosUf.size();i++){
                //OBTENEMOS EL DATO DE LA UF
                Uf ufObtenida = datosUf.get(i);
                //AGREGAMOS EL OBJETO AL SET DE UFS
                listaUfs.add(ufObtenida);
                //IMPRIMIMOS AL JSON LOS DATOS DE LISTA
                Map<String, Object> nuevaUfJson = new HashMap<>();
                nuevaUfJson.put("fecha", formatter.format(ufObtenida.getFecha()));
                nuevaUfJson.put("dato", ufObtenida.getValor());
                ufsArray.add(nuevaUfJson);
            }
            //AGREGAMOS LOS DATOS AL JSON

            String fechaTexto = formatter.format(fechaInicio);
            UFS.put("inicio", fechaTexto);
            fechaTexto = formatter.format(fechaFin);
            UFS.put("fin", fechaTexto);
            UFS.put("UFs",ufsArray.toArray());


            // SE CREA EL ARCHIVO JSON CON GSON
            Gson gson = new Gson();
            writer.write(gson.toJson(UFS));
            writer.close();

        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }
}

