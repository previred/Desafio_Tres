package com.desafio.previred;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.previred.desafio.tres.uf.vo.Uf;

import junit.framework.TestCase;

public class ExportarCsvTest extends TestCase {
	
	private final ExportarCsv exportarCsv=new ExportarCsv("prueba.csv","1","2");


	public void testGenerarArchivo() {
		
		List<Uf>  listaOrdenadas =getListaOrdenada();
		LocalDate fechaInicio1 = LocalDate.parse("2010-06-14");
		Date dateInicio = Date.from(fechaInicio1.atStartOfDay(ZoneId.systemDefault()).toInstant());	
		LocalDate fechaFin1 = LocalDate.parse("2012-09-22");
		Date dateFin = Date.from(fechaFin1.atStartOfDay(ZoneId.systemDefault()).toInstant());
		 File file =exportarCsv.generarArchivo(listaOrdenadas, dateInicio, dateFin);
		 assertTrue(file.exists());
       }
	
	private List<Uf>  getListaOrdenada() {       
		List<Uf> listaOrdenada = new ArrayList<>();
		listaOrdenada.add(genearUf("2012-09-22", 10.0));
		listaOrdenada.add(genearUf("2010-07-01", 20.0));
		listaOrdenada.add(genearUf("2010-06-14", 30.0));
		
		return listaOrdenada;
	}
	
	private Uf  genearUf(String fechaUf, double valor) {
		Uf uf = new Uf();
		LocalDate fecha = LocalDate.parse(fechaUf);
		Date date = Date.from(fecha.atStartOfDay(ZoneId.systemDefault()).toInstant());	
		uf.setFecha(date);
		uf.setValor(valor);
		
		return uf;
	}

	
	
}
