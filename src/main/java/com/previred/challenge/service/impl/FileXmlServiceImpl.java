package com.previred.challenge.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.previred.challenge.domain.Valores;
import com.previred.challenge.error.ServiceException;
import com.previred.challenge.service.IFileService;
import com.previred.challenge.util.Util;

@Service
public class FileXmlServiceImpl implements IFileService {

	private static final Logger LOGGER = LoggerFactory.getLogger(FileXmlServiceImpl.class);
	
	private final XmlMapper mapper;

	FileXmlServiceImpl(XmlMapper xmlMapper) {
		this.mapper = xmlMapper;
	}

	@Override
	public byte[] getFile(Valores value) {
		LOGGER.debug("Start Method: getFile(..) from class: %s", this.getClass().getName());
		try {
			LOGGER.debug("Into Method that return byte[] file xml");
			return Util.getByteArrayOutputStream(mapper.writeValueAsString(value));
		} catch (Exception e) {
			throw new ServiceException("Error en generar archivo Xml");
		}
	}

}
