package com.previred.desafio.comparator;

import java.util.TreeSet;

import org.junit.Test;

import com.previred.desafio.model.UfTO;

public class UfComparatorTest {

	@Test
	public void testComparator() {
		UfTO uf1 = new UfTO();
		uf1.setFecha("2020-02-01");
		uf1.setDato(1.1);
		UfTO uf2 = new UfTO();
		uf2.setFecha("2020-02-15");
		uf2.setDato(1.2);
		UfTO uf3 = new UfTO();
		uf3.setFecha("2020-02-24");
		uf3.setDato(1.3);

		TreeSet<UfTO> ufs = new TreeSet<>(new UfComparator());
		ufs.add(uf1);
		
		ufs.add(uf3);
		ufs.add(uf2);

		for (UfTO e : ufs) {
			
			System.out.println(e);
		}

	}
}
