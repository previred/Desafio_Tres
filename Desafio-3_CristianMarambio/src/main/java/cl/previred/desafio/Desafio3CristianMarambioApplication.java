package cl.previred.desafio;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import cl.previred.desafio.model.UfObject;
import cl.previred.desafio.model.UfsList;
import cl.previred.desafio.util.Utils;

import org.apache.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import com.previred.desafio.tres.uf.vo.Uf;
import com.google.gson.Gson;
import com.google.gson.JsonIOException;
import com.previred.desafio.tres.uf.Valores;
import com.previred.desafio.tres.uf.vo.Ufs;
import com.previred.desafio.tres.uf.DatosUf;

@SpringBootApplication
public class Desafio3CristianMarambioApplication {
	
	private static final Logger logger = Logger.getLogger(Desafio3CristianMarambioApplication.class);
	private static final String path = "valores.json";
	private static final String pattern = "yyyy/MM/dd";

	public static void main(String[] args) throws ParseException {
		SpringApplication.run(Desafio3CristianMarambioApplication.class, args);

		DateFormat formatoFecha = new SimpleDateFormat(pattern);
		Utils util = new Utils();
		Valores v = new Valores();
		Ufs listaConLagunas = v.getRango();
		// lista con lagunas ordenada
		List<Uf> listaConLagunasOrdenada = util.orderByDate(listaConLagunas);

		Date fechaInicio = listaConLagunas.getInicio();
		Date fechaFin = listaConLagunas.getFin();

		// Lista todas las fechas
		List<Uf> listaCompletaFechas = DatosUf.getInstance().getUfs(fechaInicio, fechaFin);

		List<Uf> fechasFiltradas = util.getFilterDates(listaConLagunasOrdenada, listaCompletaFechas);

		for (Uf u : fechasFiltradas) {
			listaConLagunasOrdenada.add(u);
		}

		// ordenamos por fechas descendiente
		listaConLagunasOrdenada.sort(Comparator.comparing(Uf::getFecha).reversed());

		try {
			UfsList json = new UfsList();
			json.setInicio(formatoFecha.format(fechaInicio));
			json.setFin(formatoFecha.format(fechaFin));
			for (Uf uf : listaConLagunasOrdenada) {
				UfObject obj = new UfObject();
				obj.setFecha(formatoFecha.format(uf.getFecha()));
				obj.setDato(uf.getValor().toString());
				json.getUfs().add(obj);
			}
			logger.info("json ---->  " + json);
			Gson g = new Gson();
			Writer writer = new FileWriter(path);
			g.toJson(json, writer);
			writer.close();
		} catch (JsonIOException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
}
