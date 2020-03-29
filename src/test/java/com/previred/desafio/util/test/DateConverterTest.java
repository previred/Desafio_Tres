package com.previred.desafio.util.test;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;

import org.junit.Assert;
import org.junit.Test;

import com.previred.desafio.util.DateConverter;

public class DateConverterTest {

	@Test
	public void testConvertDateToLocalDate() throws Exception{
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		Date date= format.parse("2020-03-29");
		
		LocalDate localDate = DateConverter.convertDateToLocalDate(date);
		Assert.assertEquals(29, localDate.getDayOfMonth());
		Assert.assertEquals(3, localDate.getMonthValue());
		Assert.assertEquals(2020, localDate.getYear());
	}

	@Test
	public void testConvertLocalDateToDate() throws Exception{
		LocalDate localDate = LocalDate.of(2020, 3, 29);
		
		Date date= DateConverter.convertLocalDateToDate(localDate);
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		
		Assert.assertEquals(29, cal.get(Calendar.DAY_OF_MONTH));
		Assert.assertEquals(2, cal.get(Calendar.MONTH));	//primer mes enero es 0
		Assert.assertEquals(2020, cal.get(Calendar.YEAR));
	}
	
	@Test
	public void testCalculateNumberOfDays() throws Exception{
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		Date initDate= format.parse("2020-03-27");
		Date endDate= format.parse("2020-03-29");
		
		Assert.assertEquals(3, DateConverter.calculateNumberOfDays(initDate, endDate));
	}
}
