package com.previred.desafio.util;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import java.util.logging.Logger;

import org.junit.Test;
import org.junit.jupiter.api.Assertions;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.previred.desafio.comparator.UfComparator;
import com.previred.desafio.model.UfTO;
import com.previred.desafio.tres.uf.DatosUf;
import com.previred.desafio.tres.uf.Valores;
import com.previred.desafio.tres.uf.vo.Uf;
import com.previred.desafio.tres.uf.vo.Ufs;

public class UtilTest {

	private static final Logger LOGGER = Logger.getLogger(UtilTest.class.getName());

	private Valores uf;

	public UtilTest() {
		uf = new Valores();
	}

	@Test
	public void objectToString() {
		Ufs ufs = uf.getRango();
		ObjectMapper obj = new ObjectMapper();
		try {
			String jsonStr = obj.writeValueAsString(ufs);
			Assertions.assertNotNull(jsonStr);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void parseDateToString() {
		SimpleDateFormat parseador = new SimpleDateFormat("yyyy-MM-dd");
		Date date = new Date();
		String result = parseador.format(date);
		LOGGER.info(result);
		Assertions.assertNotNull(result);
	}

	@Test
	public void mergeSet() {
		// fuente a completar
		Set<UfTO> rango = getAllUFs();

		// fuente de ufs
		Ufs ufs = uf.getRango();
		Set<UfTO> valores = getUFsByDateInicioFin(ufs.getInicio(), ufs.getFin());

		int sizeRangoOriginal = rango.size();
		int sizeValoresOriginal = valores.size();
		int totalOriginal = sizeRangoOriginal + sizeValoresOriginal;
		LOGGER.info("tamaño original rango:" + sizeRangoOriginal + " tamaño original valores:" + sizeValoresOriginal
				+ " totalOriginal:" + totalOriginal);
		Set<UfTO> tree = Util.mergeSet(rango, valores);

		
		Assertions.assertNotNull(tree);
		LOGGER.info("tamaño rango post proceso:" + rango.size() + " tamaño valores post proceso:" + valores.size()
				+ " total post proceso:" + tree.size());
		
		
	}

	private Set<UfTO> getUFsByDateInicioFin(Date inicio, Date fin) {
		Set<UfTO> valores = new TreeSet<>(new UfComparator());
		List<Uf> ufList = DatosUf.getInstance().getUfs(inicio, fin);
		for (Uf u : ufList) {
			UfTO ufto = new UfTO();
			ufto.setFecha(Util.parseDateToString(u.getFecha()));
			ufto.setDato(u.getValor());
			valores.add(ufto);
		}
		return valores;
	}

	private Set<UfTO> getAllUFs() {
		Ufs ufs = uf.getRango();
		TreeSet<UfTO> setUFs = new TreeSet<>(new UfComparator());
		for (Uf uf : ufs.getUfs()) {
			UfTO ufTO = new UfTO();
			ufTO.setFecha(Util.parseDateToString(uf.getFecha()));
			ufTO.setDato(uf.getValor());
			setUFs.add(ufTO);
		}
		return setUFs;
	}

	 

}
