package com.previred.model;

import com.google.gson.annotations.SerializedName;
import com.previred.desafio.tres.uf.vo.Uf;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class DesafioTresUF implements Comparable<DesafioTresUF> {
    private static final DateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd");

    @SerializedName("fecha")
    private Date date;
    @SerializedName("dato")
    private double value;

    public DesafioTresUF(final Date date, final double value) {
        this.date = date;
        this.value = value;
    }

    public Date getDate() {
        return this.date != null ? new Date(this.date.getTime()) : null;
    }

    public double getValue() {
        return this.value;
    }

    @Override
    public int compareTo(final DesafioTresUF o) {
        if (this.date == null) {
            return 1;
        }
        if (o.getDate() == null) {
            return -1;
        }
        return this.date.compareTo(o.getDate());
    }


    public static DesafioTresUF getFromUF(final Uf uf) {
        return new DesafioTresUF(uf.getFecha(), uf.getValor());
    }

    public static List<DesafioTresUF> getFromList(final List<Uf> ufs) {
        List<DesafioTresUF> desafioTresUfs = new ArrayList<>(ufs.size());
        ufs.forEach(uf -> desafioTresUfs.add(getFromUF(uf)));
        return desafioTresUfs;
    }

    @Override
    public String toString() {
        return String.format("DesafioTresUF{date=%s, value=%s}", DATE_FORMAT.format(date), value);
    }
}
