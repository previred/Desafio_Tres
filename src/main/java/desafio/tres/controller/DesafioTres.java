package desafio.tres.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.slf4j.Logger;
import com.google.gson.Gson;
import com.previred.desafio.tres.uf.DatosUf;
import com.previred.desafio.tres.uf.Valores;
import com.previred.desafio.tres.uf.vo.Uf;
import com.previred.desafio.tres.uf.vo.Ufs;

import desafio.tres.dto.RespUF;
import desafio.tres.dto.RespuestaDTO;

@RestController
@RequestMapping("desafioTres")
public class DesafioTres {


    static final Logger LOGGER=LoggerFactory.getLogger(DesafioTres.class);
	
	@RequestMapping("/getComplementoValoresUf")
	public ResponseEntity<byte[]> getComplementoValoresUf(HttpServletResponse response) {
		
		LOGGER.info("inicio:getComplementoValoresUf");
		RespuestaDTO respuesta = new RespuestaDTO();
		Gson gson = new Gson();

		Valores valores = new Valores();
		Ufs ufs = valores.getRango();
		Date f1 = ufs.getInicio();
		Date f2 = ufs.getFin();

		List<Uf> listUf = ufs.getUfs().stream().collect(Collectors.toList());
		DatosUf datosUf = DatosUf.getInstance();

		complementaListadoUfs(datosUf.getUfs(f1, f2), listUf);
		generaRespuesta(respuesta, f1, f2, listUf);
		
		LOGGER.info("fin:getComplementoValoresUf");
		
		return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=valores.json")
				.contentType(MediaType.APPLICATION_JSON).body(gson.toJson(respuesta).getBytes());
		
	}

	private void complementaListadoUfs(List<Uf> valoresUfRango, List<Uf> listUf) {
		
		LOGGER.info("inicio:complementaListadoUfs");
		try {
		valoresUfRango.forEach(ufRango -> {
			Date aux = ufRango.getFecha();
			boolean existe = listUf.stream().filter(d -> d.getFecha().equals(aux)).findFirst().isPresent();
			if (!existe) {
				listUf.add(ufRango);
			}
		});

		Collections.sort(listUf, new Comparator<Uf>() {
			public int compare(Uf o1, Uf o2) {
				return o2.getFecha().compareTo(o1.getFecha());
			}
		});
		}catch(Exception e) {
			LOGGER.error("exception e: "+e.getStackTrace());
		}
		LOGGER.info("fin:complementaListadoUfs");
	}

	private void generaRespuesta(RespuestaDTO respuesta, Date f1, Date f2, List<Uf> listUf) {
		LOGGER.info("inicio:generaRespuesta");
		try {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		respuesta.setInicio(format.format(f1));
		respuesta.setFin(format.format(f2));
		List<RespUF> listRespUf = new ArrayList<RespUF>();

		for (Uf uf : listUf) {
			RespUF respUf = new RespUF();
			respUf.setDato(String.valueOf(uf.getValor()));
			respUf.setFecha(format.format(uf.getFecha()));
			listRespUf.add(respUf);
		}

		respuesta.setUFs(listRespUf);
		}catch(Exception e) {
			LOGGER.error("Exception e: "+e.getStackTrace());
		}

		LOGGER.info("fin:generaRespuesta");
	}

}
