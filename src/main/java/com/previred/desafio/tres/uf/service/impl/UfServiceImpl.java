package com.previred.desafio.tres.uf.service.impl;

import com.previred.desafio.tres.uf.DatosUf;
import com.previred.desafio.tres.uf.Valores;
import com.previred.desafio.tres.uf.dto.ListaUfsOutputJson;
import com.previred.desafio.tres.uf.dto.UfOutputJsonFormat;
import com.previred.desafio.tres.uf.service.UfService;
import com.previred.desafio.tres.uf.vo.Uf;
import com.previred.desafio.tres.uf.vo.Ufs;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;

@Service
public class UfServiceImpl implements UfService {

    private Valores valores;

    private DatosUf datosUf;

    private SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");

    private DecimalFormat df = (DecimalFormat)DecimalFormat.getNumberInstance(Locale.GERMAN);

    public UfServiceImpl() {
        this.valores = new Valores();
        this.datosUf = DatosUf.getInstance();
    }

    @Override
    public Ufs getRango() throws IOException {
        try {
            return valores.getRango();
        } catch (Exception e) {
            throw new IOException(e);
        }
    }

    @Override
    public ListaUfsOutputJson getRangoCompleto() throws IOException {
        try {
            List<UfOutputJsonFormat> listaUfJsonLocal = new ArrayList<>();
            Ufs valoresUfLocal = valores.getRango();
            List<Uf> listaUfsRangoCompleto = datosUf.getUfs(valoresUfLocal.getInicio(), valoresUfLocal.getFin());
            valoresUfLocal.getUfs().addAll(listaUfsRangoCompleto);
            List<Uf> ufsListaLocal = new ArrayList<>(valoresUfLocal.getUfs());
            ufsListaLocal.sort(Comparator.comparing(Uf::getFecha).reversed());
            ListaUfsOutputJson listaUfsOutputJson = new ListaUfsOutputJson();
            listaUfsOutputJson.setInicio(simpleDateFormat.format(valoresUfLocal.getInicio()));
            listaUfsOutputJson.setFin(simpleDateFormat.format(valoresUfLocal.getFin()));
            for (Uf uf:
                 ufsListaLocal) {
                UfOutputJsonFormat ufOutputJsonFormat =
                        new UfOutputJsonFormat(simpleDateFormat.format(uf.getFecha()), df.format(uf.getValor()));
                listaUfJsonLocal.add(ufOutputJsonFormat);
            }
            listaUfsOutputJson.setUFs(listaUfJsonLocal);
            return listaUfsOutputJson;
        } catch (Exception e) {
            throw new IOException(e);
        }
    }
}
