package com.previred.desafio.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.previred.desafio.tres.uf.DatosUf;
import com.previred.desafio.tres.uf.Valores;

@Configuration
public class Config {
	@Bean
	public Valores valores() {
		return new Valores();
	}
}
