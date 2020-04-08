package com.previred.uf.delegate.impl;

import com.previred.desafio.tres.uf.Valores;
import com.previred.desafio.tres.uf.vo.Ufs;
import com.previred.uf.delegate.ValoresDelegate;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component("valoresDelegate")
public class ValoresDelegateImpl implements ValoresDelegate {
    @Override
    public Ufs getRango() throws Exception {
        return getUfs();
    }

    static Ufs getUfs() {
        Valores valores = new Valores();
        Ufs ufs;
        //Obtengo los valores
        ufs = valores.getRango();
        return ufs;
    }
}
