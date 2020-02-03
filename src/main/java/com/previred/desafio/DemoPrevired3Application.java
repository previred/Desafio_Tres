package com.previred.desafio;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.previred.desafio.gcasanova.ResolucionDesafio;

@SpringBootApplication
public class DemoPrevired3Application implements CommandLineRunner {

	@Autowired
	ResolucionDesafio resDesafio;

	public static void main(String[] args) {

		SpringApplication app = new SpringApplication(DemoPrevired3Application.class);
		app.run(args);
	}

	@Override
	public void run(String... args) throws Exception {

		resDesafio.resolution();

	}

}
