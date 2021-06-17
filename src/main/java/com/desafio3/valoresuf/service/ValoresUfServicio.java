package com.desafio3.valoresuf.service;

import com.desafio3.valoresuf.model.UF;
import com.previred.desafio.tres.uf.DatosUf;
import com.previred.desafio.tres.uf.Valores;
import com.previred.desafio.tres.uf.vo.Uf;
import com.previred.desafio.tres.uf.vo.Ufs;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
public class ValoresUfServicio {

    Logger log = LoggerFactory.getLogger(ValoresUfServicio.class);

    List<UF> listUF = new ArrayList<>();
    DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

    ZoneId defaultZoneId = ZoneId.systemDefault();

    @Autowired
    ListUfsServicio ufsServicio;

    @Autowired
    PathWrite p;

    /**
     * method: getRango
     * @return JSONObject
     */
    public JSONObject getRango() {

        //3rd party JAR, Third-party JAR, instantiate Valores class
        Valores valores = new Valores();

        //3rd party JAR, returns a structure with a date range and a list of UF values
        Ufs ufs = valores.getRango();
        log.info("Consume la función getRango de la clase com.previred.desafio.tres.uf.Valores");

        //3rd party JAR, Third-party JAR, instantiate DatosUf class
        DatosUf datosUf = DatosUf.getInstance();

        int ind = 0;
        //Validate if the Set of UF values is empty
        if (ufs.getUfs() != null) {

            //Convert Set to List of UF values
            ufsServicio.convertSetToList(ufs.getUfs());

        } else {

            //Create new list of UF values
            ufsServicio.setUfs(datosUf.getUfs(ufs.getInicio(), ufs.getFin()));
            ind = 1;

        }

        LocalDate fechaInicio = convertToLocalDateViaInstant( ufs.getInicio() );
        LocalDate fechaFin = convertToLocalDateViaInstant( ufs.getFin() );

        //Complete range of dates
        for (LocalDate date = fechaInicio; date.isBefore(fechaFin.plusDays(1)); date = date.plusDays(1)) {

            //Convert LocalDate to Date
            Date auxDate = Date.from(date.atStartOfDay(defaultZoneId).toInstant());

            //Evaluates if the date not exists in the set of UF values
            if (!containsFecha(ufsServicio.getUfs(), auxDate)) {

                //Add new UF value to the list List<Uf>
                ufsServicio.getUfs().add( datosUf.getUf(auxDate) );

                log.info("Uso del método \"getUf\" de la clase com.previred.desafio.tres.uf.DatosUf");
            }

        }

        //Order date from highest to lowest
        Comparator<Uf> byFecha = (Uf c1, Uf c2) -> Long.valueOf(c2.getFecha().getTime()).compareTo(c1.getFecha().getTime());

        //Order list of UF by date, from highest to lowest
        Collections.sort(ufsServicio.getUfs(), byFecha);
        log.info("Ordena lista de salida de forma descendente");

        //Formats "yyyy-mm-dd" to the fecha property and "#.###,00" to the dato property of the UF object
        fillListUfWithDateFormat( ufsServicio.getUfs() );

        if (ind == 1)
            log.info("*Nota:* Se usó el método \"getUfs\" de la clase com.previred.desafio.tres.uf.DatosUf");

        //Create JSON object with obtained values
        JSONObject obj = new JSONObject();
        obj.put("inicio", dateFormat.format( ufs.getInicio() ) );
        obj.put("fin", dateFormat.format( ufs.getFin() ) );
        obj.put("UFs", listUF);

        log.info("Genera formato de salida JSON");

        return obj;
    }

    /**
     * @method writeFile
     * @param obj
     * @throws IOException
     */
    public void writeFile (JSONObject obj) throws IOException {

        String path = PathWrite.stringPath.append(p.getFileName())
                .toString();

        try ( FileWriter file = new FileWriter(path) ) {

            file.write(obj.toString());
            log.info("Escribe archivo \"{}\"",path);

        } catch (IOException e) {
            log.error("Error creating file \"{}\"",path);
            throw e;
        }
        finally {
            PathWrite.stringPath.delete(0, PathWrite.stringPath.length()-1);
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
        return list.stream().anyMatch( o -> o.getFecha().getTime() == fecha.getTime() );
    }

    /**
     * @method fill_ListUf_WithDateFormat
     * @param list<Uf>
     */
    private void fillListUfWithDateFormat(final List<Uf> list){
        list.stream().forEach(o -> listUF.add( new UF( dateFormat.format( o.getFecha() ),
                                                       new DecimalFormat("#,###.00", new DecimalFormatSymbols(Locale.ITALIAN))
                                                               .format( o.getValor() )
                                                     )
                                              )
                              );
    }


}
