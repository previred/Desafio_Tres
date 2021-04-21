package cl.previred.tres;

import com.previred.desafio.tres.uf.Valores;
import com.previred.desafio.tres.uf.vo.Uf;

import cl.previred.tres.adapters.LocalDateAdapter;
import cl.previred.tres.adapters.UFAdapter;

import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonIOException;

public class Main {

	public static void main(String[] args) throws IOException {
		UFRange range = new UFRange(new Valores().getRango());
		
		GsonBuilder builder = new GsonBuilder();
		builder.registerTypeAdapter(LocalDate.class, new LocalDateAdapter());
		builder.registerTypeAdapter(Uf.class, new UFAdapter());
		builder.setPrettyPrinting();
		Gson gson = builder.create();
		
		FileWriter writer = new FileWriter("valores.json");
		try {
			gson.toJson(range, writer);
		} catch (JsonIOException e) {
			System.out.println("Error writing the json file");
		} finally {
			writer.flush();
			writer.close();
		}
	}
	
}
