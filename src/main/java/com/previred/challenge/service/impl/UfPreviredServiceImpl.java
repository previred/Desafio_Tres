package com.previred.challenge.service.impl;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Comparator;
import java.util.Date;
import java.util.Iterator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.previred.challenge.service.IUfPreviredService;
import com.previred.challenge.util.CloneUtil;
import com.previred.desafio.tres.uf.DatosUf;
import com.previred.desafio.tres.uf.Valores;
import com.previred.desafio.tres.uf.vo.Uf;
import com.previred.desafio.tres.uf.vo.Ufs;

@Service
public class UfPreviredServiceImpl implements IUfPreviredService {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(UfPreviredServiceImpl.class);

	private final Valores valores;
	private final DatosUf ufComponent;

	private Ufs ufs;
	private Ufs ufsAux;
	
	private LocalDate startDate;
	private LocalDate endDate;

	public UfPreviredServiceImpl() {
		this.valores = new Valores();
		this.ufComponent = DatosUf.getInstance();
	}

	@Override
	public com.previred.challenge.domain.Valores getUf() {
		LOGGER.debug("Start Method: getUf(..) from class: %s", this.getClass().getName());
		LOGGER.debug("calling the method that assigns values to the variables");
		settingValueService();
		LOGGER.debug("Starting loop which gets missing uf values");
		while (startDate.isBefore(endDate)) {
			if(fillListUfWhenListAuxIsEmpty()) {
				break;
			}
			fillListUfWhenListAuxIsNotEmpty( returnMissingDays(startDate, 0L) );
		}
		LOGGER.debug("Uf list is cloned to a new object with which it works");
		com.previred.challenge.domain.Valores value = CloneUtil.Object(this.ufs, com.previred.challenge.domain.Valores.class);
		LOGGER.debug("Order Uf list");
		value.getUFs().sort(Comparator.comparing(Uf::getFecha));
		LOGGER.debug("Return Object");
		return value;
	}
	
	private void settingValueService() {
		LOGGER.debug("Start Method: settingValueService() from class: %s", this.getClass().getName());
		this.ufs = valores.getRango();
		this.ufsAux = CloneUtil.Object(this.ufs, Ufs.class);
		this.startDate = this.ufs.getInicio().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
		this.endDate = this.ufs.getFin().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
		LOGGER.debug("Finish Method: settingValueService() from class: %s", this.getClass().getName());

	}
		
	private boolean fillListUfWhenListAuxIsEmpty() {
		LOGGER.debug("Start Method: fillListUfWhenListAuxIsEmpty() from class: %s", this.getClass().getName());
		if (this.ufsAux.getUfs().isEmpty()) {
			LOGGER.debug("The aux list is empty, there are no more dates delivered, therefore they are searched from the date that remained until the end date");
			this.ufs.getUfs()
					.addAll(ufComponent.getUfs(
							Date.from(startDate.atStartOfDay(ZoneId.systemDefault()).toInstant()),
							Date.from(endDate.atStartOfDay(ZoneId.systemDefault()).toInstant())));
			LOGGER.debug("Finish Method: fillListUfWhenListAuxIsEmpty() from class: %s", this.getClass().getName());
			return true;
		}else { 
			LOGGER.debug("Finish Method: fillListUfWhenListAuxIsEmpty() from class: %s", this.getClass().getName());
			return false; 
		}
	}
	
	private void fillListUfWhenListAuxIsNotEmpty(long daysMissing) {
		LOGGER.debug("Start Method: fillListUfWhenListAuxIsNotEmpty(..) from class: %s", this.getClass().getName());
		if (daysMissing > 1) {
			LOGGER.debug("There is more than one non-existent date, so it is searched for more than one date");
			this.ufs.getUfs().addAll(ufComponent.getUfs(
					Date.from(startDate.atStartOfDay(ZoneId.systemDefault()).toInstant()),
					Date.from(startDate.plusDays(daysMissing).atStartOfDay(ZoneId.systemDefault()).toInstant())));
		} else if (daysMissing == 1) {
			LOGGER.debug("There is only a non-existent date, so you are looking for a date");
			this.ufs.getUfs()
					.add(ufComponent.getUf(Date.from(startDate.atStartOfDay(ZoneId.systemDefault()).toInstant())));
		}
		this.startDate = this.startDate.plusDays(daysMissing + 1L);
		LOGGER.debug("Finish Method: fillListUfWhenListAuxIsNotEmpty(..) from class: %s", this.getClass().getName());
	}

	private long returnMissingDays(LocalDate date, Long day) {
		LOGGER.debug("Start Method: returnMissingDays(..) from class: %s", this.getClass().getName());
		Date dateAux = Date.from(date.atStartOfDay(ZoneId.systemDefault()).toInstant());
		if (removeIfExistDate(dateAux)) {
			LOGGER.debug("leaving recursion, number: %d", day);
			return day;
		} else {
			LOGGER.debug("Entering recursion, number: %d", day);
			return returnMissingDays(date.plusDays(1L), (day + 1L));
		}
	}

	private boolean removeIfExistDate(Date date) {
		LOGGER.debug("Start Method: removeIfExistDate(..) from class: %s", this.getClass().getName());
		boolean isDelete = false;
		Iterator<Uf> itr = this.ufsAux.getUfs().iterator();
		while (itr.hasNext()) {
			Uf uf = itr.next();
			if (uf.getFecha().equals(date)) {
				this.ufsAux.getUfs().remove(uf);
				isDelete = true;
				break;
			}
		}
		LOGGER.debug("Finish Method: removeIfExistDate(..) from class: %s", this.getClass().getName());
		return isDelete;
	}

}
