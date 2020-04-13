package com.navastud.complementouf.repositories;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import com.navastud.complementouf.models.DateRange;
import com.navastud.complementouf.utls.Utils;
import com.previred.desafio.tres.uf.DatosUf;
import com.previred.desafio.tres.uf.vo.Uf;
import com.previred.desafio.tres.uf.vo.Ufs;

@Component("UfsRepository")
public class UfsRepository {

	@Autowired
	private DatosUf datosUf;

	@Autowired
	private Ufs ufs;

	@Autowired
	private DateRange dateRange;

	@Bean
	public DateRange dateRangeUfs() {
		LocalDate startDate = Utils.dateToLocalDate(this.ufs.getInicio());
		LocalDate endDate = Utils.dateToLocalDate(this.ufs.getFin());
		return new DateRange(startDate, endDate);
	}

	public Optional<Ufs> findUfs() {
		return Optional.of(updateUfs());
	}

	private Ufs updateUfs() {
		List<Date> dates = this.ufs.getUfs().parallelStream().map(uf -> uf.getFecha()).sorted()
				.collect(Collectors.toList());

		for (Date date : dateRange.toDateList()) {
			if (!dates.contains(date)) {
				this.ufs.getUfs().add(datosUf.getUf(date));
			}
		}
		return this.ufs;
	}

	public List<Uf> getListUfsOrderDescend() {
		return this.updateUfs().getUfs().stream().sorted((uf1, uf2) -> uf2.getFecha().compareTo(uf1.getFecha()))
				.collect(Collectors.toList());
	}

	public Date getFechaInicio() {
		return this.ufs.getInicio();
	}

	public Date getFechaFin() {
		return this.ufs.getFin();
	}

}
