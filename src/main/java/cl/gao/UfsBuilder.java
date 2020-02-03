package cl.gao;

import static cl.gao.PairGenerator.getPairs;
import static java.time.temporal.ChronoUnit.DAYS;
import static java.util.Comparator.comparing;
import static java.util.stream.Collectors.toList;
import static java.util.stream.Collectors.toSet;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.Date;
import java.util.LinkedHashSet;
import java.util.List;

import com.previred.desafio.tres.uf.DatosUf;
import com.previred.desafio.tres.uf.vo.Uf;
import com.previred.desafio.tres.uf.vo.Ufs;

import cl.gao.PairGenerator.Pair;

public class UfsBuilder {
	private Ufs ufs;

	private UfsBuilder() {
	}

	static public UfsBuilder from(Ufs ufsArg) {
		UfsBuilder builder = new UfsBuilder();
		builder.ufs = ufsArg;
		return builder;
	}

	public UfsBuilder ordenarDescendentePorFecha() {
		ufs.setUfs(ufs.getUfs().stream().sorted(comparing(Uf::getFecha).reversed()).collect(LinkedHashSet::new,LinkedHashSet::add,LinkedHashSet::addAll));
		return this;
	}

	public UfsBuilder rellenarDiasFaltantes(DatosUf datosUf) {
		List<Pair<Uf>> ufPairList = obtenerParesDeUfOrdenadosPorFechaAscendente();
		ArrayList<Uf> nuevosValores = new ArrayList<Uf>();
		for (Pair<Uf> ufPair : ufPairList) {
			nuevosValores.add(ufPair.firstItem);
			Instant fechaDesde = ufPair.firstItem.getFecha().toInstant();
			Instant fechaHasta = ufPair.secondItem.getFecha().toInstant();
			int cantidadDeDiasFaltantes = (int) DAYS.between(DAYS.addTo(fechaDesde, 1), fechaHasta);
			if (cantidadDeDiasFaltantes > 0) {
				Date rangoFaltanteFechaDesde = Date.from(DAYS.addTo(fechaDesde, 1));
				Date rangoFaltanteFechaHasta = Date.from(DAYS.addTo(fechaHasta, -1));
				nuevosValores.addAll(datosUf.getUfs(rangoFaltanteFechaDesde, rangoFaltanteFechaHasta));
			}
			nuevosValores.add(ufPair.secondItem);
		}
		ufs.setUfs(nuevosValores.stream().collect(toSet()));
		return this;
	}

	public Ufs build() {
		return ufs;
	}

	private List<Pair<Uf>> obtenerParesDeUfOrdenadosPorFechaAscendente() {
		return getPairs(newSortedList(ufs.getUfs(), comparing(Uf::getFecha)));
	}

	<T> List<T> newSortedList(Collection<T> collection, Comparator<T> comparator) {
		return collection.stream().sorted(comparator).collect(toList());
	}
}
