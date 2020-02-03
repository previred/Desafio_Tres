package cl.gao;

import java.io.FileWriter;
import java.lang.reflect.Type;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import com.previred.desafio.tres.uf.vo.Ufs;

public class UfsJsonSerializer {
	private UfsJsonSerializer() {
	}

	private Ufs ufs;

	static public UfsJsonSerializer from(Ufs ufsArg) {
		UfsJsonSerializer serializer = new UfsJsonSerializer();
		serializer.ufs = ufsArg;
		return serializer;
	}

	public void writeToPath(String path) {
		try {
			Gson gson = new GsonBuilder().registerTypeAdapter(Double.class, new NumerosFormateados())
					.setDateFormat("yyyy-MM-dd").setPrettyPrinting().create();
			try (FileWriter writer = new FileWriter(path)) {
				gson.toJson(ufs, writer);
			}
		} catch (Exception e) {
			throw new RuntimeException("UfsJsonSerializer.writeToPath failed", e);
		}
	}

	static public class NumerosFormateados implements JsonSerializer<Double> {
		DecimalFormat formatoNumerico;
		public NumerosFormateados() {
			DecimalFormatSymbols dfs = new DecimalFormatSymbols();
			dfs.setDecimalSeparator(',');
			dfs.setGroupingSeparator('.');
			formatoNumerico = new DecimalFormat("###,###.###",dfs);
		}
		
		@Override
		public JsonElement serialize(Double numero, Type typeOfSrc, JsonSerializationContext context) {
			return context.serialize(formatoNumerico.format(numero));
		}
	}
}
