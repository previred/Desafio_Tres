package org.previred.desafiotres.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@AllArgsConstructor
@Builder
@Data
@NoArgsConstructor
public class UFfinal {
    private Date inicio;
    private Date fin;
    private List<Ufdato> UFs = new ArrayList<>();
}
