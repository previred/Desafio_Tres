package com.previred.desafio.tres.impl.exportar.csv;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.Writer;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;

import com.previred.desafio.tres.inter.exportar.IExportarArchivo;
import com.previred.desafio.tres.uf.vo.Uf;
import com.previred.desafio.tres.uf.vo.Ufs;

public class CSVFile implements IExportarArchivo {

	private static final String NO_SE_LOGRO_GENERAR_EL_ARCHIVO = "no.se.logro.generar.el.archivo";
	private static final String ERROR_AL_CERRAR_ARCHIVO = "error.al.cerrar.archivo";
	private static final String NOMBRE_ARCHIVO = "valores.csv";
	private static final SimpleDateFormat FORMATO_FECHA = new SimpleDateFormat("yyyy-MM-dd");

	@Override
	public void exportar(List<Uf> ufList, Ufs ufs) throws Exception {
		Writer out = null;
		CSVPrinter printer = null;
		try {
			out = new PrintWriter(NOMBRE_ARCHIVO, "UTF-8");
			printer = new CSVPrinter(out, CSVFormat.DEFAULT.withHeader(generarCabecera(ufs)).withDelimiter(';'));
			NumberFormat nf = NumberFormat.getInstance(new Locale("es", "CL"));
			nf.setMinimumFractionDigits(2);
			String codigoCuerpo = "2";
			for (Uf uf : ufList) {
				printer.printRecord(codigoCuerpo, FORMATO_FECHA.format(uf.getFecha()), nf.format(uf.getValor()));
			}
		} catch (IOException e) {
			throw new IOException(NO_SE_LOGRO_GENERAR_EL_ARCHIVO, e);
		} catch (Exception e) {
			throw new Exception(NO_SE_LOGRO_GENERAR_EL_ARCHIVO, e);
		} finally {
			cerrarPrinter(printer);
			cerrarOutput(out);
		}
	}

	private void cerrarOutput(Writer out) throws IOException {
		try {
			if (out != null) {
				out.close();
			}
		} catch (IOException e) {
			throw new IOException(ERROR_AL_CERRAR_ARCHIVO, e);
		}
	}

	private void cerrarPrinter(CSVPrinter printer) throws IOException {
		try {
			if (printer != null) {
				printer.flush();
				printer.close();
			}
		} catch (IOException e) {
			throw new IOException(ERROR_AL_CERRAR_ARCHIVO, e);
		}
	}

	/**
	 * Cabecera del csv
	 * 
	 * @param ufs
	 *            objeto que contine la fecha de inicio y fin
	 * @return
	 */
	private String[] generarCabecera(Ufs ufs) {
		String[] cabecera = { "1", FORMATO_FECHA.format(ufs.getInicio()), FORMATO_FECHA.format(ufs.getFin()) };
		return cabecera;
	}

	@Override
	public String getFileName() {
		return NOMBRE_ARCHIVO;
	}

}
