package desafio;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Set;

import com.google.gson.Gson;
import com.previred.desafio.tres.uf.DatosUf;
import com.previred.desafio.tres.uf.Valores;
import com.previred.desafio.tres.uf.vo.Uf;

import dto.UfDTO;
import dto.ValoresDTO;

public class ValorUF {

	private ValorUF() {
	}

	public static void main(String[] args) throws IOException {

		Valores valores = new Valores();
		Date fechaInicio = valores.getRango().getInicio();
		Date fechaTermino = valores.getRango().getFin();

		LocalDate start = fechaInicio.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
		LocalDate end = fechaTermino.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

		System.out.println("Fecha de Inicio : " + start);
		System.out.println("Fecha de Termino: " + end);

		List<UfDTO> listaUfs = new ArrayList<>();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		ZoneId defaultZoneId = ZoneId.systemDefault();

		Set<Uf> items = valores.getRango().getUfs();

		for (LocalDate date = start; date.isBefore(end.plusDays(1)); date = date.plusDays(1)) {
			Boolean existe = Boolean.FALSE;
			Date fechaDate = Date.from(date.atStartOfDay(defaultZoneId).toInstant());
			for (Uf item : items) {
				if (item.getFecha().equals(fechaDate)) {
					String strDate = formatter.format(fechaDate);
					UfDTO uf = new UfDTO();
					uf.setValor(item.getValor());
					uf.setFecha(strDate);
					listaUfs.add(uf);
					existe = Boolean.TRUE;
					break;
				}
			}
			if (!existe) {
				Uf ufFecha = DatosUf.getInstance().getUf(fechaDate);
				String strDate = formatter.format(ufFecha.getFecha());
				UfDTO uf = new UfDTO();
				uf.setValor(ufFecha.getValor());
				uf.setFecha(strDate);
				listaUfs.add(uf);
			}
		}

		listaUfs.sort(Comparator.comparing(UfDTO::getFecha).reversed());

		ValoresDTO valoresDTO = new ValoresDTO();
		valoresDTO.setInicio(start.toString());
		valoresDTO.setFin(end.toString());
		valoresDTO.setUfs(listaUfs);

		generarArchivo(generarJson(valoresDTO));

	}

	public static void generarArchivo(String jsonStr) throws IOException {
		
		String fileName = "valores.json";
		Path path = Paths.get("file/" + fileName);
		Files.createDirectories(path.getParent());
		if( !Files.exists(path))
		       Files.createFile(path);
		Files.write(path, (jsonStr).getBytes());
		System.out.println("Archivo JSON generado en: " + path.toAbsolutePath());
	}

	public static String generarJson(ValoresDTO valoresDTO) {
		Gson gsonObj = new Gson();
		String jsonStr2 = gsonObj.toJson(valoresDTO);
		return jsonStr2;
	}

	public static Date obtenerFechaDeString(String fechaStr, String formato) {
		Date fechaFinal = null;
		SimpleDateFormat parseador = new SimpleDateFormat(formato);
		try {
			fechaFinal = parseador.parse(fechaStr);
		} catch (ParseException e) {
			fechaFinal = Calendar.getInstance().getTime();
		}
		return fechaFinal;
	}

}
