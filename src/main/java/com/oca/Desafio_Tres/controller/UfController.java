package com.oca.Desafio_Tres.controller;

import com.oca.Desafio_Tres.input.GetUFsInput;
import com.oca.Desafio_Tres.output.GetUFsOutput;
import com.oca.Desafio_Tres.output.UF;
import com.previred.desafio.tres.uf.vo.Uf;
import com.previred.desafio.tres.uf.vo.Ufs;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import com.previred.desafio.tres.uf.Valores;
import com.previred.desafio.tres.uf.DatosUf;

@RestController()
public class UfController {
    private static final Logger _log = LoggerFactory.getLogger(UfController.class);
    //private static String inputDateFormat = "yyyy-MM-dd";
    private static String outputDateFormat = "yyyy-MM-dd";
    private static Long dayInMs = 86400000L;

    public static List<Date> getDiasEntre(Date startDate, Date endDate) {
        List<Date> datesInRange = new ArrayList<>();
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(startDate);

        Calendar endCalendar = new GregorianCalendar();
        endCalendar.setTime(endDate);

        while (calendar.before(endCalendar)) {
            Date result = calendar.getTime();
            datesInRange.add(result);
            calendar.add(Calendar.DATE, 1);
        }

        Date last = calendar.getTime();
        datesInRange.add(last);
        return datesInRange;
    }

    @PostMapping("/ufs")
    public GetUFsOutput getUfs(
        //    @RequestBody @Valid GetUFsInput input
        ) throws Exception {
        //_log.info("/ufs "+input.toString());
        _log.info("/ufs ");

        //SimpleDateFormat idf = new SimpleDateFormat(inputDateFormat);
        //Date inicio = idf.parse(input.getInicio());
        //Date fin = idf.parse(input.getFin());

        SimpleDateFormat odf = new SimpleDateFormat(outputDateFormat);

        Valores valoresUFs = new Valores();
        Ufs ufs = valoresUFs.getRango();

        String inicioVal = odf.format(ufs.getInicio());
        String finVal = odf.format(ufs.getFin());

        //transform to output format
        ArrayList<UF> ufsProc = new ArrayList<UF>();
        for(Uf ufData:ufs.getUfs()) {
            UF ufOut = new UF();
            ufOut.setFecha(odf.format(ufData.getFecha()));
            ufOut.setDato(ufData.getValor());
            ufsProc.add(ufOut);
        }

        //sort to process
        Collections.sort(ufsProc, (a, b) -> {
            return a.getFecha().compareTo(b.getFecha());
        });

        List<UF> ufsOut = completarData(ufsProc, ufs.getInicio(), ufs.getFin(), odf);

        //sort output
        Collections.sort(ufsOut, (a, b) -> {
            return -a.getFecha().compareTo(b.getFecha());
        });

        GetUFsOutput output = new GetUFsOutput(inicioVal, finVal, ufsOut);

        return output;
    }

    private List<UF> completarData(List<UF> ufsOri, Date fechaInicio, Date fechaFin, SimpleDateFormat odf) throws ParseException {
        ArrayList<UF> ufsOut = new ArrayList<UF>();

        List<Date> fechasRecorrer = getDiasEntre(fechaInicio, fechaFin);

        DatosUf datosUf = DatosUf.getInstance();

        int posDatoLista = 0;
        String fechaAntStr = "";
        for(Date fechaAct: fechasRecorrer) {
            String fechaActStr = odf.format(fechaAct);
            _log.error("f: " + fechaActStr + " p: " + posDatoLista);
            Date fechaActLimpia = odf.parse(fechaActStr);

            if(fechaActStr.compareTo(fechaAntStr) == 0) {
                _log.error("fr: " + fechaActStr);
                continue;
            }

            UF ufAct = null;

            if(posDatoLista < ufsOri.size()) {
                UF ufOriAct = ufsOri.get(posDatoLista);
                if (fechaActStr.compareTo(ufOriAct.getFecha()) == 0) {
                    ufAct = ufOriAct;
                    posDatoLista++;
                }
            }

            if(ufAct == null) {
                try {
                    Uf ufCalc = datosUf.getUf(fechaActLimpia);
                    ufAct = new UF();
                    ufAct.setFecha(fechaActStr);
                    ufAct.setDato(ufCalc.getValor());
                } catch(Exception e) {
                    _log.error("Problemas buscando valor Uf para fecha " + fechaActStr, e);
                }
            }

            ufsOut.add(ufAct);
            fechaAntStr = fechaActStr;
        }

        return ufsOut;
    }
}

