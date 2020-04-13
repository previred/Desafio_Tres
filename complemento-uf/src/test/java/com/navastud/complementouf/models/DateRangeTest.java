package com.navastud.complementouf.models;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.time.LocalDate;
import java.time.Month;
import java.time.Year;
import java.time.YearMonth;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAdjusters;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest()
public class DateRangeTest {

	@Test
	public void getDatesBetweenTwoLocalDateTest() throws Exception {

		LocalDate start = LocalDate.now();
		LocalDate end = LocalDate.now().plusMonths(1).with(TemporalAdjusters.lastDayOfMonth());
		List<LocalDate> dates = Stream.iterate(start, date -> date.plusDays(1))
				.limit(ChronoUnit.DAYS.between(start, end) + 1).collect(Collectors.toList());

		int countDays = end.getDayOfMonth()
				+ (YearMonth.of(start.getYear(), start.getMonth().getValue()).lengthOfMonth()
						- (start.getDayOfMonth() - 1));

		assertThat(dates.get(0), is(start));
		assertThat(dates.get(countDays - 1), is(end));
		assertThat(dates.size(), is(countDays));
	}

	@Test
	public void getListDatesBetweenTwoLocalDateFromDateRangeTest() {

		LocalDate start = LocalDate.now();
		LocalDate end = LocalDate.now().plusMonths(1).with(TemporalAdjusters.lastDayOfMonth());
		List<LocalDate> dates = new DateRange(start, end).toList();

		int countDays = end.getDayOfMonth()
				+ (YearMonth.of(start.getYear(), start.getMonth().getValue()).lengthOfMonth()
						- (start.getDayOfMonth() - 1));

		assertThat(dates.get(0)).isNotNull().isEqualTo(start);
		assertThat(dates.get(countDays - 1)).isNotNull().isEqualTo(end);
		assertThat(dates.size()).isNotNull().isEqualTo(countDays);
	}

	@Test
	public void countDaysBetweenToDatesTest() {

		LocalDate start = LocalDate.now();
		LocalDate end = LocalDate.now().plusYears(2).with(TemporalAdjusters.lastDayOfMonth());

		DateRange range = new DateRange(start, end);

		int years = end.getYear() - start.getYear();
		int countDays = 0;

		for (int i = 0; i < years; i++) {
			Month month = Month.DECEMBER;
			int year = start.getYear() + i;
			LocalDate of = LocalDate.of(Year.of(year).getValue(), month.getValue(),
					YearMonth.of(year, month.getValue()).lengthOfMonth());
			countDays += of.getDayOfYear();
		}

		countDays = (countDays - start.getDayOfYear() + 1) + end.getDayOfYear();
		List<LocalDate> dates = range.toList();

		assertThat(range.countDays()).isNotNull().isEqualTo(countDays);
		assertThat(dates.get(0)).isNotNull().isEqualTo(start);
		assertThat(dates.get(countDays - 1)).isNotNull().isEqualTo(end);
	}

}
