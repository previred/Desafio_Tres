package com.previred.desafio.util.test;

import java.io.File;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import com.previred.desafio.tres.uf.vo.Uf;
import com.previred.desafio.uf.UFValueResult;
import com.previred.desafio.util.CSVWriter;


public class CSVWriterTest {

	private static final String TEST_CSV_FILE_NAME = "test.csv";

	@Test
	public void testWrite() throws Exception{
		
		UFValueResult result = createUFValueResult();
		
		CSVWriter writer = new CSVWriter();
		writer.write(result, TEST_CSV_FILE_NAME);

		File testFile = new File(TEST_CSV_FILE_NAME);
		
		Assert.assertTrue(testFile.exists());
		
		String csvContent = new String(Files.readAllBytes(Paths.get(TEST_CSV_FILE_NAME)), StandardCharsets.UTF_8);
		
		Assert.assertTrue( csvContent.contains("1;2020-03-29;2020-03-29"));
		Assert.assertTrue( csvContent.contains("2;2020-03-29;22.334,09"));
		
		testFile.delete();
	}
	
	private UFValueResult createUFValueResult() throws ParseException {
		
		List<Uf> ufs= new ArrayList<Uf>();
		Uf uf = new Uf();
		
		
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		Date date = format.parse("2020-03-29");
		
		uf.setFecha(date);
		uf.setValor(22334.09);
		ufs.add(uf);
		
		UFValueResult result = new UFValueResult(date, date, ufs);
		return result;
	}
}
