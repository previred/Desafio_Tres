package com.previred.desafio.service;

import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UfServiceTest {

	@Autowired
	private UfService service;
	
	public UfServiceTest() {
	}

	@Test
	public void generateJSON() {
		Assertions.assertNotNull(service.generateJSON());
	}
}
