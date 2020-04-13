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
	private Ufs ufs;

	@Autowired
	private UfsDto ufsDto;

	@Autowired
	@Qualifier("UfConverter")
	private UfConverter ufConverter;

	public UfsDto convertUfsToUfsDto(Ufs ufs) {

		this.ufsDto
				.setUFs(ufs.getUfs().stream().map(uf -> ufConverter.convertUfToUfDto(uf)).collect(Collectors.toList()));
		this.ufsDto.setInicio(Utils.dateToLocalDate(ufs.getInicio()));
		this.ufsDto.setFin(Utils.dateToLocalDate(ufs.getFin()));

		return this.ufsDto;
	}

	public Ufs convertUfsDtoToUfs(UfsDto dto) {

		this.ufs.setUfs(dto.getUFs().stream().map(uf -> ufConverter.convertUfDtoToUf(uf)).collect(Collectors.toSet()));
		this.ufs.setInicio(Utils.localDateToDate(dto.getInicio()));
		this.ufs.setFin(Utils.localDateToDate(dto.getFin()));

		return this.ufs;
	}

}
