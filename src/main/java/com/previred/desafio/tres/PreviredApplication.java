package com.previred.desafio.tres;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan({"com.previred"})
public class PreviredApplication {

	public static void main(String[] args) {
		SpringApplication.run(PreviredApplication.class, args);
	}

}
