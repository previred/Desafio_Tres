package principal;

import com.previred.desafio.tres.uf.*;
import com.previred.desafio.tres.uf.vo.Uf;
import com.previred.desafio.tres.uf.vo.Ufs;

import java.util.*;
import model.UfsSalida;
import util.Utiles;

public class Principal {

    public static void main( String[] args ) {

        Valores valores = new Valores();
        Ufs cargaUFs = valores.getRango();
        Set<Uf> ufToSet= cargaUFs.getUfs();

        // Ordenamos los registros aleatorios
        Map<String, Uf> ufMap = Utiles.setUfToHashmap(ufToSet);
        Map<String, Uf> ufMapListado = new TreeMap<String, Uf>(ufMap);

        Long cantidadDias = Utiles.definirRangoDias(cargaUFs.getInicio(), cargaUFs.getFin());
        List<model.Uf> listaUfRango = Utiles.getlistaUFsalida(cargaUFs.getInicio(), cantidadDias,ufMapListado);

        UfsSalida ufsSalida = new UfsSalida();
        ufsSalida.setInicio(Utiles.fechaEnString(cargaUFs.getInicio()));
        ufsSalida.setFin(Utiles.fechaEnString(cargaUFs.getFin()));
        ufsSalida.getUFs().addAll(listaUfRango);

        Utiles.generarArchivoUFJson(ufsSalida);
    }
}
