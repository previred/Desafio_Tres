package com.previred.desafio.utils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import org.springframework.stereotype.Service;

import com.previred.desafio.tres.uf.vo.Uf;


@Service
public class CollectionUtils {

	
	/**
	 * Metodo encargado de ordenar la lista
	 *  
	 * @param arr
	 */
	public void sortCollection(ArrayList<Uf> arr) {

		Collections.sort(arr, new Comparator<Uf>() {
			public int compare(Uf o1, Uf o2) {
				if (o1.getFecha() == null || o2.getFecha() == null)
					return 0;
				return o1.getFecha().compareTo(o2.getFecha());
			}
		});

	}

}
