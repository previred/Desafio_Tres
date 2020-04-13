package com.navastud.complementouf.models;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.navastud.complementouf.utls.Utils;

public class DateRange {

	private LocalDate startDate;

	private LocalDate endDate;

	public DateRange() {
		super();
	}

	public DateRange(LocalDate startDate, LocalDate endDate) {
		super();
		this.startDate = startDate;
		this.endDate = endDate;
	}

	public Stream<LocalDate> stream() {
		return Stream.iterate(startDate, date -> date.plusDays(1))
				.limit(ChronoUnit.DAYS.between(startDate, endDate) + 1);
	}

	public List<LocalDate> toList() {
		return stream().collect(Collectors.toList());
	}

	public List<Date> toDateList() {
		return stream().map(date -> Utils.localDateToDate(date)).collect(Collectors.toList());
	}

	public int countDays() {
		return (int) this.stream().count();
	}

	public LocalDate getStartDate() {
		return startDate;
	}

	public void setStartDate(LocalDate startDate) {
		this.startDate = startDate;
	}

	public LocalDate getEndDate() {
		return endDate;
	}

	public void setEndDate(LocalDate endDate) {
		this.endDate = endDate;
	}

}
