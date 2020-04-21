package com.previred.desafio.tres.service;

import static org.junit.Assert.assertTrue;
import static org.mockito.BDDMockito.given;
import static org.mockito.Matchers.any;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Spy;
import org.mockito.runners.MockitoJUnitRunner;

import com.previred.desafio.tres.dto.RespuestaDto;

@RunWith(MockitoJUnitRunner.class)
public class ComplementoValoresServiceTests {

	@Spy
	private ComplementoValoresService service;

	public ComplementoValoresServiceTests() {
		service = new ComplementoValoresService();
	}

	@Test
	public void debeProduceComplementoValores() {
		RespuestaDto produceComplementoValores = service.produceComplementoValores();
		Integer codigo = produceComplementoValores.getCodigo();

		assertTrue(codigo.equals(0));
	}

	@Test
	public void noDebeProduceComplementoValores() {

		try {
			given(service.escribeJson(any())).willThrow(Exception.class);
			RespuestaDto produceComplementoValores = service.produceComplementoValores();
			assertTrue(produceComplementoValores.getCodigo().equals(1));
		} catch (Exception e) {
			//
		}

	}
}
