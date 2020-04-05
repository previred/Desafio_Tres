package com.previred;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DesafioTresApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(DesafioTresApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("aplicación de consola con spring boot");
		
		ComplementoValoresUF cvuf = new ComplementoValoresUF();
		cvuf.getRango();
		
	}

}
