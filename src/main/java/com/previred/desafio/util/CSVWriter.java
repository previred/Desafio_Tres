package com.previred.desafio.util;

import static com.previred.desafio.util.DateConverter.prettyPrint;

import java.io.BufferedWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;

import com.previred.desafio.tres.uf.vo.Uf;
import com.previred.desafio.uf.UFValueResult;

public class CSVWriter {

	public void write(UFValueResult ufs, String sampleCsvFile) throws Exception {
		
		try(BufferedWriter writer = Files.newBufferedWriter(Paths.get(sampleCsvFile));
			CSVPrinter csvPrinter = new CSVPrinter(writer, CSVFormat.DEFAULT
		                .withHeader("1", prettyPrint(ufs.getInicio()), prettyPrint(ufs.getFin()))
		                .withDelimiter(';')
					);){
	
	        for (Uf uf : ufs.getSortedUfs()) {
	        	csvPrinter.printRecord("2", prettyPrint(uf.getFecha()), prettyNumberPrint(uf.getValor()));
			}
	        
	        csvPrinter.flush();
		}
	}
	
	private String prettyNumberPrint(Double number) {
		DecimalFormat myFormatter = new DecimalFormat("###,###.##");
		myFormatter.setDecimalFormatSymbols(DecimalFormatSymbols.getInstance(new Locale("es")));
	    return myFormatter.format(number);
	}
}
