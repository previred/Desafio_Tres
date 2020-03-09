package cl.desafio.uf.entidades;

import com.previred.desafio.tres.uf.vo.Ufs;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by APIUXNB531 on 7/3/2020.
 */
public class UFsRango extends Ufs {


    private List<UFValor> listUfsRango = new ArrayList<>();

    public List<UFValor> getListaUFs() {
        return this.listUfsRango;
    }

    public void setListaUFs(List<UFValor> listaUFs) {
        this.listUfsRango = listaUFs;
    }
}
