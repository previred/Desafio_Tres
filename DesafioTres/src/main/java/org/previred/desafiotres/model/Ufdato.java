package org.previred.desafiotres.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.Date;

@AllArgsConstructor
@Builder
@Data
public class Ufdato {

    private Double dato;
    private Date fecha;


}
