package com.previred.challenge.util;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.text.DecimalFormat;

public final class Util {

	private Util() {
		throw new AssertionError("Error, private construct (not possible init construct)");
	}

	public static final String FORMAT_NUMBER = "###,###.##";
	public static final String FORMAT_DATE = "yyyy-MM-dd";

	public static String doubleToString(Double valor) {
		return new DecimalFormat(FORMAT_NUMBER).format(valor);
	}
	
	public static byte[] getByteArrayOutputStream(String data) {
		byte[] bs = data.getBytes();
		ByteArrayOutputStream baos = null;
		try {
			baos = new ByteArrayOutputStream();
			baos.write(bs);
			return bs;
		} catch (Exception e) {
			throw new RuntimeException();
		} finally {
			closeStream(baos);
		}
	}
	
	public static void closeStream(ByteArrayOutputStream baos) {
		if (baos != null) {
			try {
				baos.close();
			} catch (IOException e) {
				throw new RuntimeException();
			}
		}
	}
}
