package com.previred.desafio.persistence.dao;

import java.util.Date;
import java.util.List;

public interface ValoresDAO {
	public void generarJsonValoresGetUf();
	public void generarJsonValoresGetUfs();
	public List<Date> getDaysBetweenDates(Date startdate, Date enddate);
}
