package com.previred.desafio.persistence.dao.impl;

import java.io.FileWriter;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.List;

import com.google.gson.Gson;
import com.previred.desafio.model.dto.UFsJsonDTO;
import com.previred.desafio.model.dto.ValoresJsonDTO;
import com.previred.desafio.persistence.dao.ValoresDAO;
import com.previred.desafio.tres.uf.DatosUf;
import com.previred.desafio.tres.uf.Valores;
import com.previred.desafio.tres.uf.vo.Uf;

public class ValoresDAOImpl implements ValoresDAO
{	
	private SimpleDateFormat formatdate = new SimpleDateFormat("yyyy-MM-dd");
	private DecimalFormat formatnumber = new DecimalFormat("###,###.###");
	
	public ValoresDAOImpl() {
		// TODO Auto-generated constructor stub
	}
	
	public void generarJsonValoresGetUf() {
		ArrayList<UFsJsonDTO> jsonUfs = new ArrayList<UFsJsonDTO>();
		
		// Se consume el método getRango() de la clase Valores
		Valores valoresObj = new Valores();
		Date fecInicio = valoresObj.getRango().getInicio();
		Date fecFin = valoresObj.getRango().getFin();
		
		// Se consume el método getUfs() de la clase singleton DatosUf
		List<Uf> datosUfs = DatosUf.getInstance().getUfs(fecInicio, fecFin);
		
		// Se crea el objeto para generar el archivo en formato json
		ValoresJsonDTO valoresJson = new ValoresJsonDTO();
		
		// Se formatean las fechas "yyyy-mm-dd"
		String strInicio = formatdate.format(fecInicio);
		String strFin = formatdate.format(fecFin);
		
		valoresJson.setInicio(strInicio);
		valoresJson.setFin(strFin);
		Collections.reverse(datosUfs);
		
		for (Uf item : datosUfs) {
			UFsJsonDTO uf = new UFsJsonDTO();
		    String strFecha = formatdate.format(item.getFecha());
		    String strValor = formatnumber.format(item.getValor());
			uf.setDato(strValor);
			uf.setFecha(strFecha);
			jsonUfs.add(uf);
		}
		
		valoresJson.setUfs(jsonUfs);
		
		// Se convierte el objeto para generar el archivo en formato json
		// Se debe modificar la ruta donde se desea guardar el archivo generado
		Gson gson = new Gson();
		try (FileWriter writer = new FileWriter("C:\\Users\\cesar\\Documents\\workspace\\Desafio_Tres\\data\\valoresUf.json")) {
            gson.toJson(valoresJson, writer);
            System.out.println("Fecha Inicio: "+strInicio);
            System.out.println("Fecha Fin "+strFin);
            System.out.println("El archivo valores.json fue generado con éxito usando el método getUfs");
        } catch (IOException e) {
            e.printStackTrace();
        }
	}
	
	public void generarJsonValoresGetUfs() {
		ArrayList<UFsJsonDTO> jsonUfs = new ArrayList<UFsJsonDTO>();
		
		// Se consume el método getRango() de la clase Valores
		Valores valoresObj = new Valores();
		Date fecInicio = valoresObj.getRango().getInicio();
		Date fecFin = valoresObj.getRango().getFin();
		
		String strInicio = formatdate.format(fecInicio);
		String strFin = formatdate.format(fecFin);
        
        List<Uf> rangoUfs = new ArrayList<Uf>();
        Iterator <Uf> it = valoresObj.getRango().getUfs().iterator();
        
        // Se genera un rango de fechas para consultar en los métodos Valores y DatosUf 
        List<Date> rangoDias = getDaysBetweenDates(fecInicio, fecFin);
        
        // Se crea una lista Uf para verficar si existen en la lista rangoDias generada
        while (it.hasNext()) {
            Uf uf = new Uf();
    		uf.setFecha(it.next().getFecha());
    		if (it.hasNext()) {
    			uf.setValor(it.next().getValor());
    		}
    		rangoUfs.add(uf);
		}
        
        // Se crea el objeto para generar el archivo en formato json
        ValoresJsonDTO valoresJson = new ValoresJsonDTO();
 		
 		valoresJson.setInicio(strInicio);
 		valoresJson.setFin(strFin);
        
 		// Se invierte el orden para que sea descendente
        Collections.reverse(rangoDias);
        
        // Se complementan los valores de UF para las fechas faltantes en el rango generado
		for (Date dia : rangoDias)
		{
			UFsJsonDTO uf = new UFsJsonDTO();
			Integer sw = 0;
			for (Uf item : rangoUfs) {
				String strFechaF = "";
				String strValorF = "";
				Calendar calendar=Calendar.getInstance();
				calendar.setTime(dia);
				calendar.set(Calendar.HOUR, 0);
				Date diaF = calendar.getTime();
			    
				if (sw == 0 ) {
					// getRango() de Valores
					if (diaF.equals(item.getFecha()) && item.getValor() != null) {
					    strFechaF = formatdate.format(item.getFecha());
				    	strValorF = formatnumber.format(item.getValor());
				    	uf.setFecha(strFechaF);
						uf.setDato(strValorF);
						sw = 1;
					} else {
						// getUf() de DatosUf
						Uf UfObj = DatosUf.getInstance().getUf(diaF);
						strFechaF = formatdate.format(diaF);
						strValorF = formatnumber.format(UfObj.getValor());
						uf.setFecha(strFechaF);
						uf.setDato(strValorF);
						sw = 1;
					}
				} else {
					break;
				}
			}
			
			jsonUfs.add(uf);
		}
		
		// Objeto json para exportar
		valoresJson.setUfs(jsonUfs);
		
		// Se convierte el objeto para generar el archivo en formato json
		// Se debe modificar la ruta donde se desea guardar el archivo generado
		Gson gson = new Gson();
		try (FileWriter writer = new FileWriter("C:\\Users\\cesar\\Documents\\workspace\\Desafio_Tres\\data\\valores.json")) {
			gson.toJson(valoresJson, writer);
			System.out.println("Fecha Inicio: "+strInicio);
			System.out.println("Fecha Fin "+strFin);
			System.out.println("El archivo valores.json fue generado con éxito usando el método getUf");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public List<Date> getDaysBetweenDates(Date startdate, Date enddate) {
	    List<Date> dates = new ArrayList<Date>();
	    Calendar calendar = new GregorianCalendar();
	    calendar.setTime(startdate);

	    while (calendar.getTime().before(enddate)) {
	        Date result = calendar.getTime();
	        dates.add(result);
	        calendar.add(Calendar.DATE, 1);
	    }
	    dates.add(enddate);
	    return dates;
	}
}
