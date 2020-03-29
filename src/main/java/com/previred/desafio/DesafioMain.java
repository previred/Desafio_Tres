package com.previred.desafio;

import com.previred.desafio.uf.UFValueResult;
import com.previred.desafio.uf.UFValueRetriever;
import com.previred.desafio.util.CSVWriter;

public class DesafioMain {

	public static void main(String[] args) {
		
		UFValueRetriever retriever = new UFValueRetriever();		
		UFValueResult ufs = retriever.getUF();
		
		CSVWriter writer = new CSVWriter();
		try {
			writer.write(ufs);
		} catch (Exception e) {
			System.out.println("Error tratando de escribir archivo valores.csv");
		}
	}

}
