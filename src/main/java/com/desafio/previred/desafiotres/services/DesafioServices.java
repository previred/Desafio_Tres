package com.desafio.previred.desafiotres.services;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.TimeZone;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.previred.desafio.tres.uf.Valores;
import com.previred.desafio.tres.uf.tools.RandomDate;
import com.previred.desafio.tres.uf.vo.Uf;
import com.previred.desafio.tres.uf.vo.Ufs;

@Service
public class DesafioServices {

	private static final Logger LOG_DESAFIO = LogManager.getLogger(DesafioServices.class);

	@Autowired
	private ObjectMapper objectMapper;

	public String generateUfs() throws JsonProcessingException {
		Valores valores = new Valores();

		Ufs ufs = valores.getRango();
		Set<Uf> ufSet = ufs.getUfs();

		List<Uf> ufList;

		LOG_DESAFIO.info("Start size {}", ufSet.size());

		if (ufSet.size() >= 100) {
			ufList = removeData(ufSet, ufSet.size());
		} else {
			ufList = addData(ufSet, ufs);
		}

		LOG_DESAFIO.info("Final size {}", ufList.size());

		Collections.sort(ufList, new Comparator<Uf>() {

			@Override
			public int compare(Uf ufFirst, Uf ufSecond) {
				// TODO Auto-generated method stub
				return ufSecond.getFecha().compareTo(ufFirst.getFecha());
			}
		});

		ufs.setUfs(new LinkedHashSet<Uf>(ufList));

		return objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(ufs);
	}

	private List<Uf> addData(Set<Uf> ufSet, Ufs ufs) {
		Calendar calendarStart = Calendar.getInstance(TimeZone.getTimeZone(ZoneId.systemDefault()));
		calendarStart.setTime(ufs.getInicio());
		int yearStart = calendarStart.get(Calendar.YEAR);
		int monthStart = calendarStart.get(Calendar.MONTH);
		int dayStart = calendarStart.get(Calendar.DAY_OF_MONTH);

		Calendar calendarFinish = Calendar.getInstance(TimeZone.getTimeZone(ZoneId.systemDefault()));
		calendarFinish.setTime(ufs.getFin());
		int yearFinish = calendarFinish.get(Calendar.YEAR);
		int monthFinish = calendarFinish.get(Calendar.MONTH);
		int dayFinish = calendarFinish.get(Calendar.DAY_OF_MONTH);
		while (ufSet.size() < 100) {
			RandomDate randomDate = new RandomDate(LocalDate.of(yearStart, monthStart, dayStart),
					LocalDate.of(yearFinish, monthFinish, dayFinish));
			Uf uf = new Uf();
			uf.setFecha(Date.from(randomDate.nextDate().atStartOfDay().atZone(ZoneId.systemDefault()).toInstant()));
			Random r = new Random();
			double minUF = 28000;
			double maxUF = 30000;
			double randomUF = minUF + (maxUF - minUF) * r.nextDouble();
			uf.setValor(randomUF);
			ufSet.add(uf);

		}
		return new ArrayList<>(ufSet);

	}

	private List<Uf> removeData(Set<Uf> ufSet, int size) {

		if (size == 100) {
			return new ArrayList<Uf>(ufSet);
		} else {
			List<Uf> ufList = new ArrayList<>(ufSet);
			while (ufList.size() > 100) {
				ufList.remove(ufList.size() - 1);
			}

			return ufList;
		}
	}

}