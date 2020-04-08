package com.previred.desafio;

import com.previred.desafio.tres.uf.vo.Uf;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import javax.xml.parsers.*;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.apache.logging.log4j.*;
import org.w3c.dom.*;

/**
 * @author Carolina Fuenzalida
 */
public class CreaXML {

    static Logger log = LogManager.getRootLogger();
    static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    static DecimalFormat formateador = new DecimalFormat("###,###.##");

    /**
     * Método para generar archivo XML llamado valores.xml
     *
     * @param fechaInicio Fecha inicial del intervalo
     * @param fechaFin Fecha final del intervalo
     * @param listaDatos Lista para construir los tags del XML
     */
    public static void generarXML(Date fechaInicio, Date fechaFin, List<Uf> listaDatos) {
        Document dom;
        Element e = null;

        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        try {
            DocumentBuilder db = dbf.newDocumentBuilder();
            dom = db.newDocument();

            // create the root element
            Element rootEle = dom.createElement("valores");

            // create data elements and place them under root
            e = dom.createElement("inicio");
            e.appendChild(dom.createTextNode(sdf.format(fechaInicio)));
            rootEle.appendChild(e);

            e = dom.createElement("fin");
            e.appendChild(dom.createTextNode(sdf.format(fechaFin)));
            rootEle.appendChild(e);

            Element ufs = dom.createElement("UFs");
            rootEle.appendChild(ufs);

            if (!listaDatos.isEmpty()) {

                Iterator<Uf> iter = listaDatos.iterator();
                while (iter.hasNext()) {
                    Uf next = iter.next();

                    Element uf = dom.createElement("UF");

                    Element fecha = dom.createElement("fecha");
                    fecha.appendChild(dom.createTextNode(sdf.format(next.getFecha())));
                    uf.appendChild(fecha);

                    Element dato = dom.createElement("dato");
                    dato.appendChild(dom.createTextNode(formateador.format(next.getValor())));
                    uf.appendChild(dato);

                    ufs.appendChild(uf);
                }
            }

            rootEle.appendChild(ufs);
            dom.appendChild(rootEle);
            dom.setXmlStandalone(true);

            try {
                Transformer tr = TransformerFactory.newInstance().newTransformer();
                tr.setOutputProperty(OutputKeys.METHOD, "xml");
                tr.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
                tr.setOutputProperty(OutputKeys.INDENT, "yes");
                tr.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4");

                Source origen = new DOMSource(dom);
                Result destino = new StreamResult("valores.xml");
                tr.transform(origen, destino);

                System.out.println("Archivo valores.xml ha sido creado");

            } catch (TransformerConfigurationException ex) {
                System.out.println("ERROR: No se ha podido crear el transformador del documento XML\n" + ex.getMessage());
            } catch (TransformerException ex) {
                System.out.println("ERROR: No se ha podido crear la salida del documento XML\n" + ex.getMessage());
            }
        } catch (ParserConfigurationException pce) {
            System.out.println("ERROR: No se ha podido crear el generador de documentos XML\n" + pce.getMessage());
        }
    }
}
