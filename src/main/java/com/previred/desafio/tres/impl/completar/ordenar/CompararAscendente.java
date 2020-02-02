package com.previred.desafio.tres.impl.completar.ordenar;

import java.util.Comparator;

import com.previred.desafio.tres.uf.vo.Uf;

public class CompararAscendente implements Comparator<Uf> {

	@Override
	public int compare(Uf uf1, Uf uf2) {
		if (validsParams(uf1, uf2)) {
			return 0;
		}
		if (uf1.getFecha().getTime() < uf2.getFecha().getTime()) {
			return -1;
		} else {
			return 1;
		}
	}

	private boolean validsParams(Uf uf1, Uf uf2) {
		return (!validUf(uf1)) || (!validUf(uf2));
	}

	private boolean validUf(Uf uf) {
		return (uf != null && uf.getFecha() != null);
	}

}
