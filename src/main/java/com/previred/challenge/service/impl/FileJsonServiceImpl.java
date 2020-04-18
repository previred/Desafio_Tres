package com.previred.challenge.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.previred.challenge.domain.Valores;
import com.previred.challenge.error.ServiceException;
import com.previred.challenge.service.IFileService;
import com.previred.challenge.util.Util;

@Service
public class FileJsonServiceImpl implements IFileService {

	private static final Logger LOGGER = LoggerFactory.getLogger(Logger.class);
	
	private final ObjectMapper mapper;

	FileJsonServiceImpl(ObjectMapper mapper) {
		this.mapper = mapper;
	}

	@Override
	public byte[] getFile(Valores value) {
		LOGGER.debug("Start Method: getFile(..) from class: %s", this.getClass().getName());
		try {
			LOGGER.debug("Into Method that return byte[] file json");
			return Util.getByteArrayOutputStream(mapper.writeValueAsString(value));
		} catch (Exception e) {
			throw new ServiceException("Error en generar archivo Json");
		}
	}

}