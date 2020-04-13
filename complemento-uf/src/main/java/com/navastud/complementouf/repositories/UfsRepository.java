package com.navastud.complementouf.repositories;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.navastud.complementouf.models.DateRange;
import com.navastud.complementouf.utls.Utils;
import com.previred.desafio.tres.uf.DatosUf;
import com.previred.desafio.tres.uf.Valores;
import com.previred.desafio.tres.uf.vo.Uf;
import com.previred.desafio.tres.uf.vo.Ufs;

@Component("UfsRepository")
public class UfsRepository {

	@Autowired
	private Ufs ufs;

	@Autowired
	private DatosUf datosUf;

	public Optional<Ufs> findUfs() {
		return getUfs();
	}

	private Optional<Ufs> getUfs() {

		ufs = new Valores().getRango();
		LocalDate startDate = Utils.dateToLocalDate(ufs.getInicio());
		LocalDate endDate = Utils.dateToLocalDate(ufs.getFin());
		DateRange dateRange = new DateRange(startDate, endDate);

		List<Date> dates = ufs.getUfs().parallelStream().map(uf -> uf.getFecha()).sorted().collect(Collectors.toList());

		for (Date date : dateRange.toDateList()) {
			if (!dates.contains(date)) {
				ufs.getUfs().add(datosUf.getUf(date));
			}
		}
		return Optional.of(ufs);
	}

	public List<Uf> getListUfsOrderDescend() {
		return this.ufs.getUfs().stream().sorted((uf1, uf2) -> uf1.getFecha().compareTo(uf2.getFecha()))
				.collect(Collectors.toList());
	}

	public Date getFechaInicio() {
		return this.ufs.getInicio();
	}

	public Date getFechaFin() {
		return this.ufs.getFin();
	}

}
