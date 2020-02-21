package com.previred.common;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Set;
import java.util.TreeMap;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.previred.desafio.tres.uf.DatosUf;
import com.previred.desafio.tres.uf.vo.Uf;
import com.previred.desafio.tres.uf.vo.Ufs;

public class ComplementoUf {
	
	private static Logger LOGGER = LoggerFactory.getLogger(ComplementoUf.class);
	private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyyMMdd");
	
	public static List<SortedUf> getComplementedRange(Ufs ufRange) throws PreviredException {
				
		//validate input params
		if(ufRange == null) 
			throw new IllegalArgumentException("uf range shouldn't be null.");
		
		Date start = ufRange.getInicio();
		Date end = ufRange.getFin();
						
		if(start == null || end == null) 
			throw new IllegalArgumentException("start and end dates shouldn't be null.");			
		
		start = initializeDate(start);
		end = initializeDate(end);
		
		//validate start < end
		if(start.getTime() > end.getTime()) 
			throw new IllegalArgumentException("start date cloudn't be greater than end date.");					
		
		//tree map to sort range values
		Map<String, SortedUf> map= new TreeMap<String, SortedUf>(); 
		
		fillKnownUfValues(ufRange, map);
		complementUfValues(start, end, map);
		
		List<SortedUf> result = new ArrayList<SortedUf>(map.size());
		
		result.addAll(map.values());
		
		return result;
		
	}

	/*
	 * Complement unknown values, iterate until we exceed end date
	 */
	private static void complementUfValues(Date start, Date end, Map<String, SortedUf> map) throws PreviredException {

		Date currentDate = start;
		Date rangeStart = currentDate; //this date will define the start of a range to query
		Date rangeEnd = currentDate; //this date will define the end of a range to query
		Boolean rangeProcessed = false;
		
		while(currentDate.getTime() < end.getTime()) {
			
			Date nextDate = new Date(currentDate.getTime() + TimeUnit.DAYS.toMillis(1));
			
			//if current date already exist or is the last date in range, we must query values 
			if(map.get(DATE_FORMAT.format(currentDate)) != null) {
				
				//query and add values excluding current date
				processRangeQuery(map, rangeStart, rangeEnd);				
				rangeProcessed = true;
				
			} else if(nextDate.getTime() > end.getTime()) {
				
				//query and add values including current date
				processRangeQuery(map, rangeStart, currentDate);
				rangeProcessed = true;
				
			}
			
			rangeEnd = currentDate;
			//set new current date
			currentDate = nextDate;
			
			//if we have processed the range, restart it 
			if(rangeProcessed) {
				
				rangeStart = currentDate;
				rangeEnd = currentDate;
				rangeProcessed = false;
				
			}
			
		}
		
	}

	/*
	 * Process a query for a range of dates, we assume that the throughput of a single date is
	 * better than the range, so we call a single query if is possible 
	 */
	private static void processRangeQuery(Map<String, SortedUf> map, Date rangeStart, Date rangeEnd)
			throws PreviredException {
		
		LOGGER.info("Querying range start : {}, end {}", DATE_FORMAT.format(rangeStart), DATE_FORMAT.format(rangeEnd));
		DatosUf datos = DatosUf.getInstance(); 
		
		try {
		
			if(rangeStart.getTime() == rangeEnd.getTime()) {
			
				Uf uf = datos.getUf(rangeStart);
				addUfValueToMap(map, uf);
				
			} else {
				
				List<Uf> ufList = datos.getUfs(rangeStart, rangeEnd);
			
				for(Uf uf : ufList) {
					
					addUfValueToMap(map, uf);
					
				}
				
			}
			
		} catch(NoSuchElementException e) {
			
			LOGGER.warn("Impossible to get Uf range.");			
			
		}
		
	}

	/*
	 * add an uf to a consolidated sorting map
	 */
	private static void addUfValueToMap(Map<String, SortedUf> map, Uf uf) throws PreviredException {
		
		//if valor is valid, we add it to our map
		if(uf.getValor() != null) {
			
			map.put(DATE_FORMAT.format(uf.getFecha()), new SortedUf(uf.getFecha(), uf.getValor()));
			
		} else {
			
			throw new PreviredException("Imposible to get a valid Uf for date " + uf.getFecha()); 
			
		}
		
	}

	/*
	 * fill known values already in the range set 
	 */
	private static void fillKnownUfValues(Ufs ufRange, Map<String, SortedUf> map) throws PreviredException {

		Set<Uf> ufSet = ufRange.getUfs();
		
		if(ufSet != null) {
			
			for(Uf uf : ufSet) {
				
				addUfValueToMap(map, uf);
				
			}
			
		}
		
	}
	
	/*
	 * Set the hour of provided date to 0 		
	 */
	private static Date initializeDate(Date date) {
		
		Calendar cal = new GregorianCalendar();
		cal.setTime(date);
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MILLISECOND, 0);
		return cal.getTime();

	}

}
