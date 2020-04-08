package com.previred.uf.delegate.impl;

import com.previred.desafio.tres.uf.DatosUf;
import com.previred.desafio.tres.uf.vo.Uf;
import com.previred.uf.delegate.UfDelegate;
import com.previred.uf.delegate.UfsDelegate;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class DatosUfDelegateImpl implements UfDelegate, UfsDelegate {
    @Override
    public Uf getUf(Date date){
        DatosUf datosUf = DatosUf.getInstance();
        Uf uf = new Uf();
        try{
            //Obtengo los valores uf
            uf = datosUf.getUf(date);
        }catch(Exception e) {
            e.printStackTrace();
        }
        return uf;
    }

    @Override
    public List<Uf> getUfs(Date inicio, Date fin){
        DatosUf datosUfs = DatosUf.getInstance();
        List<Uf> ufsLst = new ArrayList<>();
        try{
            //Obtengo los valores uf
            ufsLst = datosUfs.getUfs(inicio, fin);
        }catch(Exception e) {
            e.printStackTrace();
        }
        return ufsLst;
    }
}
