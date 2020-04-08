
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonIOException;
import com.previred.desafio.tres.uf.DatosUf;
import com.previred.desafio.tres.uf.Valores;
import com.previred.desafio.tres.uf.vo.Uf;
import com.previred.desafio.tres.uf.vo.Ufs;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.Writer;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class desafio {


	public static void main(String[] args) {
		
		LocalDateTime fecha = LocalDateTime.now();
		DateTimeFormatter fechaformato = DateTimeFormatter.ofPattern("ddMMyyyyHHmmss");
		String fechaString = fecha.format(fechaformato);
		
		Valores valores =  new Valores();
		Ufs ufs = valores.getRango();
		System.out.println("Inicio "+ ufs.getInicio());
		System.out.println("fin "+ufs.getFin());

		List<Uf> listuf=new ArrayList <Uf> ();
		List<Uf> listdatosuf=new ArrayList <Uf> ();
		listuf.addAll (ufs.getUfs());
		
		DatosUf datosuf = DatosUf.getInstance();
		
		listdatosuf = datosuf.getUfs(ufs.getInicio(), ufs.getFin());
		listuf.addAll (listdatosuf);
		
		listuf.sort((o1,o2) -> o2.getFecha().compareTo(o1.getFecha()));
	    for(Uf b:listuf){  
	        System.out.println(b.getFecha());  
	    }  

	    
	    //creacion de csv
	    try (PrintWriter writer = new PrintWriter(new File("test"+fechaString+".csv"))) {

	        StringBuilder sb = new StringBuilder();

	        sb.append("1");
	        sb.append("; ");
	        sb.append(ufs.getInicio());
	        sb.append("; ");
	        sb.append(ufs.getFin());
	        sb.append('\n');
	        
	        for(Uf b:listuf){  
		        System.out.println(b.getFecha());  
		        sb.append("2");
		        sb.append("; ");
		        sb.append(b.getFecha());
		        sb.append("; ");
		        sb.append(b.getValor());
		        sb.append('\n');
		    }  
	        

	        writer.write(sb.toString());

	        System.out.println("done! Create csv");

	    } catch (FileNotFoundException e) {
	    	System.out.println(e.getMessage());
	    }
	    
	    
	    try {

            DocumentBuilderFactory documentFactory = DocumentBuilderFactory.newInstance();

            DocumentBuilder documentBuilder = documentFactory.newDocumentBuilder();

            Document document = documentBuilder.newDocument();

            // root element
            Element root = document.createElement("valores");
            document.appendChild(root);

            // inicio element
            Attr attrinicio = document.createAttribute("inicio");
            attrinicio.setValue(ufs.getInicio().toString());
            root.setAttributeNode(attrinicio);
            
            // fin element
            Attr attrfin = document.createAttribute("fin");
            attrfin.setValue(ufs.getFin().toString());
            root.setAttributeNode(attrfin);
            
            // UFs element
            Element UFs = document.createElement("UFs");

            root.appendChild(UFs);
            
            for(Uf b:listuf){  
            	Element uf = document.createElement("UF");

                UFs.appendChild(uf);
                
	            Element fechaxml = document.createElement("fecha");
	            fechaxml.appendChild(document.createTextNode(b.getFecha().toString()));
	            uf.appendChild(fechaxml);
	            
	            Element datoxml = document.createElement("dato");
	            datoxml.appendChild(document.createTextNode(b.getValor().toString()));
	            uf.appendChild(datoxml);
            }
            
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource domSource = new DOMSource(document);
            StreamResult streamResult = new StreamResult(new File("xml"+fechaString+".xml"));


            transformer.transform(domSource, streamResult);

            System.out.println("Done creating XML ");

        } catch (ParserConfigurationException pce) {
            pce.printStackTrace();
        } catch (TransformerException tfe) {
            tfe.printStackTrace();
        }
	    
		//create JSON    
	    try {
	    	Gson gson = new GsonBuilder().setPrettyPrinting().create();
	        Map<String, Object> map = new HashMap<>();
	        map.put("inicio", ufs.getInicio() );
	        map.put("fin", ufs.getFin());
	        map.put("UFs",  listuf);

	        Writer writer = new FileWriter("json"+fechaString+".json");

	        new Gson().toJson(map, writer);

	        writer.close();
	        System.out.println("done! Create Json");
	    } catch (Exception ex) {
	        ex.printStackTrace();
	    }
	    
	}
}
