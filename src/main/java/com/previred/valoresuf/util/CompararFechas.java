/**
 * 
 */
package com.previred.valoresuf.util;

import java.util.Comparator;

import com.previred.valoresuf.model.UfsList;

/**
 * @author crist
 *
 */
public class CompararFechas implements Comparator<UfsList>{

	/**
	 * Compara las fechas para su orden
	 */
	public int compare(UfsList l1, UfsList l2) {
		if (l1.getFecha().isBefore(l2.getFecha())) {
			return 1;
		} else if (l1.getFecha().isEqual(l2.getFecha())) {
			return 0;
		} else {
			return -1;
		}
	}

}
