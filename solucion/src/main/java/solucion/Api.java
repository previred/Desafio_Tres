package solucion;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 
 * @author Jorge Rojas
 *
 *Clase que contiene funcion main y es unida con el framework springboot
 *
 */

@SpringBootApplication
public class Api 
{
	public static void main (String[] args)
	{
		SpringApplication.run(Api.class, args);
	}
}
