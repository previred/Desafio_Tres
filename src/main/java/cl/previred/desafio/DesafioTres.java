package cl.previred.desafio;

import com.previred.desafio.tres.uf.vo.Uf;
import com.previred.desafio.tres.uf.Valores;
import com.previred.desafio.tres.uf.DatosUf;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import static java.util.logging.Logger.getLogger;
import java.util.stream.Collectors;
import org.joda.time.LocalDate;
import org.joda.time.Days;
import org.json.JSONObject;
import static org.joda.time.LocalDate.fromDateFields;
import org.joda.time.LocalDateTime;

/**
 *
 * @author yesenia
 */
public class DesafioTres {

    private static final DatosUf DATOS_UF = DatosUf.getInstance();
    private static final Logger LOG = getLogger(DesafioTres.class.getName());

    public static void main(String[] args) {
        LOG.info("========= Desafio tres Previred =========");
        LOG.info("    Desarrollado por: Yesenia Doria      ");
        LOG.log(Level.INFO, "Inicio de proceso: {0}", LocalDateTime.now().toString("yyyy-MM-dd HH:mm"));
        Valores valores = new Valores();

        List<Uf> listaFinal = valores.getRango().getUfs().stream().collect(Collectors.toList());
        List<Uf> list = new ArrayList<>(listaFinal);
        list.sort(Comparator.comparing(Uf::getFecha).reversed());

        completarUfs(list, listaFinal);

        /**
         * ordenamineto por fecha descendente
         */
        listaFinal.sort(Comparator.comparing(Uf::getFecha).reversed());

        JSONObject json = new JSONObject();
        JSONObject jsonL = new JSONObject();
        List<JSONObject> ufs = new ArrayList<>();

        formatearUfs(listaFinal, ufs);

        json.put("inicio", fromDateFields(listaFinal.get(listaFinal.size() - 1).getFecha()).toString("yyyy-MM-dd"));
        json.put("fin", fromDateFields(listaFinal.get(0).getFecha()).toString("yyyy-MM-dd"));

        jsonL.put("UFs", ufs);

        /**
         * Se concatena objetos individuales debido a que el json final no
         * conserva orden establecido en el formato solicitado estando juntos
         */
        String finalJson = json.toString().substring(0, json.toString().length() - 1).concat(",").concat(jsonL.toString().substring(1));

        escribirFichero(finalJson);
        LOG.info("Finalizado correctamente");
    }

    /**
     * Escribe fichero JSON
     * @param finalJson 
     */
    private static void escribirFichero(String finalJson) {
        try (FileWriter file = new FileWriter("valores.json")) {
            file.write(finalJson);
            file.flush();
        } catch (IOException e) {
            getLogger("log").log(Level.SEVERE, "Ha ocurrido un error con la creaci\u00F3n del fichero: {0}", e.getMessage());
        }
    }

    /**
     * Iteracion para lograr renombrar los nombres de los fields por los
     * indicados en el formato de salida
     * @param listaFinal
     * @param ufs 
     */
    private static void formatearUfs(List<Uf> listaFinal, List<JSONObject> ufs) {
        listaFinal.stream().map((uf) -> {
            JSONObject k = new JSONObject();
            k.put("fecha", fromDateFields(uf.getFecha()).toString("yyyy-MM-dd"));
            k.put("dato", uf.getValor());
            return k;
        }).forEachOrdered((k) -> {
            ufs.add(k);
        });
    }

    /**
     * Relleno de fechas ausentes
     * @param list
     * @param listaFinal 
     */
    private static void completarUfs(List<Uf> list, List<Uf> listaFinal) {

        for (int i = 0; i < list.size() - 1; i++) {
            LocalDate current = fromDateFields(list.get(i).getFecha());
            LocalDate before = fromDateFields(list.get(i + 1).getFecha());

            int diff = Days.daysBetween(before, current).getDays();
            LocalDate initial = current.minusDays(diff - 1);

            List<Uf> missings = DATOS_UF.getUfs(initial.toDate(), current.minusDays(1).toDate());
            listaFinal.addAll(missings);

        }
    }

}
