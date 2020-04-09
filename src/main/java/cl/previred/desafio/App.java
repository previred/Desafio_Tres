package cl.previred.desafio;

import java.io.File;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.TreeMap;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.TransformerFactoryConfigurationError;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.DOMImplementation;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Text;

import com.previred.desafio.tres.uf.DatosUf;
import com.previred.desafio.tres.uf.Valores;
import com.previred.desafio.tres.uf.vo.Uf;

public class App {
	public static void main(String[] args) throws ParseException {
		Valores v = new Valores();

		Set<Uf> item = v.getRango().getUfs();

		crearXml(item, v.getRango().getInicio(), v.getRango().getFin());

	}

	public static String formatearFecha(Date date) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
		String fecha = sdf.format(date);
		return fecha;
	}

	@SuppressWarnings("unlikely-arg-type")
	private static void crearXml(Set<Uf> item, Date fechaInicio, Date fechaFin) {
		// Creo una instancia de DocumentBuilderFactory
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		// Creo un documentBuilder
		DocumentBuilder builder = null;
		try {
			builder = factory.newDocumentBuilder();
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// Creo un DOMImplementation
		DOMImplementation implementation = builder.getDOMImplementation();
		// Creo un documento con un elemento raiz
		Document documento = implementation.createDocument(null, "valores", null);
		documento.setXmlVersion("1.0");
		Element uf = documento.createElement("UF");
		Element ufs = documento.createElement("UFs");
		Element inicio = documento.createElement("inicio");
		Element fin = documento.createElement("fin");
		Text fechaIni = documento.createTextNode(formatearFecha(fechaInicio));
		Text fechaTermino = documento.createTextNode(formatearFecha(fechaFin));
		List<Date> listFecha = new ArrayList<>();
		// utlizo la interfaz Map para recorrer el arreglo, además utilizo
		// Collections.reverseOrder()
		// para ordenar la Key de forma descendente.
		Map<Double, Date> map = new TreeMap<Double, Date>(Collections.reverseOrder());
		for (Uf o : item) {
			map.put(o.getValor(), o.getFecha());
			listFecha.add(o.getFecha());
		}

		List<Date> fechas = new ArrayList<>();

		fechas.add(fechaInicio);
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(fechaInicio);
		Date d = fechaInicio;
		while (d.compareTo(fechaFin) == -1) {
			calendar.add(Calendar.DAY_OF_YEAR, 1);
			d = calendar.getTime();
			fechas.add(calendar.getTime());
		}
		fechas.add(fechaFin);
		for (int j = 0; j < listFecha.size(); j++) {
			if (fechas.indexOf((Date) listFecha.toArray()[j]) > 1) {
				fechas.remove(fechas.indexOf((Date) listFecha.toArray()[j]));
			}
		}
		if (listFecha.size() < 100) {
			for (int i = fechas.size(); i < 101; i++) {

				Optional<Double> of = Optional.of(DatosUf.getInstance().getUf(fechas.get(i)).getValor());
				if (!"".equals(of)) {
					map.put(of.get(), fechas.get(i));

				}

			}
		}
		Iterator<Double> it = map.keySet().iterator(); 
		
		while (it.hasNext()) {
			Double key = (Double) it.next();
			Element fecha = documento.createElement("fecha");
			Text textFecha = documento.createTextNode(formatearFecha(map.get(key)));
			fecha.appendChild(textFecha);
			uf.appendChild(fecha);
			Element dato = documento.createElement("dato");
			Text textDato = documento.createTextNode(Double.toString(key));
			dato.appendChild(textDato);
			uf.appendChild(dato);
		}

		inicio.appendChild(fechaIni);
		fin.appendChild(fechaTermino);
		ufs.appendChild(uf);

		// Añado al root los elementos inicio, fin y ufs
		documento.getDocumentElement().appendChild(inicio);
		documento.getDocumentElement().appendChild(fin);
		documento.getDocumentElement().appendChild(ufs);
		// Asocio el source con el Document
		Source source = new DOMSource(documento);
		// Creo el Result, indicado que fichero se va a crear
		Result result = new StreamResult(new File("valores.xml"));

		// Creo un transformer, se crea el fichero XML
		Transformer transformer = null;
		try {
			transformer = TransformerFactory.newInstance().newTransformer();
		} catch (TransformerConfigurationException | TransformerFactoryConfigurationError e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			transformer.transform(source, result);
//					System.out.println("paso");
		} catch (TransformerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("e: " + e);
		}

	}
}
