package cl.previred.tres.adapters;

import java.io.IOException;
import java.time.ZoneId;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import com.previred.desafio.tres.uf.vo.Uf;


public class UFAdapter extends TypeAdapter<Uf> {

	@Override
	public void write(JsonWriter out, Uf value) throws IOException {
		out.beginObject();
		out.name("fecha");
		out.value(value.getFecha().toInstant().atZone(ZoneId.systemDefault()).toLocalDate().toString());
		out.name("dato");
		out.value(String.format("%,.2f", value.getValor()));
		out.endObject();
	}

	@Override
	public Uf read(JsonReader in) throws IOException {
		// TODO Auto-generated method stub
		return null;
	}

}
