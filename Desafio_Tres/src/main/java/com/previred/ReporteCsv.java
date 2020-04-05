package com.previred;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.previred.desafio.tres.uf.DatosUf;
import com.previred.desafio.tres.uf.vo.Uf;
import com.previred.desafio.tres.uf.vo.Ufs;

public class ReporteCsv {
	
	private static Logger LOG = LoggerFactory.getLogger(ReporteCsv.class);
	
	private Ufs ufs;
	private DatosUf datosUf;
	
	public ReporteCsv(Ufs ufs, DatosUf datosUf) {
		super();
		this.ufs = ufs;
		this.datosUf = datosUf;
	}

	public Ufs getUfs() {
		return ufs;
	}

	public void setUfs(Ufs ufs) {
		this.ufs = ufs;
	}

	public DatosUf getDatosUf() {
		return datosUf;
	}

	public void setDatosUf(DatosUf datosUf) {
		this.datosUf = datosUf;
	}

	@Override
	public String toString() {
		return "ReporteCsv [ufs=" + ufs + ", datosUf=" + datosUf + "]";
	}

	public void valoresUfToCsv() {
		BufferedWriter writer = null;
        System.out.println("Generando Archivo csv ... ");
        LOG.info("Generando Archivo csv ... ");
        try {
//            File file = new File("C:\\valores.csv");
            File file = new File("valores.csv");
            //TODO: validar que el archivo no esté abierto
            file.delete();
            if (!file.createNewFile()) {
                System.out.println("No se pudo crear el archivo valores.csv");
                LOG.info("No se pudo crear el archivo valores.csv");
            }
            writer = new BufferedWriter(new FileWriter(file));

            //imprimir primera fila del archivo csv
            writer.write(cabeceraToCsv());
            writer.newLine();
//            imprimir el detalle de la UF
//            for(Uf uf : datosUf.getUfs(ufs.getInicio(), ufs.getFin())) {
            for(Uf uf : getSortedList()) {
//    			System.out.println(uf.getFecha()+" - "+uf.getValor());
    			writer.write(ufToCsv(uf));
                writer.newLine();
    		}
            writer.flush();
            System.out.println("Fin de la Generación del Archivo valores.csv ... ");
            LOG.info("Fin de la Generación del Archivo c:\\valores.csv ");
        } catch (IOException ex) {
        	LOG.info("Proceso Abortado Por Exception: ", ex);
        }
        finally {
            try {
                    if(writer!=null)
                        writer.close();
            }
            catch (java.io.IOException ioe) {
                System.out.println("Error: "+ioe.getMessage());
            }
        }
	}
	
	public String ufToCsv(Uf uf) {
		String linea;
		
		linea = "2; ";
		linea = linea + getDateAsString(uf.getFecha(), "yyyy-MM-dd") + "; ";
		linea = linea + uf.getValor();
		return linea;
	}
	
	public String cabeceraToCsv() {
		String linea;
		
		linea = "1; ";
		linea = linea + getDateAsString(ufs.getInicio(), "yyyy-dd-MM") + "; ";
		linea = linea + getDateAsString(ufs.getFin(), "yyyy-dd-MM") + "; ";
		return linea;
	}
	
    /**
     * retorna una fecha como String en el formato que indica el parámetro
     * formato. Por ejemplo: "yyyyMMdd"
     * @param fecha
     * @param formato
     * @return
     */
    public String getDateAsString(Date fecha, String formato) {
        SimpleDateFormat formatter = new SimpleDateFormat(formato);

        if(fecha == null) {
            return "";
        } else {
            return formatter.format(fecha.getTime());
        }        
    }
    
	
	/**
	 * Retorna la lista con valores de las UFs ordenadas en forma descendente por fecha
	 * @return
	 */
	public List<Uf> getSortedList() {
		List<Uf> ufList = new ArrayList<>();
		ufList = datosUf.getUfs(ufs.getInicio(), ufs.getFin());
//		Collections.sort(datosUf.getUfs(ufs.getInicio(), ufs.getFin()), new Comparator<Uf>() {
		Collections.sort(ufList, new Comparator<Uf>() {
			
			@Override
			public int compare(Uf m1, Uf m2) {
//				ordena la lista en forma descendente
				return m2.getFecha().compareTo(m1.getFecha());
			}
		});
		
		return ufList;
	}
	
}
