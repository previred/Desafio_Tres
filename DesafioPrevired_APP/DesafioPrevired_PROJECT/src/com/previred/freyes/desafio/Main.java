package com.previred.freyes.desafio;

import com.previred.desafio.tres.uf.DatosUf;
import com.previred.desafio.tres.uf.Valores;
import com.previred.desafio.tres.uf.vo.Uf;
import com.previred.desafio.tres.uf.vo.Ufs;

import com.previred.freyes.desafio.DTO.UeFe;
import com.previred.freyes.desafio.DTO.ValoresUF;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.StringWriter;

import java.text.SimpleDateFormat;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

public class Main {
    
    public static void main(String[] args) {

        DatosUf datosUf = DatosUf.getInstance();

        Valores valores = new Valores();
        ValoresUF valoresUf = new ValoresUF();

        Ufs ufs = new Ufs();
        ufs = valores.getRango();
        List<Uf> ufslist = new ArrayList<>(ufs.getUfs());
        List<Uf> auxList = new ArrayList<Uf>();
        
        List<UeFe> uefeList = new ArrayList<UeFe>();

        SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
        
        try {
            
            ordenarDescendente(ufslist);

            for (int i = 0; i < ufslist.size(); i++) {
                int diferencia = 0;
                if (i < ufslist.size() - 1) {
                    diferencia = (int) ((ufslist.get(i)
                                                .getFecha()
                                                .getTime() - ufslist.get(i + 1)
                                                                    .getFecha()
                                                                    .getTime()) / 86400000) * -1;
                }
                if (diferencia > 0) {
                    for (int x = 0; x < diferencia - 1; x++) {
                        Calendar calendar = Calendar.getInstance();
                        calendar.setTime(ufslist.get(i).getFecha());
                        calendar.add(Calendar.DAY_OF_YEAR, x + 1);

                        Date fechaNueva = calendar.getTime();

                        Uf nuevaUf = new Uf();
                        nuevaUf.setFecha(fechaNueva);
                        nuevaUf.setValor(datosUf.getUf(fechaNueva).getValor());

                        auxList.add(nuevaUf);
                    }
                }
            }

            for (int i = 0; i < auxList.size(); i++) {
                ufslist.add(auxList.get(i));
            }

            ordenarDescendente(ufslist);

            for (int i = 0; i < ufslist.size(); i++) {
                UeFe uf = new UeFe();
                uf.setFecha(formato.format(ufslist.get(i).getFecha()));
                uf.setValor(ufslist.get(i).getValor());
                uefeList.add(uf);
            }

            valoresUf.setInicio(formato.format(ufs.getInicio()));
            valoresUf.setFin(formato.format(ufs.getFin()));
            valoresUf.setUf(uefeList);
            
            String xml = toXml(valoresUf);
            String rutaArchivo = creaXml(xml);
            System.out.println("Archivo creado en la siguiente ruta: " + rutaArchivo);

        } catch (Exception e) {
            System.out.println("Error al crear XML - [Detalle]=> " + e.getMessage());
        }
    }

    //Metodo que permite ordenar de manera descendente segun la fecha
    private static void ordenarDescendente(List<Uf> ufslist) {
        Collections.sort(ufslist, new Comparator<Uf>() {
            public int compare(Uf uf1, Uf uf2) {
                return uf1.getFecha().compareTo(uf2.getFecha());
            }
        });
    }

    //Metodo para transformar a XML
    private static String toXml(ValoresUF valoresUf) {
        try {
            //Create JAXB Context
            JAXBContext jaxbContext = JAXBContext.newInstance(ValoresUF.class);

            //Create Marshaller
            Marshaller jaxbMarshaller = jaxbContext.createMarshaller();

            //Required formatting??
            jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);

            //Print XML String to Console
            StringWriter sw = new StringWriter();

            //Write XML to StringWriter
            jaxbMarshaller.marshal(valoresUf, sw);

            //Verify XML Content
            String xmlContent = sw.toString();
            
            return xmlContent;

        } catch (JAXBException e) {
            return "Error al transformar XML - [Detalle]=> " + e.getMessage();
        }
    }
    
    //Metodo para crear archivo XML
    private static String creaXml(String xml){
        final String NOMBRE_ARCHIVO = "valores.xml";
        
        try {	
            File file = new File(NOMBRE_ARCHIVO);
            
            // Si el archivo no existe es creado
            if (!file.exists()) {
                file.createNewFile();
            }
            
            FileWriter fw = new FileWriter(file);
            BufferedWriter bw = new BufferedWriter(fw);
            
            bw.write(xml);
            bw.close();
            
            String ruta = file.getAbsolutePath();
            
            return ruta;
            
        } catch(IOException e) {
            return "Error al crear el arhivo xml - [Detalle]=> " + e.getMessage();
        }
    }
}