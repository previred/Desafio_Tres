package com.example.previredTest.service.serviceImpl;

import com.example.previredTest.dto.PeriodWithUfDTO;
import com.example.previredTest.dto.UfDTO;
import com.example.previredTest.service.PreviredTestService;
import com.previred.desafio.tres.uf.DatosUf;
import com.previred.desafio.tres.uf.Valores;
import com.previred.desafio.tres.uf.vo.Uf;
import com.previred.desafio.tres.uf.vo.Ufs;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.*;
import java.util.concurrent.TimeUnit;

@Service
public class PreviredTestServiceImpl implements PreviredTestService {

    @Override
    public PeriodWithUfDTO getPeriodWithUfData() throws IOException {
        PeriodWithUfDTO periodWithUfDTO = new PeriodWithUfDTO();
        Valores valores = new Valores();
        Ufs ufs = valores.getRango();
        periodWithUfDTO.setInicio(this.convertDateToStringFormat(ufs.getInicio()));
        periodWithUfDTO.setFin(this.convertDateToStringFormat(ufs.getFin()));
        List<Uf> ufsFromGetRango = new ArrayList<>(ufs.getUfs());
        ufsFromGetRango.sort(Comparator.comparing(Uf::getFecha));
        Date previousValue = ufs.getInicio();
        List<Uf> newUfList = new ArrayList<>();
        for(Uf data: ufsFromGetRango) {
            if (!previousValue.equals(data.getFecha())){
                Long diffMiliSeconds = data.getFecha().getTime() - previousValue.getTime();
                Integer diff = Math.toIntExact(TimeUnit.DAYS.convert(diffMiliSeconds, TimeUnit.MILLISECONDS));
                if (diff.equals(0) || diff.equals(1)) {
                    continue;
                } else if (diff.equals(2)) {
                    LocalDateTime initLocalTime = LocalDateTime.from(
                            previousValue.toInstant().atZone(ZoneId.of("America/Santiago"))).plusDays(1);
                    Date initLocalTimeToDate = this.convertLocalDateTimeToDate(initLocalTime);
                    previousValue = data.getFecha();
                    newUfList.add(DatosUf.getInstance().getUf(initLocalTimeToDate));
                } else if (diff > 2) {
                    LocalDateTime initLocalTime = LocalDateTime.from(
                            previousValue.toInstant().atZone(ZoneId.of("America/Santiago"))).plusDays(1);
                    LocalDateTime endLocalTime = LocalDateTime.from(
                            data.getFecha().toInstant().atZone(ZoneId.of("America/Santiago"))).minusDays(1);
                    Date initLocalTimeToDate = this.convertLocalDateTimeToDate(initLocalTime);
                    Date endLocalTimeToDate = this.convertLocalDateTimeToDate(endLocalTime);
                    previousValue = data.getFecha();
                    List<Uf> newUfs = DatosUf.getInstance().getUfs(initLocalTimeToDate, endLocalTimeToDate);
                    for (Uf uf : newUfs) {
                        newUfList.add(uf);
                    }
                }
            }
        }
        ufsFromGetRango.addAll(newUfList);
        ufsFromGetRango.sort(Comparator.comparing(Uf::getFecha));
        Collections.sort(ufsFromGetRango, Collections.reverseOrder(Comparator.comparing(Uf::getFecha)));
        ufsFromGetRango = this.fixElementOnTheList(ufsFromGetRango, ufs.getInicio(), ufs.getFin());
        Collections.sort(ufsFromGetRango, Collections.reverseOrder(Comparator.comparing(Uf::getFecha)));
        periodWithUfDTO.setUFs(this.convertListUfToUfDTO(ufsFromGetRango));

        return periodWithUfDTO;
    }

    public List<UfDTO> convertListUfToUfDTO(List<Uf> ufList) {
        List<UfDTO> ufDTOList = new ArrayList<>();
        ufList.stream().forEach(uf -> {
            UfDTO ufDTO = new UfDTO();
            ufDTO.setDato(uf.getValor());
            ufDTO.setFecha(this.convertDateToStringFormat(uf.getFecha()));
            ufDTOList.add(ufDTO);
        });
        return ufDTOList;
    }

    public String convertDateToStringFormat(Date date) {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        String dateWithFormat = df.format(date);
        return dateWithFormat;
    }

    public List<Uf> fixElementOnTheList(List<Uf> newUfList, Date startDate, Date endDate) {
        Uf lastElement = newUfList.get(0);
        Uf firstElement = newUfList.get(newUfList.size() - 1);
        newUfList = this.validateDiffAndAddValueToList(newUfList, endDate, lastElement.getFecha(), Boolean.FALSE);
        newUfList = this.validateDiffAndAddValueToList(newUfList, startDate, firstElement.getFecha(), Boolean.TRUE);
        return newUfList;
    }

    public List<Uf> validateDiffAndAddValueToList(List<Uf> newUfList, Date endDate, Date endDateOfList, Boolean init) {
        Long diffMiliSeconds = (init)? endDateOfList.getTime() - endDate.getTime()
                : endDate.getTime() - endDateOfList.getTime();
        Integer diff = Math.toIntExact(TimeUnit.DAYS.convert(diffMiliSeconds, TimeUnit.MILLISECONDS));
        if (diff.equals(1)) {
            LocalDateTime initLocalTime = LocalDateTime.from(
                    endDate.toInstant().atZone(ZoneId.of("America/Santiago")));
            Date initLocalTimeToDate = Date.from(
                    initLocalTime.atZone(ZoneId.systemDefault()).toInstant());
            newUfList.add(DatosUf.getInstance().getUf(initLocalTimeToDate));
        } else if (diff >= 2) {
            LocalDateTime initLocalTime = LocalDateTime.from(
                    endDateOfList.toInstant().atZone(ZoneId.of("America/Santiago"))).plusDays(1);
            LocalDateTime endLocalTime = LocalDateTime.from(
                    endDate.toInstant().atZone(ZoneId.of("America/Santiago")));
            Date initLocalTimeToDate = this.convertLocalDateTimeToDate(initLocalTime);
            Date endLocalTimeToDate = this.convertLocalDateTimeToDate(endLocalTime);
            List<Uf> newUfs = DatosUf.getInstance().getUfs(initLocalTimeToDate, endLocalTimeToDate);
            for (Uf uf : newUfs) {
                newUfList.add(uf);
            }
        }
        return newUfList;
    }

    public Date convertLocalDateTimeToDate(LocalDateTime initLocalTime) {
        Date initLocalTimeToDate = Date.from(
                initLocalTime.atZone(ZoneId.systemDefault()).toInstant());
        return initLocalTimeToDate;
    }
}
