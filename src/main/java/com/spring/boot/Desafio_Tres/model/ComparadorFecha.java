package com.spring.boot.Desafio_Tres.model;

import com.previred.desafio.tres.uf.vo.Uf;

import java.util.Comparator;

/**
 * Realiza la comparación de Fechas para ordenarla de
 * manera descendente
 *
 * @author  Katherine Candia
 * @version 1.0
 * @since   2020-04-11
 */
public class ComparadorFecha implements Comparator<Uf> {
    public int compare(Uf a, Uf b)
    {
        return a.getFecha().compareTo(b.getFecha());
    }
}
