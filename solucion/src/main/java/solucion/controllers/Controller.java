package solucion.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import solucion.entities.MisUF;
import solucion.services.UFService;
/**
 * 
 * @author Jorge Rojas
 *
 * Esta clase maneja una llamada HTTP GET desde localgost:8080/getUF, para retornar un archivo json descargable
 * con todas las Ufs de un periodo de tiempo determinado. 
 *
 */
@RestController
public class Controller 
{
	@Autowired
	private UFService genera_uf;
	
	@RequestMapping(value = "/getUF", method = RequestMethod.GET, produces = { "application/json" })
	public ResponseEntity<MisUF> delivery()
	{
		String nombre_archivo = "valores.json";
		HttpHeaders headers = new HttpHeaders();
		headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename="+nombre_archivo);
		headers.setContentType(MediaType.parseMediaType("application/json"));
		System.out.println("Entregando paquete con nombre: " +nombre_archivo);
		return new ResponseEntity<MisUF>( genera_uf.entrega_UF() ,headers, HttpStatus.OK);

	}
}