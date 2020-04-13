package com.navastud.complementouf;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.previred.desafio.tres.uf.DatosUf;
import com.previred.desafio.tres.uf.vo.Uf;
import com.previred.desafio.tres.uf.vo.Ufs;

@SpringBootApplication
public class ComplementoUfApplication {

	public static void main(String[] args) {
		SpringApplication.run(ComplementoUfApplication.class, args);
	}

	@Configuration
	public class ConfigurationComplementoUfApplication {

		@Bean
		public Ufs ufs() {
			return new Ufs();
		}

		@Bean
		public Uf uf() {
			return new Uf();
		}

		@Bean
		public DatosUf datosUf() {
			return DatosUf.getInstance();
		}

	}

}
