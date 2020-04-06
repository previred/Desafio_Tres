package com.previred.ComplementoValoresUF.utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletResponse;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.DOMImplementation;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Text;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.opencsv.CSVWriter;
import com.previred.ComplementoValoresUF.model.ListaUf;
import com.previred.desafio.tres.uf.vo.Uf;

public class Utilities {

	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	public boolean crearJson(ListaUf listaUf, ServletContext context) {

		String filePath = context.getRealPath("resources/reportes");
		boolean exists = new File(filePath).exists();
		
		if(!exists) {
			new File(filePath).mkdirs();
		}
		
		File file = new File(filePath+"/"+ File.separator + "valores.json");
		ObjectMapper objectMapper = new ObjectMapper();
		objectMapper.setDateFormat(sdf);
		try {
			
			objectMapper.writeValue(file, listaUf);
			return true;
			
		} catch (Exception e) {
			
			e.printStackTrace();
			return false;
			
		}	
		
	}
	
	public boolean creaCsv(List<Uf> listaUfsTotales, ServletContext context) {
		String filePath = context.getRealPath("resources/reportes");
		boolean exists = new File(filePath).exists();
		
		if(!exists) {
			new File(filePath).mkdirs();
		}
		
		File file = new File(filePath+"/"+ File.separator + "valores.csv");
		
		
		try {
			
			FileWriter fileWriter = new FileWriter(file);
			CSVWriter writer = new CSVWriter(fileWriter);
			List<String[]> datos = new ArrayList<String[]>();
			datos.add(new String[] {"Fecha","Valor UF"});
			
			for (Uf uf : listaUfsTotales) {
				String fechaUf = new SimpleDateFormat("yyyy-MM-dd").format(uf.getFecha());
				datos.add(new String[] {fechaUf, uf.getValor().toString()});
			}
			
			writer.writeAll(datos);
			writer.close();			
			return true;
			
		} catch (Exception e) {
			
			return false;
		}
	}
	
	
	public boolean crearXml(ListaUf listaUf, ServletContext context) {

		String filePath = context.getRealPath("resources/reportes");
		boolean exists = new File(filePath).exists();
		
		if(!exists) {
			new File(filePath).mkdirs();
		}
		
		File file = new File(filePath+"/"+ File.separator + "valores.xml");

		try {

            List<Uf> listado = new ArrayList<Uf>();
            listado = listaUf.getUFs();
			
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
	        DocumentBuilder builder = factory.newDocumentBuilder();
	        DOMImplementation implementation = builder.getDOMImplementation();
	        
	        Document document = implementation.createDocument(null, "valores", null);
            document.setXmlVersion("1.0");
            
            Element tagInicio = document.createElement("inicio");
            Text datoInicio = document.createTextNode(listaUf.getInicio());
            tagInicio.appendChild(datoInicio);
            
            Element tagFin = document.createElement("fin");
            Text datoFin = document.createTextNode(listaUf.getFin());
            tagFin.appendChild(datoFin);            
            
            Element tagUfs = document.createElement("UFs");
            
            document.getDocumentElement().appendChild(tagInicio);
            document.getDocumentElement().appendChild(tagFin);
            
            document.getDocumentElement().appendChild(tagUfs);
            
            for (Uf uf : listado) {
            	
            	Element tagUf = document.createElement("UF");
            	Element tagFecha = document.createElement("fecha");
            	Text datoFechaUf = document.createTextNode(sdf.format(uf.getFecha()));
            	tagFecha.appendChild(datoFechaUf);
            	tagUf.appendChild(tagFecha);
            	
            	Element tagValorUf = document.createElement("valor");
            	Text datoValorUf = document.createTextNode(String.valueOf(uf.getValor()));
            	tagValorUf.appendChild(datoValorUf);   	
            	tagUf.appendChild(tagValorUf);
            	tagUfs.appendChild(tagUf);
            	
			}		
			
            Source source = new DOMSource(document);
            Result result = new StreamResult(file);
            Transformer transformer = TransformerFactory.newInstance().newTransformer();
            transformer.transform(source, result);
            
			return true;
			
		} catch (Exception e) {
			
			e.printStackTrace();
			return false;
			
		}		
	}
	
	public void descargarArchivo(String fullPath, HttpServletResponse response, String fileName, ServletContext context) {
		
		File file = new File(fullPath);
		final int BUFFER_SIZE = 4096;
		
		if(file.exists()) {
			try {
				FileInputStream fis = new FileInputStream(file);
				String mimeType = context.getMimeType(fullPath);
				response.setContentType(mimeType);
				response.setHeader("content-disposition", "attachment; filename="+fileName);
				OutputStream outputStream = response.getOutputStream();
				byte[] buffer  = new byte [BUFFER_SIZE];
				int byteread = -1;
				while((byteread = fis.read(buffer))!=-1) {
					outputStream.write(buffer, 0, byteread);
				}
				fis.close();
				outputStream.close();
				file.delete();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}	
	}
		
}
