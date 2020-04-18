package com.previred.challenge.util;

import com.fasterxml.jackson.databind.ObjectMapper;

public class CloneUtil {

	private CloneUtil() {
		throw new AssertionError("Error, private construct (not possible init construct)");
	}
	
	public static <T> T Object(Object object,  Class<T> toValueType) {
		ObjectMapper mapper = new ObjectMapper();
		try {
			return mapper.convertValue(object, toValueType);
		} catch (Exception e) {
			throw new RuntimeException();
		}
	}
}
