package cl.desafio.tres.util;


import com.fasterxml.jackson.databind.util.StdConverter;

public class CustomDoubleDeserializer  extends StdConverter<Double, String> {

	  @Override
	  public String convert(Double value) {
	      return Utils.separatedThousand(value);
	  }
	}