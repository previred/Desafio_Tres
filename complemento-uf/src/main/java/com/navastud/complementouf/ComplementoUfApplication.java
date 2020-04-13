package com.navastud.complementouf;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.navastud.complementouf.dto.UfDto;
import com.navastud.complementouf.dto.UfsDto;
import com.previred.desafio.tres.uf.DatosUf;
import com.previred.desafio.tres.uf.Valores;
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
		public Valores valores() {
			return new Valores();
		}

		@Bean
		public Ufs ufs() {
			return valores().getRango();
		}

		@Bean
		public Uf uf() {
			return new Uf();
		}

		@Bean
		public DatosUf datosUf() {
			return DatosUf.getInstance();
		}

		@Bean
		public UfsDto ufsDto() {
			return new UfsDto();
		}

		@Bean
		public UfDto ufDto() {
			return new UfDto();
		}
	}

}
