package cl.jm.desafiotres.resultado.utils;

import java.util.Comparator;

import com.previred.desafio.tres.uf.vo.Uf;

public class ComparateUf implements Comparator<Uf>{
	public int compare(Uf e1, Uf e2) {
		return e2.getFecha().compareTo(e1.getFecha());
	}
}
