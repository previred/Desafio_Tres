package com.previred.desafio.test;

import static org.junit.Assert.assertNotNull;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import com.previred.desafio.UFValueResult;
import com.previred.desafio.UFValueRetriever;
import com.previred.desafio.tres.uf.vo.Uf;
import com.previred.desafio.util.DateConverter;

public class UFValueRetrieverTest {

	@Test
	public void testGetUf() throws Exception{
		
		UFValueRetriever handler = new UFValueRetriever();
		
		UFValueResult result = handler.getUF();
		List<Uf> valorUfTotal = result.getSortedUfs();
		
		Date inicio = result.getInicio();
		Date fin = result.getFin();
		
		assertNotNull(valorUfTotal);
		Assert.assertFalse(valorUfTotal.isEmpty());
		
		Assert.assertEquals(DateConverter.calculateNumberOfDays(inicio, fin), valorUfTotal.size());
	}
	
	
}
