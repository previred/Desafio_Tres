package com.previred.challenge.config;

public final class EndPoint {

	public final static String API = "/api";
	public final static String V1 = "/v1";
	public final static String UF = "/uf";
	
	public final static String FILE = "/file";
	public final static String JSON = "/json";
	public final static String CSV = "/csv";
	public final static String XML = "/xml";
	
		
	private EndPoint() {
		throw new AssertionError("Error, private construct (not possible init construct)");
	}
}
