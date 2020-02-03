package com.previred.desafio;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.HashMap;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.previred.desafio.utils.DateUtils;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TestDate {

	@Autowired
	DateUtils utilsDate;

	
	@Test
	public void validaHasMap() {
	HashMap<Integer, Integer> daysXmonthys = utilsDate.getDaysXmonth();
	assertNotNull(daysXmonthys);	
	}
	
	
	@Test
	public void validaBisiestro() {

		assertTrue(utilsDate.isLeap(2020));

		assertFalse(utilsDate.isLeap(2019));
	}

	@Test
	public void validaCantDias() {

		HashMap<Integer, Integer> daysXmonthys = utilsDate.getDaysXmonth();
		
		assertEquals(utilsDate.getTotalDayPerMont(utilsDate.getDaysXmonth(), 2, 2020), 29);

		assertEquals(utilsDate.getTotalDayPerMont(daysXmonthys, 4, 2020), 30);
		assertEquals(utilsDate.getTotalDayPerMont(daysXmonthys, 11, 2020), 30);
		assertEquals(utilsDate.getTotalDayPerMont(daysXmonthys, 7, 2020), 31);

	}

}