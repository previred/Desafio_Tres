package com.previred.model;

import com.google.gson.annotations.SerializedName;

import java.util.Date;
import java.util.NavigableSet;

public class DesafioTresResult {
    @SerializedName("inicio")
    private Date startDate;
    @SerializedName("fin")
    private Date endDate;
    @SerializedName("UFs")
    NavigableSet<DesafioTresUF> desafioTresUfs;

    public DesafioTresResult(final Date inicio, final Date fin, final NavigableSet<DesafioTresUF> desafioTresUfs) {
        this.startDate = inicio != null ? new Date(inicio.getTime()) : null;
        this.endDate = fin != null ? new Date(fin.getTime()) : null;
        this.desafioTresUfs = desafioTresUfs;
    }

}
