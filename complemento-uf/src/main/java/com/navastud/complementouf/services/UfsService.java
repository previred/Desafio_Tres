package com.navastud.complementouf.services;

import java.util.List;

import com.navastud.complementouf.dto.UfDto;
import com.navastud.complementouf.dto.UfsDto;

public interface UfsService {

	public List<UfDto> getAllUfs();

	public UfsDto getUfs();
}
