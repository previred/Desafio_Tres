package com.previred.services;

import com.previred.desafio.tres.uf.DatosUf;
import com.previred.desafio.tres.uf.Valores;
import org.springframework.stereotype.Service;

@Service
public class DesafioTresService {

    private Valores valores;
    private DatosUf datosUf;

    public DesafioTresService() {
        this.valores = new Valores();
        this.datosUf = DatosUf.getInstance();
    }
}
