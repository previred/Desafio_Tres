package com.previred.egs.domain;

import com.previred.desafio.tres.uf.DatosUf;
import com.previred.desafio.tres.uf.vo.Uf;
import com.previred.desafio.tres.uf.vo.Ufs;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static com.previred.egs.common.PreviredUtils.convertToDateViaInstant;

public class UfDataAggregator {

    public Set fillMissingValues (Ufs ufs){

        LocalDate startDate = ufs.getInicio().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        LocalDate endDate = ufs.getFin().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

        List <Date> tempListOfDates = ufs.getUfs().stream().map(uf->uf.getFecha()).collect(Collectors.toList());

        Set<Uf> setUfTemp = Stream.iterate(startDate, date -> date.plusDays(1))
                .limit(ChronoUnit.DAYS.between(startDate, endDate))
                .filter(ld->!tempListOfDates.contains(convertToDateViaInstant(ld)))
                .map(ld -> DatosUf.getInstance().getUf(convertToDateViaInstant(ld)))
                .collect(Collectors.toSet());

        Set<Uf> mergedSet = ufs.getUfs().stream().collect(Collectors.toSet());
        mergedSet.addAll(setUfTemp);


        return reverseSortSet(mergedSet);

    }

    private Set<Uf> reverseSortSet (Set<Uf> ufSet){
        List<Uf> list = new ArrayList<>(ufSet) ;
        Collections.sort(list, Comparator.comparing(Uf::getFecha).reversed());
        return  new LinkedHashSet<>(list);
    }

}
