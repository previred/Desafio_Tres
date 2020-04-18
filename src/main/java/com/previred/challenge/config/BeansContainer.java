package com.previred.challenge.config;

import java.text.SimpleDateFormat;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.fasterxml.jackson.dataformat.xml.ser.ToXmlGenerator;
import com.fasterxml.jackson.datatype.guava.GuavaModule;
import com.fasterxml.jackson.module.jaxb.JaxbAnnotationModule;
import com.previred.challenge.util.Util;

@Configuration
public class BeansContainer {

	@Bean("objectMapper")
	@Primary
	public ObjectMapper objectMapper() {
		return new ObjectMapper()
				.enable(MapperFeature.USE_STD_BEAN_NAMING)
				.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS)
				.setDateFormat(new SimpleDateFormat(Util.FORMAT_DATE))
				.enable(SerializationFeature.INDENT_OUTPUT);
	}
	
	@Bean("xmlMapper")
	public XmlMapper xmlMapper() {
		XmlMapper xmlMapper = new XmlMapper();
		JaxbAnnotationModule jaxbAnnotationModule = new JaxbAnnotationModule();
		xmlMapper
				.configure(ToXmlGenerator.Feature.WRITE_XML_DECLARATION, true)
				.enable(MapperFeature.USE_STD_BEAN_NAMING)
				.enable(MapperFeature.AUTO_DETECT_FIELDS)
				.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS)
				.setDateFormat(new SimpleDateFormat(Util.FORMAT_DATE))
				.enable(SerializationFeature.INDENT_OUTPUT)
				.registerModule(jaxbAnnotationModule)
				.registerModule(new GuavaModule());
		return xmlMapper;
	}
}
