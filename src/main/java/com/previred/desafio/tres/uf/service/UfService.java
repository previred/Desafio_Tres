package com.previred.desafio.tres.uf.service;

import com.previred.desafio.tres.uf.dto.ListaUfsOutputJson;
import com.previred.desafio.tres.uf.vo.Ufs;

import java.io.IOException;

public interface UfService {

    Ufs getRango() throws IOException;

    ListaUfsOutputJson getRangoCompleto() throws IOException;
}
