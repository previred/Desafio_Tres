package com.navastud.complementouf.services.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.navastud.complementouf.converters.UfConverter;
import com.navastud.complementouf.converters.UfsConverter;
import com.navastud.complementouf.dto.UfDto;
import com.navastud.complementouf.dto.UfsDto;
import com.navastud.complementouf.exception.UfsNotFoundException;
import com.navastud.complementouf.repositories.UfsRepository;
import com.navastud.complementouf.services.UfsService;
import com.previred.desafio.tres.uf.vo.Ufs;

@Service("UfsServiceImpl")
public class UfsServiceImpl implements UfsService {

	@Qualifier("UfsRepository")
	private UfsRepository ufsRepository;

	@Qualifier("UfsConverter")
	private UfsConverter ufsConverter;

	@Qualifier("UfConverter")
	private UfConverter ufConverter;

	@Autowired
	public UfsServiceImpl(UfsRepository ufsRepository, UfsConverter ufsConverter, UfConverter ufConverter) {
		super();
		this.ufsRepository = ufsRepository;
		this.ufsConverter = ufsConverter;
		this.ufConverter = ufConverter;
	}

	@Override
	public List<UfDto> getAllUfs() {
		return ufsRepository.getListUfsOrderDescend().stream().map(uf -> ufConverter.convertUfToUfDto(uf))
				.collect(Collectors.toList());
	}

	@Override
	public UfsDto getUfs() {
		Ufs ufs = ufsRepository.findUfs().orElseThrow(() -> new UfsNotFoundException("Ufs not found"));
		return ufsConverter.convertUfsToUfsDto(ufs);
	}

}
