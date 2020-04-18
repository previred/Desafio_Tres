package com.previred.challenge.service.impl;

import java.text.SimpleDateFormat;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.previred.challenge.domain.Uf;
import com.previred.challenge.domain.Valores;
import com.previred.challenge.error.ServiceException;
import com.previred.challenge.service.IFileService;
import com.previred.challenge.util.Util;

@Service
public class FileCsvServiceImpl implements IFileService {

	private static final Logger LOGGER = LoggerFactory.getLogger(FileCsvServiceImpl.class);
	
	private final static String FORMAT_LINE_CSV = "%d; %s; %s \n";

	@Override
	public byte[] getFile(Valores value) {
		LOGGER.debug("Start Method: getFile(..) from class: %s", this.getClass().getName());
		try {
			LOGGER.debug("Into Method that return byte[] file csv");
			return Util.getByteArrayOutputStream(createDataCsv(value));
		} catch (Exception e) {
			throw new ServiceException("Error en generar archivo Csv");
		}
	}
	
	private String createDataCsv(Valores value) {
		LOGGER.debug("Start Method: createDataCsv(..) from class: %s", this.getClass().getName());
		StringBuilder stringbuilder = new StringBuilder();
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(Util.FORMAT_DATE);
		LOGGER.debug("Start first line for csv file");
		stringbuilder.append(String.format(FORMAT_LINE_CSV, 1, simpleDateFormat.format(value.getInicio()), simpleDateFormat.format(value.getFin())));
		LOGGER.debug("Starting to bucle for write secundary line csv file");
		for(Uf uf : value.getUFs()) {
			stringbuilder.append(String.format(FORMAT_LINE_CSV, 2, simpleDateFormat.format(uf.getFecha()), uf.getDato()));	
		}
		LOGGER.debug("Finish to bucle for write secundary line csv file");
		return stringbuilder.toString();
	}
}
