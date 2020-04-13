package com.navastud.complementouf.converters;

import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.navastud.complementouf.dto.UfsDto;
import com.navastud.complementouf.utls.Utils;
import com.previred.desafio.tres.uf.vo.Ufs;

@Component("UfsConverter")
public class UfsConverter {

	@Autowired
	@Qualifier("UfConverter")
	private UfConverter ufConverter;

	public UfsDto convertUfsToUfsDto(Ufs ufs) {

		UfsDto ufsDto = new UfsDto();
		ufsDto.setUFs(ufs.getUfs().stream().map(uf -> ufConverter.convertUfToUfDto(uf))
				.sorted((uf1, uf2) -> uf1.getFecha().compareTo(uf2.getFecha())).collect(Collectors.toList()));
		ufsDto.setInicio(Utils.dateToLocalDate(ufs.getInicio()));
		ufsDto.setFin(Utils.dateToLocalDate(ufs.getFin()));

		return ufsDto;

	}

	public Ufs convertUfsDtoToUfs(UfsDto dto) {

		Ufs ufs = new Ufs();
		ufs.setUfs(dto.getUFs().stream().map(uf -> ufConverter.convertUfDtoToUf(uf)).collect(Collectors.toSet()));
		ufs.setInicio(Utils.localDateToDate(dto.getInicio()));
		ufs.setFin(Utils.localDateToDate(dto.getFin()));

		return ufs;
	}

}
