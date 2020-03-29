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

import com.previred.desafio.UFValueResult;
import com.previred.desafio.tres.uf.vo.Uf;

public class CSVWriter {

	private static final String SAMPLE_CSV_FILE = "valores.csv";

	public void write(UFValueResult ufs) throws Exception {
		
		try(BufferedWriter writer = Files.newBufferedWriter(Paths.get(SAMPLE_CSV_FILE));
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
