package com.previred.desafio.service;

import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.previred.desafio.comparator.UfComparator;
import com.previred.desafio.model.UfTO;
import com.previred.desafio.model.UfsTO;
import com.previred.desafio.tres.uf.DatosUf;
import com.previred.desafio.tres.uf.Valores;
import com.previred.desafio.tres.uf.vo.Uf;
import com.previred.desafio.tres.uf.vo.Ufs;
import com.previred.desafio.util.Util;

@Service
public class UfServiceImpl implements UfService {

	private static final Logger LOGGER = Logger.getLogger(UfServiceImpl.class.getName());

	@Autowired
	private Valores uf;

	public UfsTO getRango() {
		Ufs ufs = uf.getRango();
		UfsTO ufsTO = new UfsTO();
		TreeSet<UfTO> setUFs = new TreeSet<>(new UfComparator());
		ufsTO.setInicio(Util.parseDateToString(ufs.getInicio()));
		ufsTO.setFin(Util.parseDateToString(ufs.getFin()));

		for (Uf uf : ufs.getUfs()) {
			UfTO ufTO = new UfTO();
			ufTO.setFecha(Util.parseDateToString(uf.getFecha()));
			ufTO.setDato(uf.getValor());
			setUFs.add(ufTO);
		}
		ufsTO.setUFs(setUFs);
		return ufsTO;
	}

	@Override
	public UfsTO generateJSON() {

		LOGGER.info("generateJSON");
		UfsTO ufsTO = new UfsTO();

		// fuente
		Set<UfTO> rango = getAllUFs();
		// fuente
		Ufs ufs = uf.getRango();
		Set<UfTO> valores = getUFsByDateInicioFin(ufs.getInicio(), ufs.getFin());

		ufsTO.setInicio(Util.parseDateToString(ufs.getInicio()));
		ufsTO.setFin(Util.parseDateToString(ufs.getFin()));

		Set<UfTO> tree = Util.mergeSet(rango, valores);

		ufsTO.setUFs(tree);

		return ufsTO;
	}

	@Override
	public Uf getUf(Date fecha) {
		DatosUf dato = DatosUf.getInstance();
		return dato.getUf(fecha);
	}

	private Set<UfTO> getUFsByDateInicioFin(Date inicio, Date fin) {
		Set<UfTO> valores = new TreeSet<>(new UfComparator());
		// fuente de datos
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
		// fuente de datos
		Ufs ufs = uf.getRango();
		Set<UfTO> setUFs = new TreeSet<>(new UfComparator());
		for (Uf u : ufs.getUfs()) {
			UfTO ufTO = new UfTO();
			ufTO.setFecha(Util.parseDateToString(u.getFecha()));
			ufTO.setDato(u.getValor());
			setUFs.add(ufTO);
		}
		return setUFs;
	}

}
