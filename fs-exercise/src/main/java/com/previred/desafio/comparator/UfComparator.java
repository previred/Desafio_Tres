package com.previred.desafio.comparator;

import java.util.Comparator;

import com.previred.desafio.model.UfTO;

public class UfComparator implements Comparator<UfTO> {

	@Override
	public int compare(UfTO e1, UfTO e2) {
		return e2.getFecha().compareTo(e1.getFecha());
	}

}
