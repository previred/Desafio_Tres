package cl.desafio.uf.componentes;

import cl.desafio.uf.entidades.UFValor;
import cl.desafio.uf.entidades.UFsRango;
import cl.desafio.uf.servicios.GestionarUf;
import cl.desafio.uf.utilidades.Util;
import com.previred.desafio.tres.uf.vo.Uf;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import static cl.desafio.uf.utilidades.Util.cambiarFecha;

@Component
public class ControlUf {

    @Autowired
    private GestionarUf gestionUfService;
    @Autowired
    private ExportUf exportUf;

    /**
     * Lògica
     *
     */
    public void controlUf() {
        UFsRango rangoUf =  gestionUfService.listarUf();
        List<UFValor> orderList= rangoUf.getListaUFs();
        orderList.sort(Comparator.comparing(UFValor::getFecha).reversed());

        Date vfFinal = rangoUf.getFin();
        int iterador = 0;
        int difDias;
        int largoList =orderList.size() -1;
        List<UFValor> listFinal= new ArrayList();

        do{
            difDias = Util.diferenciaDias(vfFinal, orderList.get(iterador).getFecha());
            if (difDias == 1){
                listFinal.add(gestionUfService.buscarUf(vfFinal));
                listFinal.add(orderList.get(iterador));
            }else if (difDias >= 2){
                listFinal.addAll(gestionUfService.buscarUf(orderList.get(iterador).getFecha(), vfFinal));

            }else if(difDias == 0){
                listFinal.add(orderList.get(iterador));
            }
            vfFinal = cambiarFecha(orderList.get(iterador).getFecha(),-1);
            iterador++;
        }while (rangoUf.getInicio().before(vfFinal) && largoList >= iterador );

        if(rangoUf.getInicio().before(vfFinal) ){
            listFinal.addAll(gestionUfService.buscarUf(rangoUf.getInicio(), vfFinal));
        }else if(Util.diferenciaDias(vfFinal, rangoUf.getInicio())== 0){
            listFinal.add(gestionUfService.buscarUf(vfFinal));
        }
        listFinal.sort(Comparator.comparing(Uf::getFecha).reversed());

        rangoUf.getListaUFs().clear();
        rangoUf.setListaUFs(listFinal);
        exportUf.exportarJson(exportUf.formatoJson(rangoUf));
    }
}
