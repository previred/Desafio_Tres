package com.previred.desafio.test;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import com.previred.desafio.UFValueRetriever;
import com.previred.desafio.tres.uf.vo.Uf;

public class UFValueRetrieverTest {

	@Test
	public void testGetUf() throws Exception{
		
		UFValueRetriever handler = new UFValueRetriever();
		
		List<Uf> valorUfTotal = handler.getUF();
		assertTrue(true);
		
		assertNotNull(valorUfTotal);
		Assert.assertFalse(valorUfTotal.isEmpty());
	}
}
