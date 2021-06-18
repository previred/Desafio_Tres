package com.desafio3.valoresuf;

import com.desafio3.valoresuf.service.ValoresUfServicio;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.ExitCodeGenerator;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ValoresufApplication implements CommandLineRunner {

	@Autowired
	ValoresUfServicio valoresUfServicio;

	public static void main(String[] args) {
		SpringApplication.run(ValoresufApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		if (args.length > 0 && args[0].equals("exitcode")) {
			throw new ExitException();
		}

		//Create JSON object UF
		JSONObject ufs = valoresUfServicio.getRango();

		//Write file valores.json
		valoresUfServicio.writeFile(ufs);
	}

	class ExitException extends RuntimeException implements ExitCodeGenerator {
		private static final long serialVersionUID = 1L;

		@Override
		public int getExitCode() {
			return 10;
		}

	}

}
