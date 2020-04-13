package com.navastud.complementouf.converters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.navastud.complementouf.dto.UfDto;
import com.navastud.complementouf.utls.Utils;
import com.previred.desafio.tres.uf.vo.Uf;

@Component("UfConverter")
public class UfConverter {

	@Autowired
	private Uf uf;

	@Autowired
	private UfDto ufDto;

	public UfDto convertUfToUfDto(Uf uf) {

		this.ufDto.setFecha(Utils.dateToLocalDate(uf.getFecha()));
		this.ufDto.setValor(uf.getValor().toString());

		return this.ufDto;
	}

	public Uf convertUfDtoToUf(UfDto dto) {

		this.uf.setFecha(Utils.localDateToDate(dto.getFecha()));
		this.uf.setValor(Double.valueOf(dto.getValor()));

		return this.uf;
	}

}
