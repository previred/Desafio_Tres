package com.previred.challenge.test;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import com.previred.challenge.ValorUFHandler;
import com.previred.desafio.tres.uf.vo.Uf;

public class ValorUFHandlerTest {

	@Test
	public void testGetUf() throws Exception{
		
		ValorUFHandler handler = new ValorUFHandler();
		
		List<Uf> valorUfTotal = handler.getUF();
		assertTrue(true);
		
		assertNotNull(valorUfTotal);
		Assert.assertFalse(valorUfTotal.isEmpty());
	}
}
