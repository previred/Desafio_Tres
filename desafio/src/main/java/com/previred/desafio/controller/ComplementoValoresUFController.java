package com.previred.desafio.controller;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;
import com.previred.desafio.tres.uf.DatosUf;
import com.previred.desafio.tres.uf.Valores;
import com.previred.desafio.tres.uf.vo.Uf;
import com.previred.desafio.tres.uf.vo.Ufs;

@RestController
@RequestMapping("/previred")
public class ComplementoValoresUFController {

	final static Logger log = Logger.getLogger(ComplementoValoresUFController.class);

	@GetMapping("/ufs")
	public ResponseEntity<?> mostrarUfs() {

		log.info("Inicia el proceso para obtener ufs en un determinado rango.");

		Map<String, Object> response = new HashMap<>();
		Date fechaActual, fechaSiguiente;
		DatosUf datosUf = DatosUf.getInstance();
		Valores valores = new Valores();
		Ufs ufsSalida = new Ufs();

		try {

			log.info("Obtengo los valores que proporciona el jar.");

			Ufs ufs = valores.getRango();
			Set<Uf> setUfs = ufs.getUfs();

			log.info("Envió las ufs a una lista y las ordeno por fechas.");

			List<Uf> lstUf = setUfs.stream().collect(Collectors.toList());
			Collections.sort(lstUf, (uf1, uf2) -> uf1.getFecha().compareTo(uf2.getFecha()));

			log.info(
					"Envió la fecha actual y la fecha siguiente, donde getUfs retorna las lagunas de valores entre estas fechas.");

			for (int i = 0; i < lstUf.size() - 1; i++) {
				fechaActual = lstUf.get(i).getFecha();
				fechaSiguiente = lstUf.get(i + 1).getFecha();
				setUfs.addAll(datosUf.getUfs(fechaActual, fechaSiguiente));
			}

			lstUf = setUfs.stream().collect(Collectors.toList());
			Collections.sort(lstUf, (uf1, uf2) -> uf1.getFecha().compareTo(uf2.getFecha()));

			log.info("Se crea un nuevo set instanciado con un LinkedHashSet para conserva el orden.");

			Set<Uf> ordenSetUf = new LinkedHashSet<Uf>(lstUf);

			log.info("Se envían los nuevos valores en ufsSalida.");

			ufsSalida.setInicio(ufs.getInicio());
			ufsSalida.setFin(ufs.getFin());
			ufsSalida.setUfs(ordenSetUf);

			log.info("Se genera el archivo json.");

			generarArchivoJson(ufsSalida);

		} catch (Exception e) {
			response.put("mensaje", "Ocurrio un error");
			response.put("error", e.getMessage().concat(": ").concat(e.getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		return new ResponseEntity<Ufs>(ufsSalida, HttpStatus.OK);
	}

	public void generarArchivoJson(Ufs ufsSalida) {

		try (FileWriter file = new FileWriter("valores.json")) {

			Gson gson = new Gson();
			String salidaJson = gson.toJson(ufsSalida, Ufs.class);
			file.write(salidaJson);
			file.flush();
			log.info("¡Se crea con éxito el archivo!.");

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	@GetMapping(value = "/zipValores", produces = "application/zip")
	public void zipDownload(HttpServletResponse response) throws IOException {
		try {
			log.info("Inicia el proceso para obtener un archivo comprimido con el archivo de salida");

			ZipOutputStream zipSalida = new ZipOutputStream(response.getOutputStream());
			FileSystemResource archivoValores = new FileSystemResource(
					System.getProperty("user.dir").concat("\\valores.json"));

			ZipEntry zip = new ZipEntry(archivoValores.getFilename());
			zip.setSize(archivoValores.contentLength());
			zipSalida.putNextEntry(zip);
			StreamUtils.copy(archivoValores.getInputStream(), zipSalida);
			zipSalida.closeEntry();

			zipSalida.finish();
			zipSalida.close();

			log.info("¡Se crea con éxito el archivo!.");
			response.setStatus(HttpServletResponse.SC_OK);
			response.addHeader(HttpHeaders.CONTENT_DISPOSITION, "");
		} catch (IOException e) {
			response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
			response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Ocurrio un error ".concat(e.getMessage()));
		}

	}

}
