package com.previred.ComplementoValoresUF.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.previred.ComplementoValoresUF.model.ListaUf;
import com.previred.ComplementoValoresUF.service.ComplementoValoresUFService;
import com.previred.ComplementoValoresUF.utilities.Utilities;
import com.previred.desafio.tres.uf.DatosUf;
import com.previred.desafio.tres.uf.Valores;
import com.previred.desafio.tres.uf.vo.Uf;
import com.previred.desafio.tres.uf.vo.Ufs;

@Controller
public class IndexController {

	private ComplementoValoresUFService complementoValoresUFService;
	@Autowired private ServletContext context;
	private Utilities utilities;
	
	SimpleDateFormat sdfSalida = new SimpleDateFormat("yyyyMMddhhmmss");

	public Utilities getUtilities() {
		if(utilities == null) {
			utilities = new Utilities();
		}
		return utilities;
	}
	
	
	@GetMapping({ "/index", "/", "home" })
	public String index(Model model) {
		model.addAttribute("titulo", "Lista de datos obtenidos por getRango");

		List<Uf> listaUfs = new ArrayList<Uf>();
		Valores valores = new Valores();
		Ufs listaValores = valores.getRango();

		@SuppressWarnings("rawtypes")
		Iterator iterator = listaValores.getUfs().iterator();
		while (iterator.hasNext()) {
			Uf object = (Uf) iterator.next();
			listaUfs.add(object);

		}		
		System.out.println("Primer rango de datos : "+ listaUfs.size());
		
		Collections.sort(listaUfs, new Comparator<Uf>() {
			@Override
			public int compare(Uf uf1, Uf uf2) {
				return uf2.getFecha().compareTo(uf1.getFecha());
			}
		});

		Date fecha1 = null;
		Date fecha2 = null;
		
		try { 			
			
			fecha1 = listaUfs.get(0).getFecha();
			fecha2 = listaUfs.get((listaUfs.size()-1)).getFecha();
			
			List<Uf> listaUfsTotales= DatosUf.getInstance().getUfs(fecha2, fecha1);
			
			System.out.println("Lista total de datos : "+ listaUfsTotales.size());
			
			Collections.sort(listaUfsTotales, new Comparator<Uf>() {
				@Override
				public int compare(Uf uf1, Uf uf2) {
					return uf2.getFecha().compareTo(uf1.getFecha());
				}
			});
			
			model.addAttribute("fechaInicio", fecha1);
			model.addAttribute("fechaHasta", fecha2);
			model.addAttribute("listaUfs", listaUfsTotales);
			
			
			
		} catch (Exception e) {

			e.printStackTrace();
		}

		return "index";
	}

	@RequestMapping("/crearJson/{fechaInicio}/{fechaHasta}")
	public void crearJson(
			HttpServletRequest request, 
			HttpServletResponse response,
			@PathVariable String fechaInicio,
			@PathVariable String fechaHasta) {

		Date fdesde = null;
		Date fhasta = null;
		ListaUf listaUf = null;
		try {
			 fdesde = new SimpleDateFormat("yyyy-MM-dd").parse(fechaInicio);
			 fhasta = new SimpleDateFormat("yyyy-MM-dd").parse(fechaHasta);
			
		} catch (ParseException e) {
		
			e.printStackTrace();
		}
		
		List<Uf> listaUfsTotales= DatosUf.getInstance().getUfs(fhasta, fdesde);
		
		Collections.sort(listaUfsTotales, new Comparator<Uf>() {
			@Override
			public int compare(Uf uf1, Uf uf2) {
				return uf2.getFecha().compareTo(uf1.getFecha());
			}
		});

		listaUf = new ListaUf(fechaInicio, fechaHasta, listaUfsTotales);		
		boolean flagCrearJson = getUtilities().crearJson(listaUf, context);
		
		
		if(flagCrearJson) {
			String fullPath = request.getServletContext().getRealPath("/resources/reportes/"+"valores.json");
			getUtilities().descargarArchivo(fullPath, response, "valores_"+ sdfSalida.format(new Date()).toString() +".json", context);
		}
	}

	@RequestMapping("/crearXml/{fechaInicio}/{fechaHasta}")
	public void crearXml(
			HttpServletRequest request, 
			HttpServletResponse response,
			@PathVariable String fechaInicio,
			@PathVariable String fechaHasta) {

		Date fdesde = null;
		Date fhasta = null;
		ListaUf listaUf = null;
		try {
			 fdesde = new SimpleDateFormat("yyyy-MM-dd").parse(fechaInicio);
			 fhasta = new SimpleDateFormat("yyyy-MM-dd").parse(fechaHasta);
			
		} catch (ParseException e) {

			e.printStackTrace();
		}
		
		List<Uf> listaUfsTotales= DatosUf.getInstance().getUfs(fhasta, fdesde);
		
		Collections.sort(listaUfsTotales, new Comparator<Uf>() {
			@Override
			public int compare(Uf uf1, Uf uf2) {
				return uf2.getFecha().compareTo(uf1.getFecha());
			}
		});

		listaUf = new ListaUf(fechaInicio, fechaHasta, listaUfsTotales);
		boolean flagCrearXml = getUtilities().crearXml(listaUf, context);
		
		if(flagCrearXml) {
			String fullPath = request.getServletContext().getRealPath("/resources/reportes/"+"valores.xml");
			getUtilities().descargarArchivo(fullPath, response, "valores_"+ sdfSalida.format(new Date()).toString() +".xml", context);
		}
	}
	
	
	

	@RequestMapping("/crearCsv/{fechaInicio}/{fechaHasta}")
	public void crearCsv(
			HttpServletRequest request, 
			HttpServletResponse response,
			@PathVariable String fechaInicio,
			@PathVariable String fechaHasta) {

		Date fdesde = null;
		Date fhasta = null;
		try {
			 fdesde = new SimpleDateFormat("yyyy-MM-dd").parse(fechaInicio);
			 fhasta = new SimpleDateFormat("yyyy-MM-dd").parse(fechaHasta);
			
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		List<Uf> listaUfsTotales= DatosUf.getInstance().getUfs(fhasta, fdesde);
		
		Collections.sort(listaUfsTotales, new Comparator<Uf>() {
			@Override
			public int compare(Uf uf1, Uf uf2) {
				return uf2.getFecha().compareTo(uf1.getFecha());
			}
		});

		boolean flagCrearcSV = getUtilities().creaCsv(listaUfsTotales, context);
		
		if(flagCrearcSV) {
			String fullPath = request.getServletContext().getRealPath("/resources/reportes/"+"valores.csv");
			getUtilities().descargarArchivo(fullPath, response, "valores_"+ sdfSalida.format(new Date()).toString() +".csv", context);
		}
	}

}
