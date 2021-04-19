package com.previred.desafio.tres.complementouf;

import java.io.File;
import java.text.SimpleDateFormat;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.previred.desafio.tres.complementouf.vo.ComplementoUF;

@SpringBootApplication
public class ComplementoUfApplication implements CommandLineRunner{
	
	@Value("${out.file.name}")
	private String nombreArchivo;
	
	private static final Logger log = LoggerFactory.getLogger(ComplementoUfApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(ComplementoUfApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		ComplementoValoresUf complementoValoresUf = new ComplementoValoresUf();
		ComplementoUF complemento = complementoValoresUf.determinarComplemento();
		
		ObjectMapper mapper = new ObjectMapper();
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		mapper.setVisibility(PropertyAccessor.FIELD, Visibility.DEFAULT);
		mapper.setDateFormat(df);
		
		log.info("Generando archivo " + nombreArchivo + " ...");
		File archivo = new File(nombreArchivo);
		mapper.writerWithDefaultPrettyPrinter().writeValue(archivo, complemento);
		log.info("Archivo generado exitosamentente.  Path: " + archivo.getAbsolutePath());
		
	}

}
