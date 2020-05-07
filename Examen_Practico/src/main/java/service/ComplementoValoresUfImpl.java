package service;

import Dto.JsonSalida;
import Dto.UfdeSalida;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.previred.desafio.tres.uf.DatosUf;
import com.previred.desafio.tres.uf.Valores;
import com.previred.desafio.tres.uf.vo.Uf;
import com.previred.desafio.tres.uf.vo.Ufs;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.TimeUnit;

public class ComplementoValoresUfImpl implements ComplementoValoresUf {


    /**Metodo principal encargado de orquestar al resto de metodos
     * que generan el Json de Salida con fechas y montos de Ufs
     * correspondientes a la fecha respectiva **/

    public void obtenerRangosFecha() throws ParseException {

        Valores valores = new Valores();

        Ufs datosUf = valores.getRango();
        Date fechaInicio = datosUf.getInicio();
        Date fechaFin = datosUf.getFin();
        Set valorUf = datosUf.getUfs();

        String fechaInicioString = dateToString(fechaInicio);
        String fechaFinString = dateToString(fechaFin);

        List<String> rangoDeFechasAbuscar = buscarRangoFechas(fechaInicio, fechaFin);

        List<String> rangoDeFechasConUf = obtenerFechasConUf(valorUf);

        List<String> valoresAbuscarUf = new ArrayList<String>();

        valoresAbuscarUf = removerDuplicados(rangoDeFechasAbuscar, rangoDeFechasConUf);

        List<Uf> listadoFinal = completarUF(valoresAbuscarUf);

        List <UfdeSalida> resultado = UfDateToString (listadoFinal);

        generaJSON(resultado,fechaInicioString, fechaFinString);

    }

    /**Metodo encargado de la conversion de datos de tipo Date a String**/

    public String dateToString(Date fechaAconvertir) throws ParseException {
        SimpleDateFormat formateador = new SimpleDateFormat( "yyyy-MM-dd");
        String fechaString = formateador.format(fechaAconvertir);

        return fechaString;

    }


    /**metodo encargado de generar un rango de fechas,
     * a partir de una fecha de inicio y fin, que fueron generadas
     * de manera aleatoriaL**/

    public List <String> buscarRangoFechas(Date fechaInicio, Date fechaFin) throws ParseException {

        List <String> calendario = new ArrayList<String>();

        while (fechaInicio.compareTo(fechaFin)!=0){
            if (calendario.size()==0){
                fechaInicio = new Date(fechaInicio.getTime());
                String fechaInicioString = dateToString(fechaInicio);
                calendario.add(fechaInicioString);
            } else {
                fechaInicio = new Date(fechaInicio.getTime()+ TimeUnit.DAYS.toMillis(1));
                String fechaInicioString = dateToString(fechaInicio);
                calendario.add(fechaInicioString);
            }

        }
        return calendario;
    }


    /**Metodo encargado de ir a buscar el set de datos con fechas y montos de UFs **/

    public List <String> obtenerFechasConUf(Set valorUf) throws ParseException {

        List <String> listaFechas = new ArrayList<String>();

        for (Iterator iterador = valorUf.iterator(); iterador.hasNext() ; ){

            Uf contador = (Uf)iterador.next();

            System.out.println(contador.getValor());
            System.out.println(contador.getFecha());
            Date fecha = contador.getFecha();
            String fechaString = dateToString(fecha);

            listaFechas.add(fechaString);
        }

        return listaFechas;
    }



    /**Metodo encargado de hacer el cruce de datos entre el set de datos obtenidos en
     * el metodo obtenerFechasConUf , contra el rango de fecha originado en el metodo
     * buscarRangoFechas, y con ello generar un listado final de fechas para posteriormente
     * buscar el valor de Uf respectivo **/

    public List <String> removerDuplicados(List<String> rangoFechas, List<String> listaFechas){

        rangoFechas.removeAll(listaFechas);
        Collections.sort(rangoFechas);

        return rangoFechas;
    }


    /**Metodo que recibe un objeto valoresAbuscarUf,que contiene el listado de Fechas en tipo String y
     *  monto Uf en tipo Double, y que devuelve un objeto con los mismos valores de entrada,
     *  pero esta vez  la fecha de tipo Date y un valor Uf tipo Double**/

    public List <Uf> completarUF(List <String> valoresAbuscarUf) throws ParseException {

        Uf newList = new Uf();
        List <Uf> listaDeUf = new ArrayList<>();

        for (int i =0; i< valoresAbuscarUf.size(); i++){
            try{
                Date valorSinUf = stringToDate(valoresAbuscarUf.get(i));
                newList= DatosUf.getInstance().getUf(valorSinUf);
            }
            catch (ParseException e){

            }
            listaDeUf.add(newList);

        }
        return listaDeUf;
    }

    /**Metodo que se encarga de aplicar el formato del Dto de Salida, para posteriormente
     * poder generar el Json de Salida**/

    public List<UfdeSalida> UfDateToString (List<Uf> listadoFinal) throws ParseException {

        List<UfdeSalida> listaDeSalida = new ArrayList<>();
        UfdeSalida ufdeSalida ;

        for (int i =0; i< listadoFinal.size(); i++){
            ufdeSalida = new UfdeSalida();

            String fechaFinal = dateToString(listadoFinal.get(i).getFecha());
            Double valorUf = listadoFinal.get(i).getValor();

            ufdeSalida.setFecha(fechaFinal);
            ufdeSalida.setDato(valorUf);

            listaDeSalida.add(ufdeSalida);
        }

        return listaDeSalida;

    }

    /**Metodo encargado de generar el objeto final en formato JSON**/

    public void generaJSON(List <UfdeSalida> resultado, String fechaInicio, String fechaFin) {

        final Gson json = new GsonBuilder().setPrettyPrinting().create();

        JsonSalida jsonSalida = new JsonSalida();
        jsonSalida.setInicio(fechaInicio);
        jsonSalida.setFin(fechaFin);
        jsonSalida.setUFs(resultado);

        final String formatJson = json.toJson(jsonSalida);
        System.out.println(formatJson);

    }


    /**Metodo auxiliar encargado de convertir datos de tipo String a Date**/

    public Date stringToDate (String fechaAconvertir) throws ParseException {
        SimpleDateFormat formateador = new SimpleDateFormat( "yyyy-MM-dd");

        Date fechaDate = formateador.parse(fechaAconvertir);

        return fechaDate;
    }


}
