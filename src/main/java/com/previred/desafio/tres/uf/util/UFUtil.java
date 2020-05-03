package com.previred.desafio.tres.uf.util;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.previred.desafio.tres.uf.common.UFConstant;

public abstract class UFUtil {
	
	public static String dateFormat(Date date, String pattern) {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
		return simpleDateFormat.format(date);
	}
	
	public static String valueFormat(Double value) {
		return String.format(UFConstant.VALUEREGEX, value);
	}
	
	
}
