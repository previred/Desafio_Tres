package cl.jm.desafiotres.resultado.utils;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import com.previred.desafio.tres.uf.vo.Uf;

public class CreateXml {

public void Create(List<Uf> ufs) {
		
		SimpleDateFormat parse = new SimpleDateFormat("yyyy-MM-dd");
		
		try {
			 
			DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
	 
			// valores
			Document doc = docBuilder.newDocument();
			Element rootElement = doc.createElement("valores");
			doc.appendChild(rootElement);
	 
			// inicio
			Element inicio = doc.createElement("inicio");
			inicio.appendChild(doc.createTextNode("2014-10-05"));
			rootElement.appendChild(inicio);
			
			// fin
			Element fin = doc.createElement("fin");
			fin.appendChild(doc.createTextNode("2017-10-05"));
			rootElement.appendChild(fin);
			
			// UFs
			Element UFs = doc.createElement("UFs");
			rootElement.appendChild(UFs);
			
			// UF
			for (Uf uf2 : ufs) {
			
				Element UF = doc.createElement("UF");
				UFs.appendChild(UF);
				
				// nombre
				Element fecha = doc.createElement("fecha");
				fecha.appendChild(doc.createTextNode(parse.format(uf2.getFecha())));
				UF.appendChild(fecha);
		 
				// apellidos
				Element dato = doc.createElement("dato");
				dato.appendChild(doc.createTextNode(uf2.getValor().toString()));
				UF.appendChild(dato);
			
			}
			// se escribe el contenido en un archivo .xml
			TransformerFactory transformerFactory = TransformerFactory.newInstance();
			Transformer transformer = transformerFactory.newTransformer();
			DOMSource source = new DOMSource(doc);
			StreamResult result = new StreamResult(new File("XML.xml"));
				 
			transformer.transform(source, result);
	 
			} catch (ParserConfigurationException pce) {
				pce.printStackTrace();
			} catch (TransformerException tfe) {
				tfe.printStackTrace();
			}
		
	}
}
