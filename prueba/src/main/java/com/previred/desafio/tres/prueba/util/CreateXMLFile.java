package com.previred.desafio.tres.prueba.util;

import java.io.File;
import java.util.Date;
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

/**
 * Clase que genera archivo XML para las UF de un periodo.
 * 
 * @author rsepulveda
 *
 */
public class CreateXMLFile {

	private static final String DATE_FORMAT_YYYY_MM_DD = "yyyy-MM-dd";
	private static final String PATH_FILE = "C:\\TestPrevired\\valores.xml";

	/**
	 * Genera un archivo XML con valores de UF segun datos recibidos.
	 * 
	 * @param fechaInicio
	 * @param fechaFin
	 * @param listaUF
	 */
	public void createXML(Date fechaInicio, Date fechaFin, List<Uf> listaUF) {

		try {

			DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
			Document doc = docBuilder.newDocument();
			
			// Cabecera
			Element rootElement = doc.createElement("Valores");
			doc.appendChild(rootElement);

			// Datos Fecha Inicio Periodo UF
			Element inicio = doc.createElement("Inicio");
			inicio.appendChild(doc.createTextNode(Utility.convertDateToString(fechaInicio, DATE_FORMAT_YYYY_MM_DD)));
			rootElement.appendChild(inicio);

			// Datos Fecha Fin Periodo UF
			Element fin = doc.createElement("Fin");
			fin.appendChild(doc.createTextNode(Utility.convertDateToString(fechaFin, DATE_FORMAT_YYYY_MM_DD)));
			rootElement.appendChild(fin);

			// Listado de UF del periodo
			Element ufs = doc.createElement("UFs");
			rootElement.appendChild(ufs);
			
			if (listaUF != null && !listaUF.isEmpty()) {
				for (Uf objUF : listaUF) {
					// Datos UF
					Element ufTag = doc.createElement("UF");
					ufs.appendChild(ufTag);
					// Fecha UF
					Element fechaUF = doc.createElement("Fecha");
					fechaUF.appendChild(
							doc.createTextNode(Utility.convertDateToString(objUF.getFecha(), DATE_FORMAT_YYYY_MM_DD)));
					ufTag.appendChild(fechaUF);

					// Valor UF
					Element valorUF = doc.createElement("Valor");
					valorUF.appendChild(doc.createTextNode(objUF.getValor().toString()));
					ufTag.appendChild(valorUF);
				}
			}

			TransformerFactory transformerFactory = TransformerFactory.newInstance();
			Transformer transformer = transformerFactory.newTransformer();
			DOMSource source = new DOMSource(doc);
			StreamResult result = new StreamResult(new File(PATH_FILE));

			transformer.transform(source, result);

			System.out.println("Archivo XML creado con exito.");

		} catch (ParserConfigurationException pce) {
			pce.printStackTrace();
		} catch (TransformerException tfe) {
			tfe.printStackTrace();
		}
	}
}
