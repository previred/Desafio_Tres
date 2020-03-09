package cl.desafio.uf.servicios;

import cl.desafio.uf.entidades.UFValor;
import cl.desafio.uf.entidades.UFsRango;
import com.previred.desafio.tres.uf.DatosUf;
import com.previred.desafio.tres.uf.Valores;
import com.previred.desafio.tres.uf.vo.Uf;
import com.previred.desafio.tres.uf.vo.Ufs;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by APIUXNB531 on 6/3/2020.
 */
@Service
public class GestionarUf {

    private DatosUf datosUf = DatosUf.getInstance();

    public UFsRango listarUf (){
        Valores valor = new Valores();
        UFsRango uFsRango = new UFsRango();
        Ufs ufs = valor.getRango();
        uFsRango.setInicio(new Date(ufs.getInicio().getTime()));
        uFsRango.setFin(new Date(ufs.getFin().getTime()));
        UFValor ufValor;
        List<UFValor> list = new ArrayList<>();
        for (Uf ufIni:ufs.getUfs()){
            ufValor = new UFValor();
            ufValor.setValor(ufIni.getValor());
            ufValor.setFecha(new Date(ufIni.getFecha().getTime()));
            list.add(ufValor);
        }

        uFsRango.setListaUFs(list);
        return uFsRango;
    }

    public UFValor buscarUf(final Date fechaFaltante){
        UFValor ufValor;
        Uf ufIni = datosUf.getUf(fechaFaltante);
        ufValor = new UFValor();
        ufValor.setValor(ufIni.getValor());
        ufValor.setFecha(new Date(ufIni.getFecha().getTime()));
        return ufValor;
    }

    public List<UFValor> buscarUf(final Date rangoIncial, final Date rangoFinal){
        UFValor ufValor;
        List<UFValor> list = new ArrayList<>();
        List<Uf> listIf = datosUf.getUfs(rangoIncial, rangoFinal);
        for (Uf ufIni:listIf){
            ufValor = new UFValor();
            ufValor.setValor(ufIni.getValor());
            ufValor.setFecha(new Date(ufIni.getFecha().getTime()));
            list.add(ufValor);
        }
        return list;
    }
}
