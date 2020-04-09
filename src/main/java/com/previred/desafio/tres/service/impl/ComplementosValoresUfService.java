package com.previred.desafio.tres.service.impl;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Service;
import org.supercsv.io.CsvBeanWriter;
import org.supercsv.io.ICsvBeanWriter;
import org.supercsv.prefs.CsvPreference;

import com.previred.desafio.tres.service.IComplementoValoresUfService;
import com.previred.desafio.tres.uf.DatosUf;
import com.previred.desafio.tres.uf.Valores;
import com.previred.desafio.tres.uf.vo.Uf;
import com.previred.desafio.tres.uf.vo.Ufs;
import com.previred.desafio.tres.utils.ListadoUf;
import com.previred.desafio.tres.utils.ListadoUfCsv;

@Service("complementosValoresUfService")
public class ComplementosValoresUfService implements IComplementoValoresUfService {

	/**
	 * Método inicial para la visualización en formato JSON, acá valida si recibe 1
	 * o 2, Si es 1 es para realizar un recorrido un poco más lento porque revisa
	 * los datos que existen y luego rellena los que faltan. El método 2, lo que
	 * hace es llenar los datos sólo usando como base los rangos de inicio y fin.
	 */
	@Override
	public ListadoUf getDatos(int metodo) {

		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

		Ufs rangoObtenido = this.getRangos();
		List<HashMap<String, String>> ufs = new ArrayList<HashMap<String, String>>();

		if (metodo == 1) {
			ufs = this.procesarDataOriginal(rangoObtenido);
		}

		if (metodo == 2) {
			ufs = this.procesarDataAlternativa(rangoObtenido);
		}

		ListadoUf datosEntregar = new ListadoUf();

		datosEntregar.setInicio(format.format(rangoObtenido.getInicio()));
		datosEntregar.setFin(format.format(rangoObtenido.getFin()));

		datosEntregar.setUfs(ufs);

		return datosEntregar;

	}

	/**
	 * Este método procesa la data usando un recorrido más ligero porque sólo
	 * rellena los datos en base a los parámetros de inicio y fin, sin validar si
	 * existen en los datos previamente almacenados.
	 *
	 * @param rango
	 * @return
	 */
	private ArrayList<HashMap<String, String>> procesarDataAlternativa(Ufs rango) {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

		rango.getUfs();

		Ufs rangoObtenido = this.getRangos();

		ArrayList<HashMap<String, String>> ufs = new ArrayList<HashMap<String, String>>();
		new HashMap<String, Object>();

		LocalDate start = rangoObtenido.getInicio().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
		LocalDate end = rangoObtenido.getFin().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

		for (LocalDate date = start; !date.isAfter(end); date = date.plusDays(1)) {
			Date dayActual = Date.from(date.atStartOfDay(ZoneId.systemDefault()).toInstant());

			HashMap<String, String> valorHash = new HashMap<String, String>();
			Uf obtenerValorUf = this.obtenerValorUf(dayActual);
			valorHash.put("fecha", format.format(obtenerValorUf.getFecha()));
			valorHash.put("dato", String.valueOf(obtenerValorUf.getValor()));
			ufs.add(valorHash);

		}

		return ufs;

	}

	/**
	 * Este método procesa la data usando un recorrido un poco menos ligero que el
	 * alternativo, porque este recorre los datos del rango y los contrasta con la
	 * info que ya existe y ingresa en la lista los que ya existen por un lado, y
	 * consulta los que no existen por otro lado y los agrega también a la lista
	 *
	 * @param rango
	 * @return
	 */
	private ArrayList<HashMap<String, String>> procesarDataOriginal(Ufs rango) {

		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

		Set<Uf> lstUfs = rango.getUfs();

		Ufs rangoObtenido = this.getRangos();

		ArrayList<HashMap<String, String>> ufs = new ArrayList<HashMap<String, String>>();
		new HashMap<String, Object>();

		LocalDate start = rangoObtenido.getInicio().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
		LocalDate end = rangoObtenido.getFin().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

		for (LocalDate date = start; !date.isAfter(end); date = date.plusDays(1)) {
			Date dayActual = Date.from(date.atStartOfDay(ZoneId.systemDefault()).toInstant());

			for (Uf uf : lstUfs) {
				if (!dayActual.equals(uf.getFecha())) {
					HashMap<String, String> valorHash = new HashMap<String, String>();
					Uf obtenerValorUf = this.obtenerValorUf(dayActual);
					valorHash.put("fecha", format.format(obtenerValorUf.getFecha()));
					valorHash.put("dato", String.valueOf(obtenerValorUf.getValor()));
					if (!ufs.contains(valorHash)) {
						ufs.add(valorHash);
					}

				} else {
					HashMap<String, String> valorHash = new HashMap<String, String>();
					valorHash.put("fecha", format.format(uf.getFecha()));
					valorHash.put("dato", String.valueOf(uf.getValor()));
					if (!ufs.contains(valorHash)) {
						ufs.add(valorHash);
					}

				}

			}

		}

		return ufs;

	}

	/**
	 * Este método al igual que los otros dos métodos anteriores de procesamiento de
	 * la data lo que hace es similar a los otros métodos en lo que es recuperar la
	 * información, pero la adecúa a un pojo específico para el CSV
	 *
	 * @param rango
	 * @return
	 */
	private ArrayList<ListadoUfCsv> procesarDataCsv(Ufs rango) {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

		Ufs rangoObtenido = this.getRangos();

		ArrayList<ListadoUfCsv> lstUfsCsv = new ArrayList<ListadoUfCsv>();

		ListadoUfCsv datosEntregar = new ListadoUfCsv();
		datosEntregar.setTipo("1");
		datosEntregar.setDato1(format.format(rangoObtenido.getInicio()));
		datosEntregar.setDato2(format.format(rangoObtenido.getFin()));

		lstUfsCsv.add(datosEntregar);

		LocalDate start = rangoObtenido.getInicio().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
		LocalDate end = rangoObtenido.getFin().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

		for (LocalDate date = start; !date.isAfter(end); date = date.plusDays(1)) {
			Date dayActual = Date.from(date.atStartOfDay(ZoneId.systemDefault()).toInstant());

			Uf obtenerValorUf = this.obtenerValorUf(dayActual);
			datosEntregar = new ListadoUfCsv();
			datosEntregar.setTipo("2");
			datosEntregar.setDato1(format.format(obtenerValorUf.getFecha()));
			datosEntregar.setDato2(String.valueOf(obtenerValorUf.getValor()));
			lstUfsCsv.add(datosEntregar);

		}

		return lstUfsCsv;

	}

	/**
	 * Este método se comunica con el JAR y obtiene el valor de la uf según la fecha
	 * enviada.
	 *
	 * @param dayActual
	 * @return
	 */
	private Uf obtenerValorUf(Date dayActual) {
		return DatosUf.getInstance().getUf(dayActual);
	}

	/**
	 * Este método se comunica con el JAR y obtiene el objeto para poder obtener el
	 * rango de fechas y el listado de UFs de dicho rango.
	 *
	 * @return
	 */
	private Ufs getRangos() {

		Valores obtenerDatos = new Valores();

		return obtenerDatos.getRango();
	}

	/**
	 * Este método se encarga de escribir la data procesada para CSV y
	 * posteriormente pueda ser descargada por el usuario.
	 */
	@Override
	public void exportCsv(HttpServletResponse response) throws IOException {

		new SimpleDateFormat("yyyy-MM-dd");
		response.setContentType("text/csv");

		Ufs rangoObtenido = this.getRangos();

		ArrayList<ListadoUfCsv> ufs = this.procesarDataCsv(rangoObtenido);

		response.setContentType("text/csv");

		String headerKey = "Content-Disposition";
		String headerValue = String.format("attachment; filename=\"%s\"", "valores.csv");
		response.setHeader(headerKey, headerValue);

		ICsvBeanWriter csvWriter = new CsvBeanWriter(response.getWriter(), CsvPreference.EXCEL_NORTH_EUROPE_PREFERENCE);

		String[] header = { "tipo", "dato1", "dato2" };

		for (ListadoUfCsv uf : ufs) {
			csvWriter.write(uf, header);
		}

		csvWriter.close();

	}

}
