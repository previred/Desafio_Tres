package com.desafio3.valoresuf.service;

import com.desafio3.valoresuf.IUtil;
import com.desafio3.valoresuf.model.UF;
import com.previred.desafio.tres.uf.*;
import com.previred.desafio.tres.uf.vo.Uf;
import com.previred.desafio.tres.uf.vo.Ufs;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.*;

@Service
public class ValoresUfServicio implements IUtil {

    List<UF> listUF = new ArrayList<>();
    DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

    ZoneId defaultZoneId = ZoneId.systemDefault();

    @Autowired
    UfsServicio ufsServicio;

    public JSONObject getRango() {

        //3rd party JAR, Third-party JAR, instantiate Valores class
        Valores valores = new Valores();

        //3rd party JAR, Third-party JAR, instantiate DatosUf class
        DatosUf datosUf = DatosUf.getInstance();

        //3rd party JAR, returns a structure with a date range and a list of UF values
        Ufs ufs = valores.getRango();
        log.info("Consume la función getRango de la clase com.previred.desafio.tres.uf.Valores");

        int ind = 0;
        //Validate if the Set of UF values is empty
        if (ufs.getUfs() != null)
            ufsServicio.convertSetToList( ufs.getUfs() ); //Convert Set to List of UF values
        else {
            ufsServicio.setUFs(datosUf.getUfs(ufs.getInicio(), ufs.getFin())); //Create new list of UF values
            ind = 1;
        }

        LocalDate fechaInicio = convertToLocalDateViaInstant( ufs.getInicio() );
        LocalDate fechaFin = convertToLocalDateViaInstant( ufs.getFin() );

        for (LocalDate date = fechaInicio; date.isBefore(fechaFin.plusDays(1)); date = date.plusDays(1)) {

            //Convert LocalDate to Date
            Date auxDate = Date.from(date.atStartOfDay(defaultZoneId).toInstant());

            //Evaluates if the date not exists in the set of UF values
            if (!containsFecha(ufsServicio.getUFs(), auxDate)) {
                ufsServicio.getUFs().add(datosUf.getUf(auxDate)); //Add new UF value to the list List<Uf>
                log.info("Uso del método \"getUf\" de la clase com.previred.desafio.tres.uf.DatosUf");
            }

        }

        //Order date from highest to lowest
        Comparator<Uf> byFecha = new Comparator<Uf>() {
            public int compare(Uf c1, Uf c2) {
                return Long.valueOf(c2.getFecha().getTime()).compareTo(c1.getFecha().getTime());
            }
        };

        //Order list of UF by date, from highest to lowest
        Collections.sort(ufsServicio.getUFs(), byFecha);
        log.info("Ordena lista de salida de forma descendente");

        //Formats "yyyy-mm-dd" to the fecha property and "#.###,00" to the dato property of the UF object
        fill_ListUf_WithDateFormat( ufsServicio.getUFs() );

        //Create JSON object with obtained values
        JSONObject obj = new JSONObject();
        obj.put("inicio", dateFormat.format( ufs.getInicio() ) );
        obj.put("fin", dateFormat.format( ufs.getFin() ) );
        obj.put("UFs", listUF);
        log.info("Genera formato de salida JSON");

        if (ind == 1)
            log.info("*Nota:* Se usó el método \"getUfs\" de la clase com.previred.desafio.tres.uf.DatosUf");

        return obj;
    }

    /**
     * @method writeFile
     * @param obj
     * @throws IOException
     */
    public void writeFile (JSONObject obj) throws IOException {

        try {

            FileWriter file = new FileWriter("valores.json");
            file.write(obj.toString());
            file.flush();
            file.close();
            log.info("Escribe archivo \"valores.json\"");

        } catch (IOException e) {
            log.error("Error creating file \"valores.json\"", e);
        }
    }

    /**
     * @method convertToLocalDateViaInstant
     * @param dateToConvert
     * @return LocalDate
     */
    private LocalDate convertToLocalDateViaInstant(Date dateToConvert) {
        return dateToConvert.toInstant()
                .atZone(defaultZoneId)
                .toLocalDate();
    }

    /**
     * @method containsFecha
     * @param list<Uf>
     * @param fecha
     * @return boolean
     */
    private boolean containsFecha(final List<Uf> list, final Date fecha){
        return list.stream().filter(o -> o.getFecha().getTime() == fecha.getTime() ).findFirst().isPresent();
    }

    /**
     * @method fill_ListUf_WithDateFormat
     * @param list<Uf>
     */
    private void fill_ListUf_WithDateFormat(final List<Uf> list){
        list.stream().forEach(o -> listUF.add( new UF( dateFormat.format( o.getFecha() ),
                                                       new DecimalFormat("#,###.00", new DecimalFormatSymbols(Locale.ITALIAN))
                                                               .format( o.getValor() )
                                                     )
                                              )
                              );
    }


}
