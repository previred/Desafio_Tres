package desafio3;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan
public class DesafioTres {
	public static void main(String[] args) {
		SpringApplication.run(DesafioTres.class, args);
	}
	
}
