package cl.previred.desafio.tres.util;

import java.io.FileWriter;
import java.time.LocalDate;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.previred.desafio.tres.uf.vo.Uf;

import cl.previred.desafio.tres.constants.JSONConstants;

public class FileUtil {

	private static final Logger LOG = LoggerFactory.getLogger(FileUtil.class);

	//Se deja en un hilo de ejecucion independiente para mejorar la experiencia del usuario
	@Async
	public static void createJson(LocalDate fechaInicio, LocalDate fechaTermino, List<Uf> ufs) {
		LOG.debug("Inciando la creacion del archivo JSON");

		try {
			Map<String, Object> map = new LinkedHashMap<>();
			map.put("inicio", DatesUtil.toDateFormat(fechaInicio));
			map.put("fin", DatesUtil.toDateFormat(fechaTermino));
			map.put("UFs", ufs);
			
			Gson gson = new GsonBuilder()
					.serializeNulls()
					.setDateFormat(JSONConstants.JSON_DATE_FORMAT)
					.setFieldNamingStrategy(f -> f.getName().replaceAll(JSONConstants.JSON_VALOR_PATTERN, JSONConstants.JSON_VALOR_NAME))
					.setPrettyPrinting()
					.create();

			FileWriter file = new FileWriter(JSONConstants.JSON_PATH_FILE);
			file.write(gson.toJson(map));
			file.close();

		} catch (Exception e) {
			LOG.error("Error al crear el archivo JSON", e);
		}

		LOG.info("Archivo JSON creado en: {}", JSONConstants.JSON_PATH_FILE);
	}

}
