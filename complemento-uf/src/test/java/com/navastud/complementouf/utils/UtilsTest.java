package com.navastud.complementouf.utils;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDate;
import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.navastud.complementouf.utls.Utils;

@RunWith(SpringRunner.class)
@SpringBootTest()
@SuppressWarnings("deprecation")
public class UtilsTest {

	@Test
	public void getLocalDateFromDateTest() {
		LocalDate now = LocalDate.now();
		LocalDate localDateToDate = Utils.dateToLocalDate(new Date());
		assertThat(now).isNotNull().isEqualTo(localDateToDate);
	}

	@Test
	public void getLocalDate2021FromDate2021Test() {
		LocalDate now = LocalDate.of(2021, 1, 1);
		LocalDate localDateToDate = Utils.dateToLocalDate(new Date("2021/01/01"));
		assertThat(now).isNotNull().isEqualTo(localDateToDate);
	}

	@Test
	public void getDateFromLocalDateTest() {

		Date dateToLocalDate = Utils.localDateToDate(LocalDate.now());
		Date now = new Date();
		assertThat(now.getDate()).isNotNull().isEqualTo(dateToLocalDate.getDate());
	}

	@Test
	public void getDate2021ToLocalDate2021Test() {

		Date dateToLocalDate = Utils.localDateToDate(LocalDate.of(2021, 1, 1));
		Date now = new Date("2021/01/01");
		assertThat(now.getDate()).isNotNull().isEqualTo(dateToLocalDate.getDate());
	}

	@Test
	public void getStringToDoubleTest() {
		Double valor = 23610.77;
		Double stringToDouble = Utils.stringToDouble("23.610,77");
		assertThat(stringToDouble).isNotNull().isEqualTo(valor);
	}

	@Test
	public void getDoubleToStringTest() {
		String valor = "23.610,77";
		String doubleToString = Utils.doubleToString(23610.77);
		assertThat(doubleToString).isNotNull().isEqualTo(valor);
	}

	@Test
	public void getDoubleToStringOtherNumberTest() {
		String valor = "21.033,6";
		String doubleToString = Utils.doubleToString(21033.6);
		assertThat(doubleToString).isNotNull().isEqualTo(valor);
	}
}
