package cl.gao;

import com.previred.desafio.tres.uf.DatosUf;
import com.previred.desafio.tres.uf.vo.Ufs;

public class DesafioTresApp {

	public static void main(String[] args) {
		DatosUf datosUf = com.previred.desafio.tres.uf.DatosUf.getInstance();
		UfsBuilder builder = UfsBuilder.from(new com.previred.desafio.tres.uf.Valores().getRango());
		Ufs ufs = builder.rellenarDiasFaltantes(datosUf).ordenarDescendentePorFecha().build();
		UfsJsonSerializer.from(ufs).writeToPath("./valores.json");		
	}
}
