package com.app;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.previred.desafio.tres.uf.DatosUf;
import com.previred.desafio.tres.uf.Valores;
import com.previred.desafio.tres.uf.vo.Uf;
import com.previred.desafio.tres.uf.vo.Ufs;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.logging.Logger;
import java.util.stream.Collectors;


public class Main {
    /**
     * @param args the command line arguments
     */
    /*Iniciamos parametros de manera global
    */
    private static DatosUf datosUf ;
    public static final Logger LOGGER = Logger.getLogger("");;
    public static Valores valores;
    public static Calendar c;
    public static void main(String[] args) {
        System.out.println("Iniciando....");
        /* Calendar para fechas, Date obsoleto pero servible*/
         c = Calendar.getInstance();
        /*Iniciando Singleton DatosUf*/
        datosUf =DatosUf.getInstance();
        /*Iniciando Valores*/
        valores=new Valores();
        /*Tomando Rango Aletorio de valores y ordenando*/
        Ufs rangoClass=valores.getRango();
        /* buscamos los rangos de lagunas en la lista de valores*/
        Set<Uf> list_toFile=getFaltantesUf(rangoClass);
        /*añadimos la nueva lista a la lista actual*/
        rangoClass.setUfs(list_toFile);
        /*Tomamos la ruta de la carpeta de usuario, luego verificamos el sistema para guardar el archivo en la carpeta escritorio del usuario */
        String PathCarpetaUsuario=System.getProperty("user.home");
        if (System.getProperty("os.name").toLowerCase().contains("windows".toLowerCase()))
        {
            PathCarpetaUsuario+="/Desktop";
        } 
        
        /*Generamos el archivos y escribimos el Json en el*/
        File textFile = new File(PathCarpetaUsuario, "valores.json");
        System.out.println("generando archivo JSON");
        try {
            try (BufferedWriter out = new BufferedWriter(new FileWriter(textFile))) {
                out.write(UftoGson(rangoClass));
            }
        }catch(IOException ex){LOGGER.warning(ex.toString());}
        
    }
    private static List orderByDateUfs(Set<Uf> ufs){
        /*
        Funcion que recibe  Ufs generado por la clase Valores de Dependencia Jar, el cual contiene un Set de Ufs
        luego lo transforma en una lista para poder recorrer con Colectors.
        y comparar por fechas, luego se retorna un Set para mantener el formato.
        
        */
        System.out.println("Ordenando y transformado Set a List de Ufs");
        List<Uf> ufsOrdenados = ufs.stream().collect(Collectors.toList());
        Collections.sort(ufsOrdenados, (uf1, uf2) -> uf1.getFecha().compareTo(uf2.getFecha()));
        return ufsOrdenados;
    }
    private static Set getFaltantesUf(Ufs rango){
        /* Iniciamos una lista de clase RangeDatesPair la cual solo contiene los rangos de fechas 
           para poder hacer las consultas por rango de fechas o por una fecha especifica. ademas indicamos si se le entrega un null
           no guarda ningun valor.
           
        */
        List<RangeDatesPair> Range_Dates= new ArrayList<RangeDatesPair>(){

            @Override
            public boolean add(RangeDatesPair s ) {
                if( s != null ) {
                    return super.add( s );
                }
                return false;
            }
        };
        /*Ordena los set por fecha para tomar las lagunas faltantes*/
        List<Uf> list_uf= orderByDateUfs(rango.getUfs());
        for (int i=0;i<list_uf.size();i++)
        {
            if (i+1<list_uf.size())
            {   
                if (i==0)
                {
                    Range_Dates.add(compareDates(rango.getInicio(),list_uf.get(0).getFecha(),"bounds"));
                }
                Range_Dates.add(compareDates(list_uf.get(i).getFecha(),list_uf.get(i+1).getFecha(),""));
            }
            else
            {
 
                Range_Dates.add(compareDates(list_uf.get(i).getFecha(),rango.getFin(),"bounds"));
            }
        }
        /* se recorre las clases de rangos de lagunas para pedir los datos*/
        Range_Dates.forEach((item)->{
            if (item.getFin()==null)
            {
                list_uf.add(datosUf.getUf(item.getIn()));
            }
            else{
                list_uf.addAll(datosUf.getUfs(item.getIn(),item.getFin()));
            }

        });
        /* se transforma la lista en set para eliminar cualquier coincidencia repetida*/
        Set<Uf> hSet = new HashSet(list_uf);
              

        return hSet;

    }
    public static RangeDatesPair compareDates(Date in, Date fin,String t){
        /*Se comparan las fechas de las lagunas en caso de que solo haya que tomar un dato*/

        if (in.compareTo(fin)<0)
        {
            return new RangeDatesPair(in,fin);
        }

            return null;

            
    }
    public static String UftoGson(Ufs ufs) {
        /*Iniciamos un Gson object para poder hacer la transformacion de las clases y los map a json
        se usa LinkedHashMap para mantener el orden de los map ingresados.
        */
	final Gson gson = new GsonBuilder().setPrettyPrinting().create();
        Map<String, Object> map = new LinkedHashMap<>();
        List<Map> JsonMapedList=new ArrayList<>();
        List<Uf> orderer_list=orderByDateUfs(ufs.getUfs());
        /*Añadimos map en una lista para poder hacer la transformacion completa siguiendo lo solicitado en los requerimientos */
        orderer_list.forEach((item)-> {JsonMapedList.add(formatedUf(item));});
        /*añadimos elementos al map principal*/
        map.put("inicio",new SimpleDateFormat("yyyy-dd-MM").format(ufs.getInicio()));
        map.put("fin",new SimpleDateFormat("yyyy-dd-MM").format(ufs.getFin()));
        map.put("UFs",JsonMapedList);
        /*generamos el map a json y lo devolvemos*/
	String fortmatedString = gson.toJson(map);
	return fortmatedString;
        
    }
    public static Map formatedUf(Uf uf) {
        /* mapa formateado de la uf*/
        Map<String, String> UfMap = new LinkedHashMap<>();
        UfMap.put("fecha",new SimpleDateFormat("yyyy-dd-MM").format(uf.getFecha()));
        UfMap.put("dato",uf.getValor().toString().replace(",","."));
        return UfMap;
      }
   
}
