package com.navastud.complementouf.converters;

import org.springframework.stereotype.Component;

import com.navastud.complementouf.dto.UfDto;
import com.navastud.complementouf.utls.Utils;
import com.previred.desafio.tres.uf.vo.Uf;

@Component("UfConverter")
public class UfConverter {

	public UfDto convertUfToUfDto(Uf uf) {

		UfDto ufDto = new UfDto();
		ufDto.setFecha(Utils.dateToLocalDate(uf.getFecha()));
		ufDto.setValor(uf.getValor().toString());

		return ufDto;
	}

	public Uf convertUfDtoToUf(UfDto dto) {

		Uf uf = new Uf();
		uf.setFecha(Utils.localDateToDate(dto.getFecha()));
		uf.setValor(Double.valueOf(dto.getValor()));

		return uf;
	}

}
