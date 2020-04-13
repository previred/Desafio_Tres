package util;

import com.previred.desafio.tres.uf.DatosUf;
import com.previred.desafio.tres.uf.vo.Uf;

import model.UfSalida;
import model.UfsSalida;
import java.text.SimpleDateFormat;
import java.util.*;

//archivo Json
import java.io.FileWriter;
import java.io.IOException;
import com.google.gson.Gson;

public class Utiles {

    public static String fechaEnString(Date date){
        return new SimpleDateFormat("yyyy-MM-dd").format(date);
    }

    public static Map<String, Uf> setUfToHashmap(Set<Uf> setUfs ){

        Map<String, Uf> hashMapUf = new HashMap<>();

        for( Uf uf : setUfs    ) {
            hashMapUf.put(  fechaEnString( uf.getFecha() ), uf );
        }
        return hashMapUf;
    }

    public static Long definirRangoDias( Date fechaInicio, Date fechaFin) {

        Long rango = 0L;
        rango = ( fechaFin.getTime() - fechaInicio.getTime() ) / ( 86400000 );

        return rango;
    }

    public static List<model.Uf> getlistaUFsalida(Date fechaComienzo,
                                                  Long diasRango,
                                                  Map<String, Uf> ufMapListado){

        List<model.Uf> listaUFsalida = new ArrayList<model.Uf>();

        Calendar calendar = Calendar.getInstance();
        DatosUf datosUf =  DatosUf.getInstance();

        for( int i = 0; i <= diasRango; i++ ) {
            UfSalida ufSalida = new UfSalida();

            calendar.setTime( fechaComienzo );
            calendar.add(Calendar.DATE, i );
            ufSalida.setFechaUf( calendar.getTime()  );
            ufSalida.setFechaUfStr( fechaEnString( calendar.getTime() ) );

            if( ufMapListado.get(  ufSalida.getFechaUfStr()) != null  ) {
                ufSalida.setValorUf(  ufMapListado.get(  ufSalida.getFechaUfStr()).getValor()       );
            }else {
                ufSalida.setValorUf( datosUf.getUf( ufSalida.getFechaUf() ).getValor() );
            }

            model.Uf uf = new model.Uf();
            uf.setDato(Double.toString(ufSalida.getValorUf()));
            uf.setFecha(ufSalida.getFechaUfStr());

            listaUFsalida.add(uf);
        }

        return listaUFsalida;
    }

    public static void generarArchivoUFJson(UfsSalida ufsSalida){
        Gson gson = new Gson();
        String jsonStr = gson.toJson(ufsSalida);

        try {

            FileWriter file = new FileWriter("./export/valores.json");
            file.write(jsonStr);
            file.flush();
            file.close();

            System.out.println("Se generó exitosamente archivo Json en carpeta: /export");

        } catch (IOException e) {
            System.out.println("[Utiles.generarArchivoUFJson()] Error al generar ArchivoUFJson, : "+e);
        }
    }

}
