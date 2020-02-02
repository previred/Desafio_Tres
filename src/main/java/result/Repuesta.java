package result;

import java.io.FileWriter;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.google.gson.Gson;
import com.previred.desafio.tres.uf.DatosUf;
import com.previred.desafio.tres.uf.Uf2;
import com.previred.desafio.tres.uf.Valores;
import com.previred.desafio.tres.uf.vo.Uf;
import com.previred.desafio.tres.uf.vo.Ufs;
import java.io.File;

public class Repuesta {

    public static void main(String[] args) {
        algoritmo();
    }

    public static void algoritmo() {
        //Instancia Valores
        Valores valores = new Valores();
        //Llamada al objeto Singleton
        DatosUf datosUf = DatosUf.getInstance();
        Ufs ufs = valores.getRango();
        Date FechaFinal = ufs.getFin();
        Date FechaInicial = ufs.getInicio();
        Set<Uf> resultUfs = ufs.getUfs();
        FileWriter fichero = null;
        PrintWriter pw = null;
        String filePath = new File("").getAbsolutePath();
        /*instancio un objeto tipo calendario singleton*/
        Calendar c = Calendar.getInstance();
        Gson gson = new Gson();
        /*parseador de fecha*/
        SimpleDateFormat formateador = new SimpleDateFormat("YYYY-MM-dd");
        /*lista de objetos tipo UF2*/
        List<Uf2> dataList = new LinkedList<Uf2>();
        /*instancia de objeto UF*/
        Uf uf = new Uf();
        /*fecha sera el objeto que recorrera el intervalo de tiempo preguntando si existe en el 
         conjunto de resultados de Valores, en caso contrario obtendra los valores del resultado
         del objeto Rango
         */
        Date fecha = new Date();
        //Listado entregado por la clase Valores
        List<Uf> results = resultUfs.stream().collect(Collectors.toList());
        Collections.sort(results, Comparator.comparing(Uf::getFecha));
        //Rango con fecha y valores para todo el conjunto requerido
        List<Uf> ufsRango = datosUf.getUfs(FechaInicial, FechaFinal);
        /*Inicializo en la fecha inicial*/
        fecha = FechaInicial;

        /*ciclo con condición que la fecha este entre el rango de definido por Valores*/
        while (fecha.compareTo(FechaInicial) >= 0 && fecha.compareTo(FechaFinal) <= 0) {
            /*Se consulta si la fecha esta dentro de los resultados de valores, si
             la respuesta es correcta lo obtengo*/
            if (buscarUf(resultUfs, fecha)) {
                uf = buscarUfValor(results, fecha);
                /*lo agrego a esta lista para luego para construir el json*/
                dataList.add(new Uf2(String.valueOf(formateador.format(uf.getFecha())), String.valueOf(uf.getValor())));
            } /*en caso contrario lo voy a buscar a la lista del objeto Rango */ else {
                uf = buscarUfValor(ufsRango, fecha);
                /*lo agrego a esta lista para luego para construir el json*/
                dataList.add(new Uf2(String.valueOf(formateador.format(uf.getFecha())), String.valueOf(uf.getValor())));
            }

            c.setTime(fecha);
            /*le agrego un dia */
            c.add(Calendar.DATE, 1);
            c.set(Calendar.HOUR_OF_DAY, 0);

            // Convierto el resultado de calendario en fecha*/
            fecha = c.getTime();

        }
        //Formatea fechas YYYY-mm-dd
        String fechaINI = formateador.format(FechaInicial);
        String fechaFIN = formateador.format(FechaFinal);

        //*construye el json con la libreria gson*/
        String json = gson.toJson(dataList);
System.out.println(json);
        /*agrega claves al json*/
        json = "{\r\n"
                + "  \"inicio\":\"" + fechaINI + "\",\r\n"
                + "  \"fin\":\"" + fechaFIN + "\",\r\n"
                + "  \"UFs\":" + json + "\r\n"
                + "}\r\n";

        /*path absoluto para imprimir el archivo*/

        /*reemplaza valor por dato en el json*/
        json = json.replace("valor", "dato");
        try {
            fichero = new FileWriter(filePath + "//resultado//valores.json");
            pw = new PrintWriter(fichero);
            pw.println(json);

        } catch (Exception e) {
        } finally {
            try {
                // Nuevamente aprovechamos el finally para 
                // asegurarnos que se cierra el fichero.
                if (null != fichero) {
                    fichero.close();
                }
            } catch (Exception e2) {
            }
        }
    }

    /*buscar si la fecha de la uf se encuentra en el objeto Set*/
    public static boolean buscarUf(Set<Uf> ufs, Date fecha) {
        return ufs.stream()
                .anyMatch(uf -> uf.getFecha().compareTo(fecha) == 0);
    }

    /*buscar una fecha en una lista de Uf retorna el objeto uf si lo encuentra*/
    static public Uf buscarUfValor(List<Uf> lista, Date fecha) {
        Uf resp = new Uf();
        for (Uf lista1 : lista) {
            if (lista1.getFecha().equals(fecha)) {
                resp = lista1;
                break;
            }
        }
        return resp;
    }
}
