package com.previred.desafio.tres.service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.previred.desafio.tres.dto.RespuestaDto;
import com.previred.desafio.tres.dto.UfDto;
import com.previred.desafio.tres.dto.UfsDto;
import com.previred.desafio.tres.uf.DatosUf;
import com.previred.desafio.tres.uf.Valores;
import com.previred.desafio.tres.uf.vo.Uf;
import com.previred.desafio.tres.uf.vo.Ufs;

/**
 * Servicio que permite manejar datos asociados a la UF
 * 
 * @author Luis San Martin
 *
 */
public class ComplementoValoresService {

	private static final Logger LOGGER = Logger.getLogger(ComplementoValoresService.class.getName());
	private static final int LARGO_LISTA_UFS = 100;

	/**
	 * Metodo que permite producir un un archivo json con 100 datos de UF
	 * 
	 * @return
	 */
	public RespuestaDto produceComplementoValores() {
		RespuestaDto respuesta = new RespuestaDto();
		try {
			Valores valores = new Valores();

			Ufs rango = valores.getRango();
			Date fechaInicio = rango.getInicio();
			Date fechaFin = rango.getFin();

			Set<Uf> ufs = rango.getUfs();

			if (ufs.size() < LARGO_LISTA_UFS) {
				List<Uf> ufs2 = DatosUf.getInstance().getUfs(fechaInicio, fechaFin);
				ufs.addAll(obtieneUfsFaltantes(ufs, ufs2, LARGO_LISTA_UFS - ufs.size()));
			}

			List<Uf> ufsList = new ArrayList<>();
			ufsList.addAll(ufs);

			Collections.sort(ufsList, (uf1, uf2) -> uf2.getFecha().compareTo(uf1.getFecha()));

			UfsDto dto = new UfsDto();
			dto.setInicio(fechaInicio);
			dto.setFin(fechaFin);

			List<UfDto> ufDtoList = llenaUfDto(ufsList.subList(0, LARGO_LISTA_UFS));

			dto.setUfs(ufDtoList);

			String archivoSalida = escribeJson(dto);

			respuesta.setCodigo(0);
			respuesta.setMensaje("el archivo " + archivoSalida + " fue creado correctamente");

		} catch (Exception e) {
			respuesta.setCodigo(1);
			respuesta.setMensaje("Ocurrio un error al producir el listado de ufs: " + e.toString());
			LOGGER.log(Level.SEVERE, e.toString());
		}

		return respuesta;
	}

	/**
	 * Permite llenar el objeto UfDto
	 * 
	 * @param listaUfs
	 * @return
	 */
	private List<UfDto> llenaUfDto(List<Uf> listaUfs) {
		List<UfDto> ufDtos = new ArrayList<>();
		for (Uf uf : listaUfs) {
			UfDto ufDto = new UfDto();
			ufDto.setDato(uf.getValor());
			ufDto.setFecha(uf.getFecha());
			ufDtos.add(ufDto);
		}
		return ufDtos;
	}

	/**
	 * Devuelve un Set con las Ufs faltantes
	 * 
	 * @param ufs
	 * @param ufs2
	 * @param cantUfs
	 * @return
	 */
	private Set<Uf> obtieneUfsFaltantes(Set<Uf> ufs, List<Uf> ufs2, int cantUfs) {
		Set<Uf> ufsFaltantes = new HashSet<>();

		int c = 0;
		for (Uf uf : ufs2) {
			if (!ufs.contains(uf)) {
				ufsFaltantes.add(uf);
				c++;
			}
			if (c == cantUfs) {
				break;
			}
		}
		return ufsFaltantes;
	}

	/**
	 * Escribe el listado de Ufs en un archivo json
	 * 
	 * @param dto
	 * @return
	 * @throws IOException
	 */
	protected String escribeJson(UfsDto dto) throws IOException {

		Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").setPrettyPrinting().create();
		String json = gson.toJson(dto);
		LOGGER.log(Level.FINER, json);

		File file = new File("valores.json");

		if (file.exists()) {
			Files.delete(Paths.get(file.getAbsolutePath()));
		}
		if (file.createNewFile()) {
			Path path = Paths.get(file.getAbsolutePath());
			Files.write(path, json.getBytes(), StandardOpenOption.TRUNCATE_EXISTING);
		}

		return file.getAbsolutePath();
	}

}
