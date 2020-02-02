package com.previred.desafio.tres.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.PropertyResourceBundle;

import org.apache.log4j.Logger;

public class ControlarException {

	private static final String NO_SE_LOGRO_GENERAR_EL_ARCHIVO = "No se logro generar el archivo.";
	private final static Logger LOGGER = Logger.getLogger(ControlarException.class.getName());

	public String getMessage(String key) {
		try {
			PropertyResourceBundle propertyBundle = this.getReportsBundle();
			if (propertyBundle.containsKey(key)) {
				return propertyBundle.getString(key);
			} else {
				return NO_SE_LOGRO_GENERAR_EL_ARCHIVO;
			}
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			return NO_SE_LOGRO_GENERAR_EL_ARCHIVO;
		}
	}

	private PropertyResourceBundle getReportsBundle() throws IOException {
		FileInputStream is = null;
		try {
			final File file = new File(System.getProperty("user.dir") + File.separator + "src" + File.separator + "main"
					+ File.separator + "resources" + File.separator + "mensajes.properties");
			is = new FileInputStream(file);
			PropertyResourceBundle reportsBundle = new PropertyResourceBundle(is);
			return reportsBundle;
		} catch (FileNotFoundException e) {
			throw e;
		} catch (IOException e) {
			throw e;
		} finally {
			cerrarAchivo(is);
		}
	}

	/**
	 * Cierra el recurso
	 * 
	 * @param is
	 * @throws IOException
	 */
	private void cerrarAchivo(FileInputStream is) throws IOException {
		if (is != null) {
			try {
				is.close();
			} catch (IOException e) {
				throw e;
			}
		}
	}
}
