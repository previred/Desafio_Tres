package com.previred.services;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializer;
import com.previred.desafio.tres.uf.DatosUf;
import com.previred.desafio.tres.uf.Valores;
import com.previred.desafio.tres.uf.vo.Uf;
import com.previred.desafio.tres.uf.vo.Ufs;
import com.previred.model.DesafioTresResult;
import com.previred.model.DesafioTresUF;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.TreeSet;
import java.util.concurrent.TimeUnit;

@Service
public class DesafioTresService {
    private static final Logger LOGGER = LoggerFactory.getLogger(DesafioTresService.class);
    private static final DecimalFormat DECIMAL_FORMAT = new DecimalFormat("#,###.00", new DecimalFormatSymbols(Locale.forLanguageTag("es-CL")));

    private Valores valores;
    private DatosUf datosUf;

    public DesafioTresService() {
        this.valores = new Valores();
        this.datosUf = DatosUf.getInstance();
    }

    public byte[] getJsonBytes() {
        LOGGER.info("Consulting for JsonBytes");
        Ufs ufs = valores.getRango();

        TreeSet<DesafioTresUF> desafioTresUfs = getUfs(ufs);
        DesafioTresResult result = new DesafioTresResult(ufs.getInicio(), ufs.getFin(), desafioTresUfs.descendingSet());

        Gson gson = new GsonBuilder().setPrettyPrinting().setDateFormat("yyyy-MM-dd")
                .registerTypeAdapter(Double.class, (JsonSerializer<Double>) (value, theType, context) -> {
                    String stringValue;
                    if (value.isInfinite() || value.isNaN() || value < 1) {
                        stringValue = value.toString();
                    } else {
                        stringValue = DECIMAL_FORMAT.format(value);
                    }
                    return new JsonPrimitive(stringValue);
                }).create();

        return gson.toJson(result).getBytes();
    }

    public TreeSet<DesafioTresUF> getUfs(final Ufs ufs) {
        if (ufs == null || ufs.getUfs().size() == 0) {
            LOGGER.debug("Returning empty TreeSet");
            return new TreeSet<>();
        }

        TreeSet<DesafioTresUF> results = new TreeSet<>();
        ufs.getUfs().forEach(uf -> results.add(new DesafioTresUF(uf.getFecha(), uf.getValor())));
        LOGGER.debug(String.format("First element: %s, last element: %s", results.first(), results.last()));

        // Agregando primer y ultimo elemento
        if (results.first().getDate().getTime() > ufs.getInicio().getTime()) {
            DesafioTresUF desafioTresUF = DesafioTresUF.getFromUF(datosUf.getUf(ufs.getInicio()));
            LOGGER.debug(String.format("Adding new first element: %s", desafioTresUF));
            results.add(desafioTresUF);
        }
        if (results.last().getDate().getTime() < ufs.getFin().getTime()) {
            DesafioTresUF desafioTresUF = DesafioTresUF.getFromUF(datosUf.getUf(ufs.getFin()));
            LOGGER.debug(String.format("Adding new last element: %s", desafioTresUF));
            results.add(desafioTresUF);
        }

        // Agregando elementos faltantes
        List<DesafioTresUF> missingItems = new ArrayList<>();

        DateFormat df = new SimpleDateFormat("dd-MM-yyyy");
        DesafioTresUF cursor = results.first();
        for (DesafioTresUF desafioTresUf : results.subSet(cursor, false, results.last(), true)) {
            long difference = desafioTresUf.getDate().getTime() - cursor.getDate().getTime();
            long daysBetween = TimeUnit.DAYS.convert(difference, TimeUnit.MILLISECONDS);

            if (daysBetween > 1) {
                LOGGER.debug(String.format("Calculating days between %s to %s", df.format(cursor.getDate()), df.format(desafioTresUf.getDate())));
                Date nextDay = new Date(cursor.getDate().getTime() + 24 * 60 * 60 * 1_000);

                if (daysBetween == 2) {
                    missingItems.add(DesafioTresUF.getFromUF(datosUf.getUf(nextDay)));
                } else {
                    List<Uf> ufsBetween = datosUf.getUfs(nextDay, desafioTresUf.getDate());
                    missingItems.addAll(DesafioTresUF.getFromList(ufsBetween));
                }
            }
            cursor = desafioTresUf;
        }
        results.addAll(missingItems);

        return results;
    }

}
