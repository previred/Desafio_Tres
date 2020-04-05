package com.cromero.previred.services.utils;

import java.util.Comparator;

import com.cromero.previred.dto.UFDTO;

public class SortByUFDate implements Comparator<UFDTO> {

	@Override
	public int compare(UFDTO a, UFDTO b) {
		return b.getFecha().compareTo(a.getFecha());
	}

}
