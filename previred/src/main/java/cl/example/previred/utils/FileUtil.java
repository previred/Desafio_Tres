package cl.example.previred.utils;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import com.previred.desafio.tres.uf.vo.Uf;
import com.previred.desafio.tres.uf.vo.Ufs;

public class FileUtil {
	
	 public static void toCSV(List<Uf> ufs, Ufs rangoUFS) {
	    	String file = "../valores.csv";
	    	String separador = ";";
	    	String saltoLinea = "\n";
	    	try {
	    		FileWriter fw = new FileWriter(file);
	    		fw.append("1");
	    		fw.append(separador);
	    		fw.append(Util.formatearFecha(rangoUFS.getInicio()));
	    		fw.append(separador);
	    		fw.append(Util.formatearFecha(rangoUFS.getFin()));
	    		
	    		ufs.stream().forEach(u -> {
	    			try {
						fw.append(saltoLinea);
						fw.append("2");
			    		fw.append(separador);
						fw.append(Util.formatearFecha(u.getFecha()));
			    		fw.append(separador);
			    		fw.append(Util.formatearDouble(u.getValor()));
					} catch (IOException e) {
						e.printStackTrace();
					}
	    		});
	    		
	    		fw.flush();
				fw.close();
				
	    	} catch (IOException e) {
				e.printStackTrace();
			}
	    }
}
