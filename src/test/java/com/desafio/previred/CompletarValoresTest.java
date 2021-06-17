package com.desafio.previred;

//import static org.junit.Assert.*;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.Date;

//import org.junit.Test;

import junit.framework.TestCase;

import com.previred.desafio.tres.uf.vo.Uf;

public class CompletarValoresTest extends TestCase {
	
	private final CompletarValores completarValores=new CompletarValores();
	
	//@Test
	public void testOrdenarUf(){
		Set<Uf> ufs = getSetUf();
		List<Uf> listaordenada= getListaOrdenada();

		List<Uf> listaResultante=completarValores.ordenarUf(ufs);
		
		assertEquals(listaordenada, listaResultante);
	}


	public Set<Uf> getSetUf() {
		Set<Uf> ufs = new HashSet<Uf>();
	
		ufs.add(genearUf("2010-06-14", 30.0));
		ufs.add (genearUf("2010-07-01", 20.0));
		ufs.add(genearUf("2012-09-22", 10.0));
		return ufs;
	}
	               
	
	public  void testGetValoresfaltantes() {
		
		int size=97;
	    LocalDate fechaInicio = LocalDate.parse("2010-06-14");
		Date dateInicio = Date.from(fechaInicio.atStartOfDay(ZoneId.systemDefault()).toInstant());	
		LocalDate fechaFin = LocalDate.parse("2012-09-22");
		Date dateFin = Date.from(fechaFin.atStartOfDay(ZoneId.systemDefault()).toInstant());	
		Set<Uf> ufs = getSetUf();
		completarValores.getValoresfaltantes(size, dateInicio, dateFin, ufs);
		
		assertEquals(100, ufs.size());
	}
	   
	

	private Uf  genearUf(String fechaUf, double valor) {
		Uf uf = new Uf();
		LocalDate fecha = LocalDate.parse(fechaUf);
		Date date = Date.from(fecha.atStartOfDay(ZoneId.systemDefault()).toInstant());	
		uf.setFecha(date);
		uf.setValor(valor);
		
		return uf;
	}
	
	private List<Uf>  getListaOrdenada() {
		List<Uf> listaOrdenada = new ArrayList<>();
		listaOrdenada.add(genearUf("2012-09-22", 10.0));
		listaOrdenada.add(genearUf("2010-07-01", 20.0));
		listaOrdenada.add(genearUf("2010-06-14", 30.0));
		
		return listaOrdenada;
	}

}
