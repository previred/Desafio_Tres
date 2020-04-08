package com.previred.uf.utils;

import com.previred.desafio.tres.uf.vo.Uf;
import com.previred.uf.model.uf;
import org.springframework.cglib.core.Local;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class number {
    public static List<uf> formatArrayNumber(List<Uf> lstObj, Locale locale){
        List<uf> _listUf = new ArrayList<>();
        SimpleDateFormat sdf;
        sdf = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        locale(lstObj, _listUf, sdf, locale);

        return _listUf;
    }

    private static void locale(List<Uf> lstObj, List<uf> _listUf, SimpleDateFormat sdf, Locale locale) {
        NumberFormat nf_in = NumberFormat.getNumberInstance(locale);

        for (int i = 0; i < lstObj.toArray().length; i += 1) {
            uf _ufTmp = new uf();
            _ufTmp.setDato(nf_in.format(lstObj.get(i).getValor()));
            _ufTmp.setFecha(sdf.format(lstObj.get(i).getFecha()));
            _listUf.add(_ufTmp);
        }
    }
}
