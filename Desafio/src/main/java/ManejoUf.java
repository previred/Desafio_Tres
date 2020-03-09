

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Comparator;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.previred.desafio.tres.uf.DatosUf;
import com.previred.desafio.tres.uf.Valores;
import com.previred.desafio.tres.uf.vo.Uf;
import com.previred.desafio.tres.uf.vo.Ufs;

public class ManejoUf {

	static Valores valor;
	static DatosUf datos;
	
	public void procesarRangos() {
		valor = new Valores();
		Ufs ufs = valor.getRango();
		
		Set<UfComparable> lista = new HashSet<UfComparable>();
		UfComparable uc = new UfComparable();
		for (Uf u : ufs.getUfs()) {
			uc = new UfComparable();
			if (null == u.getValor() || u.getValor().equals(0)) {
				u.setValor(datos.getUf(u.getFecha()).getValor());
			}
			uc.setFecha(u.getFecha());
			uc.setValor(u.getValor());
			lista.add(uc);
		}
		ordenarLista(lista, ufs.getInicio(), ufs.getFin());
	}
	
	public void ordenarLista(Set<UfComparable> lista, Date inicio, Date fin) {
		List<UfComparable> lstOfUf = lista.stream()
                .sorted(Comparator.comparing(
                        UfComparable::getFecha))
                        .collect(Collectors.toList());
		try {
			escribirCSV(lstOfUf, inicio, fin);
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("Error al escribir archivo");
		}
	}
	
	public void escribirCSV(List<UfComparable> orden, Date inicio, Date fin) throws IOException {
		Path path = Paths.get("C:\\Users\\Public\\Documents\\testUf.csv");
		try (BufferedWriter writer = Files.newBufferedWriter(path)) {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
			
			StringBuilder sb = new StringBuilder();
			sb.append("1; "+sdf.format(inicio)+"; "+sdf.format(fin));
			sb.append("\n");
			for (UfComparable u : orden) {
				sb.append("2; "+ sdf.format(u.getFecha())+"; "+u.getValor());
				sb.append("\n");
			}
			writer.write(sb.toString());

			System.out.println("done!");

		    } catch (FileNotFoundException e) {
		      System.out.println(e.getMessage());
		    }

	}
}
