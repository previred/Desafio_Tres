package cl.previred;

import cl.previred.domain.UfOutput;
import cl.previred.domain.UfsOutput;
import cl.previred.utils.OutputFileMaker;
import cl.previred.utils.Utils;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.previred.desafio.tres.uf.DatosUf;
import com.previred.desafio.tres.uf.Valores;
import com.previred.desafio.tres.uf.vo.Uf;
import org.apache.log4j.Logger;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

import java.util.stream.Collectors;

public class Application {

    private static final String JSON_FILE_NAME = "valores.json";
    private static final Logger logger = Logger.getLogger(Application.class);

    public static void main(String[] args) {

        Valores valores = new Valores();
        DatosUf datosUf = DatosUf.getInstance();

        Set<Uf> customListUf = valores.getRango().getUfs();

        Date firstDate = customListUf.stream().min(Comparator.comparing(Uf::getFecha)).get().getFecha();
        Date lastDate = customListUf.stream().max(Comparator.comparing(Uf::getFecha)).get().getFecha();

        logger.info("The request was between " + Utils.dateFormat(firstDate) + " and " + Utils.dateFormat(lastDate));

        List<Uf> originListUf = datosUf.getUfs(firstDate, lastDate);


        logger.info("Filling the list with Ufs missing days...");
        //Fill list with Ufs
        for (Uf uf : originListUf) {
            if(!customListUf.contains(uf)){
                customListUf.add(uf);
            }
        }

        String jsonString = fillOutputJsonObject(firstDate, lastDate,
                customListUf.stream().sorted(Comparator.comparing(Uf::getFecha).reversed()).collect(Collectors.toList()));

        OutputFileMaker.makeJsonFile(jsonString, JSON_FILE_NAME);

        logger.info("Process finished.");

        BufferedReader reader =
                new BufferedReader(new InputStreamReader(System.in));
        try {
            System.out.println("Press any key to exit...");
            reader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private static String fillOutputJsonObject(Date firstDate, Date lastDate, List<Uf> customListUf){
        logger.info("Making Json Object...");
        UfsOutput ufs = new UfsOutput();
        ufs.setInicio(Utils.dateFormat(firstDate));
        ufs.setFin(Utils.dateFormat(lastDate));

        List<UfOutput> outputsUf = new ArrayList<>();
        for (Uf uf :  customListUf
        ) {
            outputsUf.add(new UfOutput(Utils.dateFormat(uf.getFecha()),Utils.ufAmountFormat(uf.getValor())));
        }
        ufs.setUFs(outputsUf);
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        logger.debug("The Json Object created is: ");
        logger.debug(gson.toJson(ufs));

        logger.info("Json Object created.");
        return gson.toJson(ufs);
    }
}
